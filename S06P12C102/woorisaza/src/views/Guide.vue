<template>
  <div class="mt-3" style="background-color : #FFFFFF; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container v-if="windowWidth>=1000">
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5">
                                  <div class="p-3">
                                    <h1 :class="{redcolor:true}">우리사자 가이드</h1>
                                    <br>
                                    <h6>우리사자는 원하는 물건을 <span :class="{redcolor:true}"> 더 싸게, 필요한 만큼</span> 구매할 수 있도록 하여<br>
                                    고객들의 금전적 부담을 줄여주고자 합니다.</h6>
                                  </div>
                                  <hr class="my-hr3">
                                  <details>
                                    <summary>
                                      <img src="@/assets/guide_rocket.png" style="width:10%; backgroun-size : cover">
                                      파티에 어떻게 참가하나요?
                                    </summary>
                                    <div :class="{graybox:true}">
                                      <p :class="{textindent1:true}">
                                        파티장: 원하는 물건의 링크를 등록하고 사람들을 모아보세요<br>
                                        기간, 인원수를 자유롭게 설정할 수 있습니다.
                                      </p>
                                      <p>파티원: 원하는 상품을 찾아 참여하기를 눌러 신청서를 작성하면 끝!</p>
                                    </div>
                                  </details>
                                  
                                  <details>
                                    <summary>
                                      <img src="@/assets/guide_cart.png" style="width:10%; backgroun-size : cover">
                                      파티장이 물건을 구매한 후에는 어떻게 진행되는거죠?
                                    </summary>
                                    <div :class="{graybox:true}">
                                      <p :class="{textindent2:true}">
                                        1. 파티원이 모두 모여 파티장이 물건 구매를 진행하게 되면<br>
                                        <b>구매 인증 폼</b>을 작성해야합니다.
                                      </p>
                                      <p :class="{textindent2:true}">
                                        2. 파티원들과 모두 모여 채팅을 통해 약속 장소와 시간을 정해보세요.<br>
                                        약속 날짜를 정했다면 <b>예상 수령일</b>을 꼭 등록해주세요
                                      </p>
                                    </div>
                                  </details>
                                  
                                  <details>
                                    <summary>
                                      <img src="@/assets/guide_membercall.png" style="width:10%; backgroun-size : cover">
                                      함께 구매한 파티원의 연락이 두절되면 어떡하나요?
                                    </summary>
                                    <div :class="{graybox:true}">
                                      <p>
                                        파티를 생성할 때 <b>위약금</b>을 설정해주세요<br>
                                        해당하는 위약금만큼의 금액은 보장받을 수 있습니다.
                                      </p>
                                    </div>
                                  </details>

                                  <details>
                                    <summary>
                                      <img src="@/assets/guide_penalty.png" style="width:10%; backgroun-size : cover">
                                      위약금은 어떤 기준으로 설정되나요?
                                    </summary>
                                    <div :class="{graybox:true}">
                                      <p>
                                        파티장이 파티를 생성할 때 위약금을 설정하게 됩니다. (0% ~ 100%)<br>
                                        해당 파티에 참가한다는 것은 해당 위약금에 동의한다는 뜻이니 참가<br>
                                        전 유의사항을 꼭 읽어주세요!
                                      </p>
                                      
                                      <p>
                                        파티원이 예상 수령일 이후 7일 이내에 구매확정을 누르지 않고, 물건<br>
                                        도 받아가지 않을 경우 파티장이 설정한 위약금만큼의 돈을 받을 수 없<br>
                                        습니다.
                                      </p>

                                      <p>
                                        파티장은 물건을 전달할 때 파티원이 <b>구매확정 버튼</b>을 누른 것을 확인<br>
                                        한 후 물건을 전달하도록 해주세요.
                                      </p>
                                    </div>
                                  </details>

                                  <details>
                                    <summary>
                                      <img src="@/assets/guide_bosscall.png" style="width:10%; backgroun-size : cover">
                                      파티장이 연락이 닿지 않으면 어떡하나요?
                                    </summary>
                                    <div :class="{graybox:true}">
                                      <p>
                                        파티원들이 결제한 대금은 저희 ‘우리사자’가 가지고 있다가<br>
                                        파티원들이 구매확정을 눌러야 파티장에게 전달되는 시스템입니다.<br>
                                        그렇기 때문에 이런 문제 발생 시 돈을 100% 환불받을 수 있으니<br>
                                        안심하세요! 
                                      </p>
                                      
                                      <p>
                                        혹시 물건을 받지 못하였다면 상세정보 페이지 내의 ‘물건을 못받았<br>
                                        어요’ 버튼을 눌러 문의해주세요.
                                      </p>
                                    </div>
                                  </details>

                                  <details>
                                    <summary>
                                      <img src="@/assets/guide_distance.png" style="width:10%; backgroun-size : cover">
                                      거리 표시 기준이 뭔가요?
                                    </summary>
                                    <div :class="{graybox:true}">
                                      <p>
                                        비회원의 경우 기본 주소 설정은 서울 시청입니다.<br>
                                        회원가입 후 자신의 주소를 설정하면 해당 주소로 변경됩니다.<br>
                                      </p>
                                      <p>
                                        구역 범위 필터링 기준은 <br> 
                                        걸어서 5분 내외 : 400m 내외 <br>
                                        걸어서 10분 내외: 400m ~ 800m <br>
                                        차타고 5분 내외: 800m ~ 2km <br>
                                        차타고 10분 내외: 2km ~ 4km
                                      </p>
                                    </div>
                                  </details>
                                </div>
                            </div>
                        </b-col>
                        <b-col></b-col>
                    </b-row>
                </b-container>
                <!-- 모바일버전 -->
                <!-- <Board></Board> -->
                <div :class="{box:true}" style="overflow-y:auto;" v-if="windowWidth<1000">
                  <div class="p-5">
                    <div class="p-3">
                      <h1 :class="{redcolor:true}">우리사자 가이드</h1>
                      <br>
                      <h6>우리사자는 원하는 물건을 <span :class="{redcolor:true}"> 더 싸게, 필요한 만큼</span> 구매할 수 있도록 하여<br>
                      고객들의 금전적 부담을 줄여주고자 합니다.</h6>
                    </div>
                    <hr class="my-hr3">
                    <details>
                      <summary>
                        <img src="@/assets/guide_rocket.png" style="width:10%; backgroun-size : cover">
                        파티에 어떻게 참가하나요?
                      </summary>
                      <div :class="{graybox:true}">
                        <p :class="{textindent1:true}">
                          파티장: 원하는 물건의 링크를 등록하고 사람들을 모아보세요<br>
                          기간, 인원수를 자유롭게 설정할 수 있습니다.
                        </p>
                        <p>파티원: 원하는 상품을 찾아 참여하기를 눌러 신청서를 작성하면 끝!</p>
                      </div>
                    </details>
                    
                    <details>
                      <summary>
                        <img src="@/assets/guide_cart.png" style="width:10%; backgroun-size : cover">
                        파티장이 물건을 구매한 후에는 어떻게 진행되는거죠?
                      </summary>
                      <div :class="{graybox:true}">
                        <p :class="{textindent2:true}">
                          1. 파티원이 모두 모여 파티장이 물건 구매를 진행하게 되면<br>
                          <b>구매 인증 폼</b>을 작성해야합니다.
                        </p>
                        <p :class="{textindent2:true}">
                          2. 파티원들과 모두 모여 채팅을 통해 약속 장소와 시간을 정해보세요.<br>
                          약속 날짜를 정했다면 <b>예상 수령일</b>을 꼭 등록해주세요
                        </p>
                      </div>
                    </details>
                    
                    <details>
                      <summary>
                        <img src="@/assets/guide_membercall.png" style="width:10%; backgroun-size : cover">
                        함께 구매한 파티원의 연락이 두절되면 어떡하나요?
                      </summary>
                      <div :class="{graybox:true}">
                        <p>
                          파티를 생성할 때 <b>위약금</b>을 설정해주세요<br>
                          해당하는 위약금만큼의 금액은 보장받을 수 있습니다.
                        </p>
                      </div>
                    </details>

                    <details>
                      <summary>
                        <img src="@/assets/guide_penalty.png" style="width:10%; backgroun-size : cover">
                        위약금은 어떤 기준으로 설정되나요?
                      </summary>
                      <div :class="{graybox:true}">
                        <p>
                          파티장이 파티를 생성할 때 위약금을 설정하게 됩니다. (0% ~ 100%)<br>
                          해당 파티에 참가한다는 것은 해당 위약금에 동의한다는 뜻이니 참가<br>
                          전 유의사항을 꼭 읽어주세요!
                        </p>
                        
                        <p>
                          파티원이 예상 수령일 이후 7일 이내에 구매확정을 누르지 않고, 물건<br>
                          도 받아가지 않을 경우 파티장이 설정한 위약금만큼의 돈을 받을 수 없<br>
                          습니다.
                        </p>

                        <p>
                          파티장은 물건을 전달할 때 파티원이 <b>구매확정 버튼</b>을 누른 것을 확인<br>
                          한 후 물건을 전달하도록 해주세요.
                        </p>
                      </div>
                    </details>

                    <details>
                      <summary>
                        <img src="@/assets/guide_bosscall.png" style="width:10%; backgroun-size : cover">
                        파티장이 연락이 닿지 않으면 어떡하나요?
                      </summary>
                      <div :class="{graybox:true}">
                        <p>
                          파티원들이 결제한 대금은 저희 ‘우리사자’가 가지고 있다가<br>
                          파티원들이 구매확정을 눌러야 파티장에게 전달되는 시스템입니다.<br>
                          그렇기 때문에 이런 문제 발생 시 돈을 100% 환불받을 수 있으니<br>
                          안심하세요! 
                        </p>
                        
                        <p>
                          혹시 물건을 받지 못하였다면 상세정보 페이지 내의 ‘물건을 못받았<br>
                          어요’ 버튼을 눌러 문의해주세요.
                        </p>
                      </div>
                    </details>

                    <details>
                      <summary>
                        <img src="@/assets/guide_distance.png" style="width:10%; backgroun-size : cover">
                        거리 표시 기준이 뭔가요?
                      </summary>
                      <div :class="{graybox:true}">
                        <p>
                          비회원의 경우 기본 주소 설정은 서울 시청입니다.<br>
                          회원가입 후 자신의 주소를 설정하면 해당 주소로 변경됩니다.<br>
                        </p>
                        <p>
                          구역 범위 필터링 기준은 <br> 
                          걸어서 5분 내외 : 400m 내외 <br>
                          걸어서 10분 내외: 400m ~ 800m <br>
                          차타고 5분 내외: 800m ~ 2km <br>
                          차타고 10분 내외: 2km ~ 4km
                        </p>
                      </div>
                    </details>
                  </div>
              </div>
            </div>
    </div>
</template>

<script>
// @ is an alias to /src
export default {
  name: "Guide",
  components: {
  },
  data() {
        return {
            windowWidth: window.innerWidth
        };
    },
  methods:{
    
  },
  mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
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
    details{
      margin-top: 2em;
    }
    .redcolor{
      color: #F45F60 ;
    }
    .graybox{
      position: relative;
      left: 13%;
      background-color:whitesmoke; 
      box-shadow: 0px 0px 2px 0.1px grey; 
      border-radius: 0.5em;
      width:87%;
      height:100%;
      margin-top: 1em;
      padding: 1.2em;
      -ms-overflow-style: none; /* IE and Edge */
      scrollbar-width: none; /* Firefox */
    }
    .textindent1{
      text-indent: -3.3em;
      margin-left: 3.3em;
    }
    .textindent2{
      text-indent: -1em;
      margin-left: 1em;
    }

    .my-hr3 {
    border: 0;
    height: 3px;
    background: #ccc;
    }
    
    p {
        font-size: 0.9em;
      }
</style>