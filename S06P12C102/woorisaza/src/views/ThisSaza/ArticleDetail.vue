<template>
    <div  v-if="article != null">
        <!-- {{articleNo}} -->
        <!-- 글 내용 -->
        <div>
            <!-- 글 제목 -->
            <div style="font-size:1em;font-weight:bold">{{article.title}}</div>
            <!-- 작성자 -->
            <div style="text-align:right;font-size:0.7em;cursor:pointer" @click="moveToUserpage">파티장 : {{article.author}}</div>
            <div v-if="finDeal" class="mt-3" style="text-align:center">
                <p class="finParty" style="color:white">거래가 완료된 파티입니다!</p>
            </div>
            <div v-if="dateover" class="mt-3" style="text-align:center">
                <p class="dateOver">기간이 만료된 파티입니다!</p>
            </div>
            <!-- 상품 사진 -->
            <div>
                <b-carousel
                v-model="slide"
                :interval="4000"
                controls
                indicators
                background="#ababab"
                @sliding-start="onSlideStart"
                @sliding-end="onSlideEnd"
                >
                    <b-carousel-slide v-for="(item, index) in article.pic" :key="index" :img-src="item"></b-carousel-slide>
                    <!-- <b-carousel-slide img-src="@/assets/fin.png"></b-carousel-slide> -->
                </b-carousel>
            </div>
        </div>
        <!-- 상품명 -->
        <div class="mt-4" style="font-size:1.3em">
            {{article.product}}
        </div>
        <!-- 상품 링크 + 기간+모집인원+1인당개수 -->
        <div class="mt-2">
            <b-row>
                <b-col >
                    <b-link target='_blank' :href="article.link" v-if="article.category!='DELIVERY'">링크 바로가기</b-link>
                </b-col>
                <b-col>
                    <div style="background-color:#D9E5FF;border-radius:0.3em;text-align:right;">
                        <div class="p-2">
                            <!-- 기간이 안나와요 -->
                            기간 : {{article.createdAt[0]}}.{{article.createdAt[1]}}.{{article.createdAt[2]}}~{{article.deadline[0]}}.{{article.deadline[1]}}.{{article.deadline[2]}} <br>
                            모집인원 : <b style="font-size:1.5em">{{article.currentRecruitMember}}</b> /{{article.totalRecruitMember}}<br>
                            최소 주문 수량 : {{article.totalProductCount/article.totalRecruitMember}}개
                        </div>
                    </div>    
                </b-col>  
            </b-row>
        </div>
        <!-- 전체 금액 + 개인 금액 -->
        <div class="mt-4">
            <b-row style="text-decoration:line-through;font-size:0.9em">
                <b-col >전체 구매 시 필요한 금액</b-col>
                <b-col style="text-align:right">{{article.totalPrice}}원</b-col>  
            </b-row>
            <b-row style="font-size:1.1em; font-weight:bold">
                <b-col>사자들과 함께 사기</b-col>
                <b-col style="text-align:right">{{article.myPrice}}원</b-col>  
            </b-row>
            
        </div>
        <!-- 찜버튼 + 참여하기 버튼 -->
        <div style="text-align:right" >
            <img v-if="isZzim" src="@/assets/zzimOn.png" style="width:1.6em;cursor:pointer" @click="deleteZZim">
            <img v-if="!isZzim" src="@/assets/zzimOff.png" style="width:1.6em;cursor:pointer" @click="addZZim">&nbsp;
            <b-button v-if="!inParty && article.currentRecruitMember<article.totalRecruitMember" variant="warning" pill @click="requestParty">참여하기</b-button>
            <b-button v-if="inParty" variant="primary" pill @click="moveToPartyDetail">상세보기</b-button>
            
        </div>
        <!-- 글내용 -->
        <div class="mt-3">
            <b-form-textarea
                id="textarea-auto-height"
                placeholder="내용이 없습니다."
                rows="8"
                max-rows="100"
                :value ="article.content"
                disabled
                no-resize
            ></b-form-textarea>
        </div>

        <!-- 목록버튼 -->
        <div style="text-align:right" class="mt-2">
            <b-button variant="warning" pill @click="toBoard" class="mr-1">목록</b-button>
            <b-button v-if="bossId==id" variant="success" pill @click="modifyArticle"  class="mr-1" style="width:8em">수정</b-button>
            <b-button v-if="bossId==id || user.isAdmin" variant="info" pill @click="deleteArticle">삭제</b-button>
        </div>
        <!-- 댓글 -->
        <hr>
        <div class="mt-3">
            <!-- 사람들 댓글 쓴 거 -->
            <!-- 댓글듯 for문 -->
            <div :key="commentRerender">
                <!-- 각 댓글 -->
                <div v-for="(comment, index) in commentList" :key="index">
                    <!-- {{comment}} -->
                    <div  style="margin-right:15px; margin-left:15px">
                        <img v-if="comment.pic==null" src="@/assets/icon.png" style="width:30px; height : 30px;" alt="">
                        <img v-if="comment.pic!=null" :src="comment.pic" style="width:30px; height : 30px" alt="">
                        <!-- 사용자 닉네임 -->
                        <a style="color:gray; width:10%; display:inline; font-size:14px;" class="ml-1" @click="moveToMypage(comment.profileId)">{{comment.nickname}}</a>
                        <b-button variant="danger" style="float:right" v-if="comment.profileId==id" @click="deleteComment(comment)">삭제</b-button>
                        <b-button variant="success" style="float:right" class="mr-1" v-if="comment.profileId==id" v-b-toggle="String(comment.id)">수정</b-button>
                        <b-collapse :id="String(comment.id)" class="mt-2">
                            <div class="p-2" style="border-radius: 2em;">
                                <b-form-textarea placeholder="댓글을 입력하세요." size="sm" class="mr-1" style="display:inline;width:90%" v-model="comment.content"></b-form-textarea>
                                <b-button style="display:inline;width:9%;" @click="modifyComment(comment)">등록</b-button>
                            </div>
                        </b-collapse>

                    </div>
                    <div >
                        <b-row>
                            <b-col cols="1"></b-col>
                            <b-col cols="8" style="color:black; font-size:14px">{{comment.content}}</b-col>
                            <b-col cols="3" style="font-size:12px; text-align: center;">{{comment.createAt[0]}}.{{comment.createAt[1]}}.{{comment.createAt[2]}} {{comment.createAt[3]}}:{{comment.createAt[4]}}</b-col>
                        </b-row>
                    </div>
                    <hr>
                </div>
            </div>
            <div class="p-2" style="border-radius: 2em;" v-if="!finDeal">
                <!-- <img src="@/assets/comment.png" style="display:inline;width:7%" class="ml-1 mr-1"> -->
                <b-form-textarea placeholder="댓글을 입력하세요." size="sm" class="mr-1" style="display:inline;width:90%" v-model="comment"></b-form-textarea>
                <b-button style="display:inline;width:9%;" @click="registerComment">등록</b-button>
                <hr>
            </div>
        </div>
    </div>
