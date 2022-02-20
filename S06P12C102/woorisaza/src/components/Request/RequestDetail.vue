<template>
    <div>
        <span class="title">
            신청서
        </span>
        <!-- 윗 부분 제품명 인원수 기간 등 -->
        <div class="mt-5" v-if="article!=null">
            <div style="font-size : 1.2em">
                제품 : {{article.product}}
            </div>
            <div> 
                <b-row>
                    <b-col sm="8">
                        <div>
                            인원 수 선택 : 
                            <b-form-input  type="number" aria-describedby="input-live-feedback" :state="nameState" :value="chooseNum" v-model="chooseNum" @change="sum" style="width:30%;display:inline" :max="article.totalRecruitMember-article.currentRecruitMember" min="1"></b-form-input>
                            <b-form-invalid-feedback id="input-live-feedback">
                                선택할 수 없는 인원입니다.
                            </b-form-invalid-feedback>
                        </div>
                    </b-col>
                    <b-col sm="4">
                        <div style="font-size:0.9em">
                            <div>기간 : ~{{article.deadline[0]}}.{{article.deadline[1]}}.{{article.deadline[2]}}</div>
                            <div>모집인원 : {{result}}/{{article.totalRecruitMember}}</div>
                            <div>위약금 : {{article.penalty}}%</div>
                        </div>
                    </b-col>
                </b-row>
            </div>
            <!-- 금액-->
            <div class="mt-5" style="border-bottom : 3px solid #F1A501">
                  <b-row style="font-size:0.8em; text-decoration:line-through">
                    <b-col sm="8">전체 구매시 필요한 금액</b-col>
                    <b-col sm="4">{{article.totalPrice}}원</b-col>
                </b-row>
                <b-row>
                    <b-col sm="8">사자들과 함께 사기</b-col>
                    <b-col sm="4">{{article.myPrice}}원 X {{chooseNum}}</b-col>
                </b-row>
            </div>
            <b-row>
                <b-col sm="8"></b-col>
                <b-col sm="4">총 {{chooseNum*article.myPrice}}원</b-col>
            </b-row>
        </div>
        <!-- 유의사항-->
        <b-container class="mt-5">
            <div class="box">
                <b-container class="p-4">
                    <span>유의사항</span>
                    <div class="mt-3">
                        <li>예상 수령일부터 7일 내에 연락이 두절될 경우 위약금은 받을 수 없습니다. 위에 고지된 위약금을 꼭 확인해주세요.</li>
                        <li>이의신청은 구매확정 페이지에서 가능합니다.</li>
                        <li>구매 취소는 파티 마감 전에만 가능하며, 취소가 반복될 시 이용에 불이익이 있을 수 있습니다.</li>
                        <li>식품의 경우 유통기한을 꼭 확인해주시고, 구매에 유의해주세요.</li>
                    </div>
                    <div class="mt-4" style="text-align:center">
                        <span  style="font-size:0.8em; color:#f2293c">주문 내용을 확인하였으며, 정보 제공 등에 동의합니다. </span>
                        <b-icon v-if="!agree" icon="check-circle" variant="secondary" @click="checkAgree"></b-icon>
                        <b-icon v-if="agree" icon="check-circle-fill" variant="primary" @click="checkAgree"></b-icon>
                    </div>
                </b-container>
            </div>
            <div class="mt-4" style="text-align : center">
                <b-button v-if="!agree || !nameState" variant="warning" disabled>정산하기</b-button>
                <b-button v-if="agree && nameState" variant="warning" @click="toBill">정산하기</b-button>
            </div>
        </b-container>
        
    </div>
</template>

<script>
import {EventBus} from "@/event-bus.js"
import {axios_contact} from "@/common.js"
export default {
    name: 'Request',
    computed: {
      nameState() {
        return this.chooseNum >= 1 &&  this.chooseNum <= this.article.totalRecruitMember-this.article.currentRecruitMember ? true : false
      }
    },
    data() {
        return {
            agree : false,
            articleNo : this.$route.params.articleNo,
            chooseNum : "1",
            result: 2,
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
        sum(){
            this.result = parseInt(this.article.currentRecruitMember)+parseInt(this.chooseNum);
        },
        checkAgree(){
            this.agree = !this.agree;
        },
        toBill(){
            EventBus.$emit("chooseNum",this.chooseNum);
            // this.$router.push("/request/"+this.articleNo).catch(()=>{});
        },
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}
.box{
        background-color:#FFF8F8 ; 
        box-shadow: 0px 0px 5px 0.1px grey; 
        width:100%;
        height:100%;
    }

li{
    font-size: 0.8em;
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