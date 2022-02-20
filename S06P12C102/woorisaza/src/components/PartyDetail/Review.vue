<template>
    <div v-if="party!=null">
        <span class="title">
             후기 남기기
        </span>
        <div class="mt-3 pr-5 pl-5">
            <div>
                <carousel 
                    :perPage="1" 
                    :navigation-enabled="true"
                    loop
                    :paginationPadding="5"
                    @page-change = "member"
                    style="text-align:center"
                    >
                    <slide v-for="(item, index) in party" :key="index">
                        <div>
                            <div v-if="item.isBoss==true">파티장</div>
                            <div v-if="item.isBoss==false">파티원</div>
                            <MyProfile :userId="item.profileId" style="color:black;"></MyProfile>
                        </div>
                    </slide>
                </carousel>
            </div>
            <div class="mt-3">
                <b-form-rating v-model="score"  variant="warning" @change="updateScore"></b-form-rating>
            </div>
            <div class="mt-3">
                <b-container>
                    <b-row>
                        <b-col><img src="@/assets/review/1.png" alt="" @click="clickImage(0)"><br>{{tag[0]}}</b-col>
                        <b-col><img src="@/assets/review/2.png" alt="" @click="clickImage(1)"><br>{{tag[1]}}</b-col>
                        <b-col><img src="@/assets/review/3.png" alt="" @click="clickImage(2)"><br>{{tag[2]}}</b-col>
                        <b-col><img src="@/assets/review/4.png" alt="" @click="clickImage(3)"><br>{{tag[3]}}</b-col>
                    </b-row>
                    <b-row class="mt-3">
                        <b-col><img src="@/assets/review/5.png" alt="" @click="clickImage(4)"><br>{{tag[4]}}</b-col>
                        <b-col><img src="@/assets/review/6.png" alt="" @click="clickImage(5)"><br>{{tag[5]}}</b-col>
                        <b-col><img src="@/assets/review/7.png" alt="" @click="clickImage(6)"><br>{{tag[6]}}</b-col>
                        <b-col><img src="@/assets/review/8.png" alt="" @click="clickImage(7)"><br>{{tag[7]}}</b-col>
                    </b-row>
                </b-container>
            </div>
            <div class="mt-3">
                <b-form-textarea
                v-model="content"
                placeholder="자세한 후기를 적어주세요." 
                rows="6"
                no-resize
                ></b-form-textarea>
            </div>
            <div class="mt-3" style="text-align : center">  
                <b-button variant="secondary" class = "mr-4" @click="notNow">나중에 하기</b-button>
                <b-button variant="warning" class = "ml-4" @click="registerReview">작성 완료</b-button>
            </div>
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
import MyProfile from "@/views/MySaza/MyProfile.vue";
export default {
    name: 'Review',
    components : {
        MyProfile,
    },
    data() {
        return {
            myId : this.$cookie.get("id"), //fromuser
            myInfo : null,
            partyId : this.$route.params.partyId,
            party : [],
            article : null,
            memberInfo : null,
            reviewList : [],
            toUser : 0,
            score : 0,
            content : "",
            tag : ["#최고","#친절","#쏘쿨","#천사","#센스","#쏘쏘","#잠수","#별로"],
            index : 0,
        };
    },

    created() {
        this.getInfo();
    },
    mounted() {
        
    },

    methods: {
        getInfo(){
            axios_contact({
            method : "get",
            url : "/party?partyId="+this.partyId,
        }).then(({data})=>{
            for (let index = 0; index < data.length; index++) {
                if(data[index].profileId == this.myId){
                    // console.log(data[index])
                    this.myInfo = data[index]
                }
                else{
                    this.party.push(data[index])
                    this.reviewList.push({
                        "content" : "",
                        "fromuser" : this.myId,
                        "score" : 0,
                        "toUser" : data[index].profileId
                    })
                }
            }
            this.memberInfo = this.party[0];
            this.toUser=this.party[0].profileId;
            axios_contact({
                method : "get",
                url : "/article/"+this.memberInfo.articleId,
            }).then(({data})=>{
                this.article = data.article+" ";
            })
        })
        },

        member(index){
            this.index = index;
            this.toUser=this.party[index].profileId;
            this.memberInfo = this.party[index]
            this.score = this.reviewList[this.index].score
            this.content = this.reviewList[this.index].content
        },

        clickImage(data){
            console.log(this.tag[data])
            this.content += this.tag[data]
            this.reviewList[this.index].content = this.content;
        },
        
        updateScore(){
            this.reviewList[this.index].score = this.score;
        },
        
        registerReview(){
            console.log("리뷰 썻어요")
            for (let index = 0; index < this.reviewList.length; index++) {
                console.log(this.reviewList[index].content)
                axios_contact({
                    method : "post",
                    url : "/review",
                    data : {
                        "content": this.reviewList[index].content,
                        "fromUser": this.reviewList[index].fromuser,
                        "score": this.reviewList[index].score,
                        "toUser": this.reviewList[index].toUser
                    }
                }).then(({data})=>{
                    // console.log(data)
                    data
                    this.$router.push("/partydetail/"+this.partyId);
                })
            }
        },

        notNow(){
            this.$router.push("/partydetail/"+this.partyId);
        },
    },
};
</script>

<style scoped>
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}
.col{
    width : 20px
}
.btn-warning{
    background-color: #F1A501 ;
    font-size : 0.7em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius : 2em;
    color : white;
    width : 100px;
}
.btn-secondary{
    background-color: #C4C4C4     ;
    font-size : 0.7em;
    padding: 0.5em;
    border-color: #C4C4C4 ;
    border-radius : 2em;
    width : 100px;
}
</style>