<template>
    <div>
        <span class="title">
             구매 인증폼
        </span>
        <div style="text-align:center" class="mt-3">
            <div class="photo" style="display:inline-block">
                <img  v-if="preImage==null" src="@/assets/fin.png" alt=""  style="width : 240px; height:280px">
                <img  v-if="preImage!=null" :src="preImage" alt="" style="width : 295px; height:295px">
            </div>
            <div class="mt-1" v-if="bm==0">
                <b-form-file v-model="image" plain @change="registerImage" accept=".jpg, .png"></b-form-file>
            </div>

            <div class="mt-3" style="text-align:left">
                운송장
                <b-form-input v-if="bm==0" v-model="billingNO" placeholder="송장 번호를 등록하세요."></b-form-input>
                <b-form-input v-if="bm==1" v-model="billingNO" placeholder="송장 번호를 등록하세요." disabled></b-form-input>
            </div>
            <div class="mt-3" v-if="bm==0">
                <b-row>
                    <b-col>택배 예상 도착일</b-col>
                    <b-col><input type="date" style="font-size : 0.8em" v-model="deliveryDate" :min=today :max=todayMax></b-col>
                </b-row><b-row>
                    <b-col>파티원 예상 수령일</b-col>
                    <b-col><input type="date" style="font-size : 0.8em" v-model="receiptDate" :min=today :max=todayMax></b-col>
                </b-row>
            </div>
            <div class="mt-3" v-if="bm==1">
                <b-row>
                    <b-col>택배 예상 도착일</b-col>
                    <b-col>{{deliveryDate}}</b-col>
                </b-row><b-row>
                    <b-col>파티원 예상 수령일</b-col>
                    <b-col>{{receiptDate}}</b-col>
                </b-row>
            </div>
            <div class="mt-5">
                <b-button v-if="bm==0" variant="warning" @click="registerPaidForm">구매 인증 폼 등록하기</b-button>
                <b-button v-if="bm==1" variant="warning" @click="goback">돌아가기</b-button>
            </div>
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
export default {
    name: 'Paidform',

    data() {
        return {
            image : null,
            preImage : null,
            billingNO : null,
            deliveryDate : null,
            receiptDate : null,
            partyId : this.$route.params.partyId,
            bm : this.$route.params.bm,
            updateOrNot : false,
            today : "",
            todayMax : "",
        };
    },

    created() {
        let today = new Date();   
        let year = today.getFullYear(); // 년도
        let month = today.getMonth() + 1;  // 월
        let date = today.getDate();  // 날짜
        this.today += year+"-"
        this.todayMax += (year+1)+"-"
        if(month<10){
            this.today+="0"
            this.todayMax+="0"
        }
        this.today+=month+"-"
        this.todayMax+=month+"-"
        if(date<10){
            this.today+="0"
            this.todayMax+="0"
        }
        this.today+=date
        this.todayMax+=date

        axios_contact({
            method : "get",
            url : "/paidForm/"+this.partyId,
        }).then(({data})=>{
            // console.log(data)
            // this.image = data.paidForm.pic;
            this.preImage = data.paidForm.pic;
            this.billingNO=data.paidForm.billingNo;

            this.deliveryDate = data.paidForm.deliveryDate[0]+"-";
            if(data.paidForm.deliveryDate[1]<10){
                this.deliveryDate+="0"
            }
            this.deliveryDate+=data.paidForm.deliveryDate[1]+"-"
            if(data.paidForm.deliveryDate[2]<10){
                this.deliveryDate+="0"
            }
            this.deliveryDate+=data.paidForm.deliveryDate[2]
            
            this.receiptDate = data.paidForm.receiptDate[0]+"-";
            if(data.paidForm.receiptDate[1]<10){
                this.receiptDate+="0"
            }
            this.receiptDate+=data.paidForm.receiptDate[1]+"-"
            if(data.paidForm.receiptDate[2]<10){
                this.receiptDate+="0"
            }
            this.receiptDate+=data.paidForm.receiptDate[2]
            this.updateOrNot=true;
        })
    },

    mounted() {
        
    },

    methods: {
        registerImage(event){
            var input = event.target;
            if(input.files && input.files[0]){
                var reader = new FileReader();
                reader.onload = (e) =>{
                    this.preImage = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
            this.imageChange = true;
        },

        registerPaidForm(){
            const that = this;
            const formData = new FormData();
            formData.append('uploadFile', this.image);
            if(this.updateOrNot){
                if(this.image==null){
                    axios_contact({
                        method : "put",
                        url : "/paidForm/"+that.partyId,
                        data : {
                            "billingNo": String(that.billingNO),
                            "deliveryDate": that.deliveryDate,
                            "partyId": that.partyId,
                            "pic": that.preImage,
                            "receiptDate": that.receiptDate
                        }
                    }).then(({data})=>{
                        data
                        this.$router.push("/partydetail/"+that.partyId)
                    })
                }else{
                    axios_contact({
                        method : "post",
                        url : "/paidForm/upload",
                        headers : {
                            'Content-Type': 'multipart/form-data',
                        },
                        data : formData,
                    }).then(({data})=>{
                        axios_contact({
                            method : "put",
                            url : "/paidForm/"+that.partyId,
                            data : {
                                "billingNo": String(that.billingNO),
                                "deliveryDate": that.deliveryDate,
                                "partyId": that.partyId,
                                "pic": data.url,
                                "receiptDate": that.receiptDate
                            }
                        }).then(({data})=>{
                            data
                            this.$router.push("/partydetail/"+that.partyId)
                        })
                    })
                }
            }else{
                axios_contact({
                    method : "post",
                    url : "/paidForm/upload",
                    headers : {
                        'Content-Type': 'multipart/form-data',
                    },
                    data : formData,
                }).then(({data})=>{
                    axios_contact({
                        method : "post",
                        url : "/paidForm",
                        data : {
                            "billingNo": that.billingNO,
                            "deliveryDate": that.deliveryDate,
                            "partyId": that.partyId,
                            "pic": data.url,
                            "receiptDate": that.receiptDate
                        }
                    }).then(({data})=>{
                        data
                        this.$router.push("/partydetail/"+that.partyId)
                    })
                })
            }
        },

        goback(){
            this.$router.push("/partydetail/"+this.partyId)
        }
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}
.photo{
    background-color: white;
    width:300px;
    height: 300px;
    border: 1px solid black;
}
.btn-warning{
    background-color: #F1A501 ;
    font-size : 0.7em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius : 2em;
    color : white;
    width : 200px;
}
</style>