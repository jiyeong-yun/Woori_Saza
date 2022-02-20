<template>
  <div style="display:inline-block" v-if="profile != null">
    <div class="card mb-3 flex-row" style="font-size:1.1em;align-items:center;background-color:#FFF9EC; width:270px;" >
      <img v-if="profile.pic==null" src="@/assets/IDimage.png" style="width:80px;height:80px;background-color:white" alt="Card image" class="card-img-left p-1 ml-3">
      <img v-if="profile.pic!=null" :src="profile.pic" style="width:80px;height:80px;background-color:white" alt="Card image" class="card-img-left p-1 ml-3">
      
      <div class="card-body" style="text-align:left">
        <div style="font-size:0.8em" v-if="grade!=null">
          등급 : {{grade}}
        </div>
        <div>
          {{profile.nickname}}
        </div>
        <div style="font-size:0.8em">
          {{profile.address}}
        </div>
        <div style="text-align:right;font-size:0.8em">
          {{profile.joinDate[0]}}.{{profile.joinDate[1]}}.{{profile.joinDate[2]}} 가입
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import {axios_contact} from "@/common.js"
export default {
  name : "MyProfile",
  props : {
        userId : String,
    },
  data(){
    return {
      id : null,
      // this.$route.params.id!=null ? this.$route.params.id : this.$cookie.get("id"),
      profile : null,
      grade : null,
    }
  },
  
  created() {
    if(this.userId !=null){
      this.id = this.userId
    }else if(this.$route.params.id!=null){
      this.id = this.$route.params.id;
    }else{
      this.id = this.$cookie.get("id");
    }
    axios_contact({
      method : "get",
      url : "user/"+this.id,
    }).then(({data})=>{
      // console.log(data)
      this.profile = data.profile;
      let s = this.profile.score/this.profile.cnt;
      if(this.profile.cnt==0){
        this.grade = "B"
      }
      else if(s>4){
        this.grade = "S";
      }else if(s>3){
        this.grade="A"
      }else if(s>2){
        this.grade="B"
      }else if(s>1){
        this.grade="C"
      }else if(s>0){
        this.grade="F"
      }
    })
  },

  methods: {
    },
};
</script>

<style >

</style>