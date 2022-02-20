<template>
    <div class="mt-3" style="background-color : #F6FBF6; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container v-if="windowWidth>=1000">
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5" v-if="id != 'null'">
                                    <!-- 신분증 -->
                                    <div v-if="this.id == this.$cookie.get('id')" style="text-align:center;" @click="moveToMypage">
                                        <my-profile style="cursor:pointer"/>
                                    </div>
                                    <div v-if="this.id != this.$cookie.get('id')" style="text-align:center;">
                                        <my-profile/>
                                    </div>
                                    <!-- 마이사자 네비게이션 바 -->
                                    <div>
                                        <my-navbar/>
                                    </div>
                                    <!-- 게시글 폼 -->
                                    <div class="mt-4">
                                        <component :is="selectComponent"> </component>
                                    </div>
                                </div>
                                <div class="p-5" v-if="id == 'null'">
                                    <div style="font-size : 1.5em">
                                        파티에 가입하고 <br>
                                        내가 필요한만큼만! 저렴한 가격에!<br>
                                        물건을 구매해보세요!<br>
                                    </div>
                                    로그인 후 사용해주세요.
                                    <div style="text-align:center">
                                        <img src="@/assets/fin.png" alt=""><br>
                                        <button :class="{p_button:true}" @click="$bvModal.show('signlogin')">로그인 | 회원가입</button>
                                    </div>
                                </div>
                            </div>
                        </b-col>
                        <b-col>
                        </b-col>
                    </b-row>
                </b-container>
                <!-- 모바일버전 -->
                <!-- <Board></Board> -->
                <div :class="{box:true}" style="overflow-y:auto;" v-if="windowWidth<1000">
                    <div class="p-5" v-if="id != 'null'">
                        <!-- 신분증 -->
                        <div v-if="this.id == this.$cookie.get('id')" style="text-align:center;" @click="moveToMypage">
                            <my-profile style="cursor:pointer"/>
                        </div>
                        <div v-if="this.id != this.$cookie.get('id')" style="text-align:center;">
                            <my-profile/>
                        </div>
                        <!-- 마이사자 네비게이션 바 -->
                        <div>
                            <my-navbar/>
                        </div>
                        <!-- 게시글 폼 -->
                        <div class="mt-4">
                            <component :is="selectComponent"> </component>
                        </div>
                    </div>
                    <div class="p-5" v-if="id == 'null'">
                        <div style="font-size : 1.5em">
                            파티에 가입하고 <br>
                            내가 필요한만큼만! 저렴한 가격에!<br>
                            물건을 구매해보세요!<br>
                        </div>
                        로그인 후 사용해주세요.
                        <div style="text-align:center">
                            <img src="@/assets/fin.png" alt=""><br>
                            <button :class="{p_button:true}" @click="$bvModal.show('signlogin')">로그인 | 회원가입</button>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</template>

<script>
import MyProfile from "@/views/MySaza/MyProfile.vue";
import MyNavbar from "@/components/MySaza/MyNavbar.vue";
import MemberOfParty from "@/components/MySaza/MemberOfParty/MemberOfParty.vue";
import BossOfParty from "@/components/MySaza/BossOfParty/BossOfParty.vue";
import Comment from "@/components/MySaza/Comment/Comments.vue";
import Review from "@/components/MySaza/Review/Reviews.vue";
import { EventBus } from "@/event-bus.js"
export default {
    name: 'Mysaza',
    components : {
        MyProfile,
        MyNavbar,
        MemberOfParty,
        BossOfParty,
        Comment,
        Review,
    },
    data() {
        return {
            selectComponent : "MemberOfParty",
            id : this.$route.params.id,
            windowWidth: window.innerWidth,
        };
    },
    created(){
        EventBus.$on("selectComponent",selectComponent=>{
            this.selectComponent=selectComponent;
        })
    },
    computed:{
    },
    watch:{
    },
    mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
        }
    },

    methods: {
       moveToMypage(){
           this.$router.push("/mypage").catch(()=>{});;
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
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
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
    .fixed {
        position: fixed;
        top:75%;
        
    }
.p_button{
        text-align: center;
        background-color:#F1A501; 
        color:white;
        box-shadow: 0px 0.5px 3px 0.5px grey; 
        border-radius: 0.1em;
        border: 0px ;
        width:50%;
        margin: 1em;
        padding: 0.25em;
    }
</style>