<template>
    <div style="">
        <div>
            <b-navbar toggleable="lg" type="white" >
                <b-navbar-nav>
                    <img src="@/assets/icon.png" style="width:70px">
                </b-navbar-nav>
                <b-navbar-nav>
                <img src="@/assets/name.png" style="width:120px; cursor:pointer" @click="toHome" >
                </b-navbar-nav>
                
                    <b-navbar-nav class="ml-auto">
                        <!-- 로그인-->
                        <div v-if="accesstoken==null">
                            <b-button pill style="background-color:#F1A501;border:0;" @click="$bvModal.show('signlogin')">로그인 | 회원가입</b-button>  &nbsp; &nbsp;
                            <!-- <b-button pill style="background-color:#F1A501;border:0;" @click="$bvModal.show('signlogin')">회원가입</b-button>&nbsp; &nbsp; -->
                            <b-modal id="signlogin" hide-footer size="sm">
                                <template #modal-title>
                                로그인
                                </template>
                                <div class="d-block text-center">
                                    <Kakaologin></Kakaologin>
                                </div>
                            </b-modal>
                        </div>
                        
                        <!-- 마이페이지 -->
                        <div v-if="accesstoken!=null">
                            <b-nav-item-dropdown right>
                            <template #button-content>
                                <em><img src="@/assets/saza.png" style="width:40px"></em>
                            </template>
                            <b-dropdown-item router-link to="/admin" v-if="user!=null && user.isAdmin">관리자</b-dropdown-item>
                            <b-dropdown-item router-link to="/mypage">마이페이지</b-dropdown-item>
                            <b-dropdown-item router-link to="/" @click="logout">로그아웃</b-dropdown-item>
                            </b-nav-item-dropdown>
                        </div>
                    </b-navbar-nav>
            </b-navbar>
        </div>
        <div>
            <div>
                <b-nav align="center" >
                    <b-nav-item router-link to="/" :active="homeActive" @click="getHomeActive">홈</b-nav-item>
                    <b-nav-item router-link to="/board" :active="thisSazaActive" @click="getThisSazaActive">이거사자</b-nav-item>
                    <b-nav-item router-link :to="mysazaUrl" :active="mySazaActive" @click="getMySazaActive">마이사자</b-nav-item>
                    <b-nav-item router-link to="/guide" :active="guideActive" @click="getGuideActive">가이드</b-nav-item>
                </b-nav>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios"
import {axios_contact} from "@/common.js"
import Kakaologin from "@/components/layout/kakaoLogin.vue"

export default {
    name: 'Navbar',
    components:{
        Kakaologin,
    },
    data() {
        return {
            homeActive:"active",
            thisSazaActive : null,
            mySazaActive: null,
            guideActive:null,
            accesstoken : null,
            data:{
                    grant_type : "authorization_code",
                    client_id : "067178783202c62976d9ac82175e67cd",
                    redirect_uri : "http://localhost:8080/",
                    // redirect_uri : "http://i6c102.p.ssafy.io/", 
                    code : this.$route.query.code,
            },
            queryString : null,
            mysazaUrl : null,
            active : this.$cookie.get("active"),
            id : this.$cookie.get("id"),
            user : null,
        };
    },
    watch:{
    },
    created(){
        this.accesstoken = this.$cookie.get("accesstoken");
        this.mysazaUrl = "/mysaza/"+this.$cookie.get("id");

        console.log(this.active)
        if(this.active == "homeActive"){
            this.getHomeActive()
        }
        else if(this.active == "thisSazaActive"){
            this.getThisSazaActive()
        }
        else if(this.active == "mySazaActive"){
            this.getMySazaActive()
        }
        else if(this.active == "guideActive"){
            this.getGuideActive()
        }

        axios_contact({
            method : "get",
            url : "/user/"+this.id
        }).then(({data})=>{
            this.user = data.profile
        })
    },
    mounted() {
        this.getKakaoQuery();
        this.getKakaoToken();
    },

    methods: {
        getHomeActive(){
            this.homeActive="active";
            this.thisSazaActive=null;
            this.mySazaActive=null;
            this.guideActive=null;
            this.$cookie.set("active","homeActive", 1);
        },
        getThisSazaActive(){
            this.homeActive=null;
            this.thisSazaActive="active";
            this.mySazaActive=null;
            this.guideActive=null;
            this.$cookie.set("active","thisSazaActive", 1);
        },
        getMySazaActive(){
            this.homeActive=null;
            this.thisSazaActive=null;
            this.mySazaActive="active";
            this.guideActive=null;
            this.$cookie.set("active","mySazaActive", 1);
        },
        getGuideActive(){
            this.homeActive=null;
            this.thisSazaActive=null;
            this.mySazaActive=null;
            this.guideActive="active";
            this.$cookie.set("active","guideActive", 1);
        },

        getKakaoQuery(){
            if(this.data.code!=null){
            this.queryString = Object.keys(this.data)
            .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(this.data[k]))
            .join('&');
            }
        },
        getKakaoToken(){
            const that = this;
            if(this.queryString!=null){
                axios({
                    method : "post",
                    url : "https://kauth.kakao.com/oauth/token",
                    headers : {
                        "Content-type" : "application/x-www-form-urlencoded;charset=utf-8",
                    },
                    data: this.queryString,
                }).then(({data})=>{
                    // console.log(data)
                    const token = data;
                    // this.accesstoken = data.access_token;
                    // this.$cookie.set("accesstoken",this.access_token, 1);
                    // console.log(this.access_token)
                    window.Kakao.Auth.setAccessToken(data.access_token);
                    window.Kakao.API.request({
                        url: '/v2/user/me',
                        success: function(data) {
                            // console.log(data);
                            // console.log(that.data.code)
                            axios_contact({
                                method : "post",
                                url : "/user/login",
                                data : {
                                    authid : data.id,
                                },
                            }).then(({data})=>{
                                if(data.profile==null){
                                    // console.log("회원가입하자")
                                    // console.log(token.access_token)
                                    that.$cookie.set("Raccesstoken",token.access_token, 1);
                                    that.$router.push("/register");
                                }
                                else{
                                    // console.log("로그인하자")
                                    // console.log(data)
                                    that.accesstoken = token.access_token;
                                    that.$cookie.set("accesstoken",token.access_token, 1);
                                    that.$cookie.set("id",data.profile.id, 1);
                                    that.mysazaUrl = "/mysaza/"+that.$cookie.get("id");
                                }
                            })
                        },
                        fail: function(error) {
                            console.log(error);
                        }
                    });
                })
            }
        },
        logout(){
            this.$cookie.delete("Raccesstoken");
            this.$cookie.delete("accesstoken");
            this.$cookie.delete("id");
            this.$router.go();
        },
        toHome(){
            this.$router.push("/");
            this.getHomeActive();
        },
    },
};
</script>

<style scoped>

.nav-link{
        color:black;
        font-size: 20px;
        margin-left: 15px;
        margin-right: 15px;
    }
.nav-link.active{
      color:#F1A501;
    }

</style>