</template>


<script>
// import axios from "axios"
// import ogs from "open-graph-scraper"
// import {ogs} from "@/ogs.js"
import {axios_contact} from "@/common.js"
export default {
    name: 'Articledetail',
    data() {
        return {
            articleNo : this.$route.params.articleno,
            slide: 0,
            sliding: null,
            article : null,
            comment : null,
            commentList : null,
            commentRerender : 0,
            id : this.$cookie.get("id"),
            inParty : false,
            bossId : null,
            isZzim : null,
            finDeal : false,
            mod : null,
            dateover:false,
            user : null,
        };
    },
    created() {
        axios_contact({
            method : "get",
            url : "/user/"+this.id,
        }).then(({data})=>{
            console.log(data)
            this.user = data.profile
        })

        // 이 부분은 글쓰기 부분에서 사진 필드에 바로 썸네일 사진 링크 넣어주기
        axios_contact({
            method : "get",
            url : "/article/"+this.articleNo,
        }).then(({data})=>{
            // const that = this;
            this.article = data.article;

            let today = new Date();   
            let year = today.getFullYear(); // 년도
            let month = today.getMonth() + 1;  // 월
            let date = today.getDate();  // 날짜
            let day = year+"-"+month+"-"+date
            let deadline = this.article.deadline[0]+"-"+this.article.deadline[1]+"-"+this.article.deadline[2]
            if(day > deadline){
                this.dateover = true
            }

            axios_contact({
                method : "get",
                url : "/party?partyId="+this.article.partyId,
            }).then(({data})=>{
                // console.log(data)
                this.party=data;
                let count = 0;
                for (let index = 0; index < data.length; index++) {
                    if(data[index].isConfirmed || data[index].isBoss){
                        count+=data[index].totalamount;
                    }
                }
                if(this.article.totalRecruitMember==count && data.length > 1){
                    this.finDeal=true;
                }
                for (let index = 0; index < data.length; index++) {
                    if(this.id == data[index].profileId){
                        this.inParty=true;
                    }
                    if(data[index].isBoss){
                        this.bossId = data[index].profileId;
                    }
                }
            })
        })

        axios_contact({
            method : "get",
            url : "/comment?articleId="+this.articleNo,
        }).then(({data})=>{
            // console.log(data)
            this.commentList=data.commentList;
            for (let index = 0; index < this.commentList.length; index++) {
                // console.log(this.commentList[index])
                let nickname;
                let pic;
                axios_contact({
                    method : "get",
                    url : "/user/"+this.commentList[index].profileId,
                }).then(({data})=>{
                    // console.log(data)
                    nickname = data.profile.nickname;
                    pic = data.profile.pic;
                    // console.log(nickname)
                    let np = {
                        nickname : nickname,
                        pic : pic
                    };
                    this.commentList[index] = Object.assign(this.commentList[index],np);
                    this.commentRerenderForce();
                    // console.log(this.commentList[index])
                })
            }
        })

        axios_contact({
            method : "get",
            url : "/zzim?articleId="+this.articleNo+"&profileId="+this.id,
        }).then((data)=>{
            if(data.data.zzim!=null){
                this.isZzim=true;
            }else{
                this.isZzim=false;
            }
        })
    },

    mounted() {
    },

    methods: {
        onSlideStart() {
            this.sliding = true
        },
        onSlideEnd() {
            this.sliding = false
        },
        requestParty(){
            this.$router.push("/request/"+this.articleNo).catch(()=>{});
        },
        toBoard(){
            this.$router.push("/board");
        },

        registerComment(){
            // console.log(this.comment+"등록해")
            axios_contact({
                method : "post",
                url : "/comment",
                data : {
                    "articleId": this.articleNo,
                    "content": this.comment,
                    "id": "null",
                    "profileId": this.$cookie.get("id"),
                },
            }).then(({data})=>{
                // console.log(data)
                data
                this.commentRerenderForce()
                this.$router.go();
            })
        },

        modifyComment(comment){
            console.log(comment)
            axios_contact({
                method : "put",
                url : "/comment",
                data : comment
            }).then(({data})=>{
                console.log(data)
                // data
                this.$router.go();
            })
        },

        deleteComment(comment){
            axios_contact({
                method : "delete",
                url : "/comment/"+comment.id,
            }).then(({data})=>{
                console.log(data);
                this.$router.go();
            })
        },
        commentRerenderForce(){
            this.commentRerender +=1;
        }, 

        moveToUserpage(){
            // console.log("이동해")
            this.$router.push("/mysaza/"+this.bossId).catch(()=>{});
        },

        moveToPartyDetail(){
            this.$router.push("/partydetail/"+this.article.partyId).catch(()=>{});
        },

        addZZim(){
            this.isZzim = true;
            axios_contact({
                method : "post",
                url : "/zzim",
                data : {
                    "profileid": this.id,
                    "articleid": this.articleNo,
                }
            }).then(({data})=>{
                data
                // console.log(data)
            })
        },
        deleteZZim(){
            this.isZzim = false;
            axios_contact({
                method : "delete",
                url : "/zzim",
                data : {
                    "profileid": this.id,
                    "articleid": this.articleNo,
                }
            }).then(({data})=>{
                data
                // console.log(data)
            })
        },

        deleteArticle(){
            axios_contact({
                method : "delete",
                url : "/article/"+this.articleNo,
            }).then(({data})=>{
                console.log(data)
                this.$router.push("/board")
            })
        },

        modifyArticle(){
            this.$router.push("/board/update/"+this.articleNo)
        },

        moveToMypage(id){
            this.$router.push("/mysaza/"+id)
        },
    },
};
</script>

<style scoped>
.btn-info{
    width : 8em;
    background-color: red ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: red;
}

.btn-warning{
    width : 8em;
    background-color: #F1A501 ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
}

.btn-primary{
    width : 8em;
    background-color: #61C5FE  ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #61C5FE ;
}

.btn-secondary{
    width : 8em;
    background-color: #C4C4C4     ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #C4C4C4 ;
    position: absolute;
}
.btn-success{
    width : 5em;
    background-color: #34a853      ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #34a853  ;
}
.btn-danger{
    width : 5em;
    background-color: #ec2f21      ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #ec2f21  ;
}

.finParty{
    font-weight: bold;   
    background-color: #ec2f21;
    font-size: 25px;
}
.dateOver{
    font-weight: bold;   
    background-color: #ec2f21;
    font-size: 25px;
}
</style>