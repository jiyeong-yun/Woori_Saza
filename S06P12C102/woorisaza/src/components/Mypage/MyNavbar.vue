<template>
  <div style="width:100%">
    <b-nav align="center" >
        <b-nav-item  :active="infoActive" @click="getInfo">정보수정</b-nav-item>
      <b-nav-item  :active="zzimActive" @click="getZzim">찜목록</b-nav-item>
      <b-nav-item  :active="qnaActive" @click="getqnaActive">나의 1:1 문의</b-nav-item>
      <b-nav-item  :active="reviewActive" @click="getreviewActive">내가 쓴 후기</b-nav-item>
    </b-nav>
  </div>
</template>

<script>
// import {axios_contact} from "@/common.js"
import { EventBus } from "@/event-bus.js"
export default {
    name: 'MyNavbar',
    components:{
    },
    data() {
        return {
            zzimActive:null,
            infoActive : "active",
            qnaActive: null,
            reviewActive:null,
        };
    },
    watch:{
    },
    created(){
    },
    mounted() {
    },

    methods: {
        getZzim(){
            this.zzimActive="active";
            this.infoActive=null;
            this.qnaActive=null;
            this.reviewActive=null;
            EventBus.$emit("selectComponent","Zzim");
        },
        getInfo(){
            this.zzimActive=null;
            this.infoActive="active";
            this.qnaActive=null;
            this.reviewActive=null;
            EventBus.$emit("selectComponent","Info");
        },
        getqnaActive(){
            this.zzimActive=null;
            this.infoActive=null;
            this.qnaActive="active";
            this.reviewActive=null;
            EventBus.$emit("selectComponent","Qna");
        },
        getreviewActive(){
            this.zzimActive=null;
            this.infoActive=null;
            this.qnaActive=null;
            this.reviewActive="active";
            EventBus.$emit("selectComponent","Review");
        },
    },
};
</script>

<style scoped>
.nav-link{
        color:black;
        font-size: 1em;
        margin-left: 0.2em;
        margin-right: 0.2em;
    }
.nav-link.active{
      color:#F45F60;
    }
</style>