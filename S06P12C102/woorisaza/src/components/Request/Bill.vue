<template>
    <div>
        <span class="title">
            정산하기
        </span>
        <b-container>
            <div style="text-align:center">
                <div class="mt-3" style="font-size : 1.5em" v-if="article != null">
                    내가 결제할 금액 : {{article.myPrice*chooseNum}}원
                </div>
                <div class="mt-4">
                    <b-form-select v-model="selected" :options="paymentMethod" style="width:15em"></b-form-select>
                </div>
                <div class="mt-5">
                    <b-button v-if="selected!=null" variant="warning" @click="toFin">결제하기</b-button>
                    <b-button v-if="selected==null" variant="warning"  disabled>결제하기</b-button>
                </div>
            </div>
        </b-container>
    </div>
</template>

<script>
import {EventBus} from "@/event-bus.js"
import {axios_contact} from "@/common.js"

export default {
    name: 'Bill',
    props : {
        chooseNum : String,
    },
    data() {
        return {
            selected : null,
            paymentMethod : [
                {value:null, text:"결제 수단을 선택하세요."},
                {value:"kakao", text:"카카오페이"},
                {value:"naver", text:"네이버페이",disabled: true},
                {value:"toss", text:"토스",disabled: true},
                {value:"account", text:"신용카드",disabled: true},
                {value:"bankbook", text:"무통장입금",disabled: true},
            ],
            articleNo : this.$route.params.articleNo,
            article : null,
        };
    },
    created() {
        axios_contact({
            method : "get",
            url : "/article/"+this.articleNo,
        }).then(({data})=>{
            this.article = data.article;
        })
    },
    mounted() {
    },

    methods: {
        toFin(){
            EventBus
            console.log("파티 입장!")
            axios_contact({
                method : "post",
                url : "/memberinfo",
                data : {
                    "amount": this.chooseNum,
                    "paidMethod": this.selected,
                    "partyId": this.article.partyId,
                    "price": this.article.myPrice*this.chooseNum,
                    "profileId": this.$cookie.get("id")
                },
            }).then(({data})=>{
                console.log(data)
                EventBus.$emit("fin",true);
            })
        },
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}
.btn-warning{
    background-color: #F1A501 ;
    width : 300px;
    font-size : 0.8em;
    padding: 0.5em;
    border-color: #F1A501;
    color : white;
    border-radius: 1em;
}
</style>