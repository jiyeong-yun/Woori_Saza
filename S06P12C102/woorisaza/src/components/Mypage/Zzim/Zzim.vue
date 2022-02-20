<template>
    <div>
        <div v-if="article!=null">
            <b-row>
                <b-col cols="8">
                    <div>
                        <!-- <div :class="{statebox:true}">거래 완료</div> -->
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" @click="moveToDetail">{{articleName}}</div>
                        <div style="font-size:0.9em">기간: ~{{article.deadline[0]}}.{{article.deadline[1]}}.{{article.deadline[2]}}</div>
                        <div style="font-size:0.9em">모집인원: {{article.currentRecruitMember}}/{{article.totalRecruitMember}}</div>
                        
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:0.9em; text-decoration:line-through" class="mt-3">{{article.totalPrice}}원</div>
                        <div style="font-size:1.3em;font-weight:bold;">{{article.myPrice}}원</div>
                    </div>
                </b-col>
            </b-row>
            <hr>
        </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"

export default {
    name: 'Zzim',
    props:{
        zzim : Object,
    },
    data() {
        return {
            article : null,
            articleName:"",
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/article/"+this.zzim.articleId,
        }).then(({data})=>{
            this.article=data.article;

            // 게시글 제목 길이 조절
            if(this.article.title.length > 20){
                this.articleName = this.article.title.substring(0,14)+"..."
            }
            else{
                this.articleName = this.article.title
            }   
        })
    },

    mounted() {
        
    },

    methods: {
        moveToDetail(){
            console.log("상세보기 페이지로 이동")
            this.$router.push("/board/"+this.zzim.articleId)
        },
    },
};
</script>

<style scoped>
.statebox{
      color: white ;
      font-size: 0.7em;
      background-color: red ;
      border-radius: 0.5em ;
      padding: 0.2em ;
      text-align: center;
      width: 5em;
      margin-bottom: 0.5em;
}
</style>