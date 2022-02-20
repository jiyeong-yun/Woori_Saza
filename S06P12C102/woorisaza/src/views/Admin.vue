<template>
    <div class="mt-3" style="width:100%;height:100%" v-if="user!=null">
        <div class="pt-5 pb-5">
            <b-container v-if="windowWidth>=1000">
                <b-row class="">
                    <b-col></b-col>
                    <b-col cols="7">
                        <div :class="{box:true}" style="overflow-y:auto;">
                            <div class="p-5" style="font-size : 1.5em; text-align:center" v-if="!user.isAdmin">
                                권한이 없습니다.
                                <div style="text-align:center">
                                    <img src="@/assets/fin.png" alt=""><br>
                                </div>
                            </div>
                            <div class="p-5" v-if="user.isAdmin">
                                <span class="title">
                                    관리자 페이지
                                </span>
                                <div class="mt-3">
                                    <b-button v-b-toggle.collapse-1 variant="primary">게시글 관리</b-button>
                                    <b-collapse id="collapse-1" class="mt-2 box p-2">
                                        <div>
                                            <b-row style="font-weight:bold">
                                                <b-col>작성자</b-col>
                                                <b-col>카테고리</b-col>
                                                <b-col>제목</b-col>
                                                <b-col></b-col>
                                            </b-row>
                                            <hr>
                                            <b-row v-for="(art, index) in articleList" :key="index" class="mt-1">
                                                <b-col>{{art.author}}</b-col>
                                                <b-col>{{art.category}}</b-col>
                                                <b-col style="cursor:pointer;color:blue" @click="moveToArticle(art)">{{art.title.substr(0,5)}}..</b-col>
                                                <b-col><b-button variant="danger" size="sm" @click="deletArticle(art)">삭제</b-button></b-col>
                                            </b-row>
                                        </div>
                                    </b-collapse>
                                </div>
                                <div class="mt-3">
                                    <b-button v-b-toggle.collapse-2 variant="warning">문의 관리</b-button>
                                    <b-collapse id="collapse-2" class="mt-2 box p-2">
                                        <div>
                                            <b-row style="font-weight:bold">
                                                <b-col>카테고리</b-col>
                                                <b-col>제목</b-col>
                                                <b-col></b-col>
                                            </b-row>
                                            <hr>
                                            <b-row v-for="(qna, index) in qnaList" :key="index" class="mt-1">
                                                <b-col>{{qna.category.substr(0,8)}}...</b-col>
                                                <b-col>{{qna.title.substr(0,7)}}...</b-col>
                                                <b-col>
                                                    <b-button variant="success" size="sm" @click="moveToQna(qna)">답변하기</b-button>
                                                </b-col>
                                            </b-row>
                                        </div>
                                    </b-collapse>
                                </div>
                            </div>
                        </div>
                    </b-col>
                    <b-col> </b-col>
                </b-row>
            </b-container>
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
export default {
    name: 'Admin',

    data() {
        return {
            windowWidth: window.innerWidth,
            id : this.$cookie.get("id"),
            user : null,
            articleList : null,
            qnaList : null,
        };
    },
    created() {
        axios_contact({
            method : "get",
            url : "/user/"+this.id
        }).then(({data})=>{
            this.user = data.profile
        })

        axios_contact({
            method : "get",
            url : "/article?profileId="+this.id
        }).then(({data})=>{
            // console.log(data)
            this.articleList = data.articleList
        })

        axios_contact({
            method : "get",
            url : "/qna/admin",
        }).then(({data})=>{
            // console.log(data)
            this.qnaList = data.qnaList
        })
    },
    mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
        }
    },

    methods: {
        moveToArticle(art){
            console.log(art)
            this.$router.push("/board/"+art.id)
        },
        deletArticle(art){
            console.log(art)
            axios_contact({
                method:"delete",
                url : "/article/"+art.id
            }).then(({data})=>{
                console.log(data)
                this.$router.go()
            })
        },
        moveToQna(qna){
            this.$router.push("/mypage/question/"+qna.id)
        },
    },
};
</script>

<style  scoped>
.box{
    background-color:white; 
    box-shadow: 0px 0px 5px 0.1px grey; 
    border-radius: 0.5em;
    width:100%;
    height:100%;
}
.title{
    font-size : 1.2em;
    background: linear-gradient(to top, #FBE1AB  50%, transparent 70%);
}
</style>