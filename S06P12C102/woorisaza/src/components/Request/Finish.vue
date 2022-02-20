<template>
    <div style="text-align:center" v-if="article!=null">
        <span class="title">
            정산 완료
        </span>
        <div class="mt-4">
            파티장이 구매를 완료하면 알려드릴게요!
        </div>
        <div class="mt-3">
            <img src="@/assets/fin.png" style="width:10em" alt="">
        </div>
        
        <div class="mt-3">
            <b-button variant="warning" @click="toPartyDetail">상세정보 보기</b-button>
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"

export default {
    name: 'Finish',
    props : {
        chooseNum : String,
    },
    data() {
        return {
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
        toPartyDetail(){
            // console.log("상세정보로 가!")
            this.$router.push("/partydetail/"+this.article.partyId)
        }
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
    width : 100px;
    font-size : 0.8em;
    padding: 0.5em;
    border-color: #F1A501;
    color : white;
    border-radius: 1em;
}
</style>