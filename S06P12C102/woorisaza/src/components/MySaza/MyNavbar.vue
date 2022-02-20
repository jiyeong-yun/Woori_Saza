<template>
  <div style="width:100%">
    <b-nav align="center" >
      <b-nav-item  :active="memberActive" @click="getMemberOfParty">참가한 파티</b-nav-item>
      <b-nav-item  :active="bossActive" @click="getBossOfParty">개설한 파티</b-nav-item>
      <b-nav-item  :active="commentActive" @click="getcommentActive">댓글</b-nav-item>
      <b-nav-item  :active="reviewActive" @click="getreviewActive">후기</b-nav-item>
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
            memberActive:"active",
            bossActive : null,
            commentActive: null,
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
        getMemberOfParty(){
            this.memberActive="active";
            this.bossActive=null;
            this.commentActive=null;
            this.reviewActive=null;
            EventBus.$emit("selectComponent","MemberOfParty");
        },
        getBossOfParty(){
            this.memberActive=null;
            this.bossActive="active";
            this.commentActive=null;
            this.reviewActive=null;
            EventBus.$emit("selectComponent","BossOfParty");
        },
        getcommentActive(){
            this.memberActive=null;
            this.bossActive=null;
            this.commentActive="active";
            this.reviewActive=null;
            EventBus.$emit("selectComponent","Comment");
        },
        getreviewActive(){
            this.memberActive=null;
            this.bossActive=null;
            this.commentActive=null;
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
        margin-left: 0.5em;
        margin-right: 0.5em;
    }
.nav-link.active{
      color:#F45F60;
    }
</style>