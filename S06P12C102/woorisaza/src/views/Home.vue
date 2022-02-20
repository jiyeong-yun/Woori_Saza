<template>
  <div class="home mt-3" style="text-align : center">
    <div style="background-color : #FFFDF2; width:100%">
      <div class="py-5 container">
        <b-container>
          <b-row align-v="center" class="my-5">
            <b-col cols="7">
              <img src="@/assets/home_1.png" style="width:100%; backgroun-size : cover">
            </b-col>
            <b-col cols="5">
              <div style="font-size:2vw">
                내가 원하는 물건을<br>
                이웃과 함께<br>
                저렴한 가격으로!<br>
              </div>
              <button :class="{p_button:true}" @click="moveToGuide" style="font-size:1.5vw">시작하기</button>
            </b-col>
          </b-row>
        </b-container>
      </div>
    </div>
    <div style="height:20vh">

    </div>
    <div style="background-color : #F6F9FB; width:100%">
      <div class="py-5 container">
        <b-container>
          <b-row align-v="center" class="my-5">
            <b-col cols="4" >
              <img src="@/assets/home_2.png" style="width:120%">
            </b-col>
            <b-col cols="8">
              <img src="@/assets/icon.png" style="width:10%">
              <div style="font-size:2vw">
                물건을 수령하면 대금이 전달되는<br>
                시스템으로 더욱 안전한 거래!<br>
              </div>
              
            </b-col>
          </b-row>
        </b-container>
      </div>
    </div>

    <div>
      <div class="py-5 container">
        <div style="font-size:3vw">어떻게 이용하나요?</div>
        <img src="@/assets/home_crown.png" style="width:10%">
        <div style="font-size:3vw">파티장</div>
      </div>
    </div>

    <div style="background-color : #F6FBF6 ; width:100%">
      <div class="py-5 container" >
        <b-container>
          <h1 class="my-5" style="font-size:3vw">파티 만들기</h1>
          <b-row align-v="center" class="my-5" style="font-size:1.2vw">
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_glass.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">원하는 상품 찾기</p>
              </div>
            </b-col>
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_link.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">상품 링크를 등록하고<br>날짜, 인원 설정하기</p>
              </div>
            </b-col>
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_partymember.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">파티원이 모두 모일때<br>까지 기다리기</p>
              </div>
            </b-col>
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_share.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">결제 후 약속 날짜에<br>약속 장소에서 물건 나누기</p>
              </div>
            </b-col>
            
          </b-row>
        </b-container>
      </div>
    </div>

    <div>
      <div class="py-5 container">
        <img src="@/assets/home_corn.png" style="width:10%">
        <h1 style="font-size:3vw">파티원</h1>
      </div>
    </div>

    <div style="background-color : #F6FBF6 ; width:100%">
      <div class="py-5 container">
        <b-container>
          <h1 class="my-5" style="font-size:3vw">파티 참여하기</h1>
          <b-row align-v="center" class="my-5" style="font-size:1.2vw">
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_glass.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">원하는 상품 찾기</p>
              </div>
            </b-col>
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_partyq.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">파티 참여 신청 후<br>결제하기</p>
              </div>
            </b-col>
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_partyboss.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">파티장이 구매신청<br>할 때까지 기다리기</p>
              </div>
            </b-col>
            <b-col cols="3">
              <img :class="{t_img:true}" src="@/assets/home_share.png" style="height:4.5em">
              <div :class="{d_card:true}">
                <p :class="{d_cardtext:true}">약속 날짜에<br>약속 장소에서 물건 받기</p>
              </div>
            </b-col>
            
          </b-row>
        </b-container>
      </div>
    </div>
    <div style="height:10vh">

    </div>
  </div>
</template>

<script>
// @ is an alias to /src
export default {
  name: "Home",
  components: {
  },
  methods:{
    moveToGuide(){
      this.$router.push("/guide")
    }
  },
};
</script>

<style  scoped>
    .p_button{
        text-align: center;
        background-color:#FFA3A4; 
        color:white;
        box-shadow: 0px 0.5px 3px 0.5px grey; 
        border-radius: 0.1em;
        border: 0px ;
        width:30%;
        margin: 1em;
        padding: 0.25em;
    }

    .d_card{
        display: table;
        background-color:white; 
        box-shadow: 0px 0px 5px 0.1px grey; 
        border-radius: 0.5em;
        width:95%;
        height:20vh;
    }
    .d_cardtext{
        display: table-cell;
        vertical-align: middle;
    }
    .t_img{
      position: relative;
      top: 2.5em;
    }
</style>