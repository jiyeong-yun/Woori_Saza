<template>
    <div class="mt-3" style="background-color : #FFFDF2; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container v-if="windowWidth>=1000">
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5">
                                    <component :is="selectComponent"></component>
                                </div>
                            </div>
                        </b-col>
                        <b-col> </b-col>
                    </b-row>
                </b-container>

                <div :class="{box:true}" style="overflow-y:auto;" v-if="windowWidth<1000"> 
                    <div class="p-5">
                        <component :is="selectComponent"></component>
                    </div>
                </div>

                <!-- 채팅창 글쓰기 버튼 -->
                <div class="chat2" v-if="!chatShow&&(windowWidth<1000)">
                    <transition name="fade">
                        <div >
                            <ChatList @chatShowFromChild="chatOff"></ChatList>
                        </div>
                    </transition>
                </div>
                <div class="chat" v-if="!chatShow&&(windowWidth>=1000)">
                    <transition name="fade">
                        <div >
                            <ChatList @chatShowFromChild="chatOff"></ChatList>
                        </div>
                    </transition>
                </div>
                <div class="button" v-if="accesstoken!=null">
                    <div v-if="chatShow">
                        <transition name="fade">
                            <div>
                                <div>
                                    <b-button variant="secondary" @click="toArticleWrite">
                                        <img src="@/assets/write.png" alt="" style="width:80%">
                                    </b-button>
                                </div>
                                <div class="mt-2">
                                    <b-button variant="secondary" v-on:click="chatShow = !chatShow">
                                        <img src="@/assets/chat.png" alt="" style="width:80%">
                                    </b-button>
                                </div>
                            </div>
                        </transition>
                    </div>
                </div>
                <!-- 모바일버전 -->
                <!-- <Board></Board> -->
            </div>
    </div>
</template>

<script>
import Board from "@/views/ThisSaza/Board.vue";
import ArticleDetail from "@/views/ThisSaza/ArticleDetail.vue";
import ChatList from "@/components/ThisSaza/Chat/ChatList.vue";
import ArticleWrite from "@/views/ThisSaza/ArticleWrite.vue";

export default {
    name: 'Thissaza',
    components : {
        Board,
        ArticleDetail,
        ChatList,
        ArticleWrite,
    },
    data() {
        return {
            chatShow: true,
            selectComponent : "Board",
            accesstoken : null,
            windowWidth: window.innerWidth
        };
    },
    created(){
        this.accesstoken = this.$cookie.get("accesstoken");
        if(this.$route.params.articleno==null){
            this.selectComponent = "Board";
        }
        else if(this.$route.params.articleno=="write"){
            this.selectComponent = "ArticleWrite";
        }
        else{
            this.selectComponent = "ArticleDetail";
        }
    },
    computed:{
        getArticlNo(){
            // return this.$store.getters.getArticleNo;
            return this.$route.params.articleno;
        }
    },
    watch:{
        getArticlNo(val){
            if(val==null){
                this.selectComponent = "Board";
            }
            else if(val=="write"){
                this.selectComponent = "ArticleWrite";
            }
            else{
                this.selectComponent = "ArticleDetail";
            }
        },
    },
    mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
        }
    },

    methods: {
        chatOff(s){
            this.chatShow=s;
        },
        toArticleWrite(){
            this.selectComponent="ArticleWrite";
            this.$router.push("/board/write").catch(()=>{});;
        }
    },
};
</script>

<style scoped>
    .box{
        background-color:white; 
        box-shadow: 0px 0px 5px 0.1px grey; 
        border-radius: 0.5em;
        width:100%;
        height:100%;
    }
    .box::-webkit-scrollbar {
        display: none; /* Chrome, Safari, Opera*/
    }

    .btn-secondary{
    width : 8em;
    height: 8em;
    background-color: #F1A501 ;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 50%;
    }
    .button {
        position: fixed;
        top:75%;
        left: 80%;
    }
    .chat {
        position: fixed;
        bottom: 5%;
        left: 60%;
        width: 400px;
        height: 600px;
    }

    .chat2 {
        position: fixed;
        bottom: 5%;
        left: 0%;
        width: 300px;
        height: 500px;
    }
    
    .fade-enter-active, .fade-leave-active {
        transition: opacity 0.5s;
    }
    .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
        opacity: 0;
    }


</style>