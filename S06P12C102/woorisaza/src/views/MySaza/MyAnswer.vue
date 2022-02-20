<template>
  <div class="mt-3" style="background-color : #F6FBF6; width:100%;height:100%" v-if="qna!=null">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container v-if="windowWidth>=1000">
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5">
                                    <h2>1:1 문의</h2>
                                    <div class="my-5">
                                      <b-row>       
                                        <b-col cols="2">
                                            
                                        </b-col>
                                        <b-col cols="8">
                                            <div class="my-3">
                                                <p>문의 유형</p>
                                                <div class="box mt-3 p-3" style="background-color:#F8FAFC">
                                                {{qna.category}}
                                                </div>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 제목</p>
                                                <div class="box mt-3 p-3" style="background-color:#F8FAFC">
                                                {{qna.title}}
                                                </div>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 내용</p>
                                                <div class="box mt-3 p-3" style="background-color:#F8FAFC;height:5rem">
                                                {{qna.content}}
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <p>답변 내용</p>
                                                <div class="box mt-3 p-3" style="background-color:#FFF8F8;height:5rem">
                                                {{qna.comment}}
                                                </div>
                                            </div>
                                        </b-col>    
                                        <b-col cols="2">
                                            <div>
                                                
                                            </div>
                                        </b-col>  
                                      </b-row>  
                                    </div>
                                </div>
                            </div>
                        </b-col>
                        <b-col>
                          
                        </b-col>
                        
                    </b-row>
                    
                </b-container>
                <div :class="{box:true}" style="overflow-y:auto;" v-if="windowWidth<1000">
                    <div class="p-5">
                        <h2>1:1 문의</h2>
                        <div class="my-5">
                            <b-row>       
                            <b-col cols="2">
                                
                            </b-col>
                            <b-col cols="8">
                                <div class="my-3">
                                    <p>문의 유형</p>
                                    <div class="box mt-3 p-3" style="background-color:#F8FAFC">
                                    {{qna.category}}
                                    </div>
                                </div>
                                <div class="my-3">
                                    <p>문의 제목</p>
                                    <div class="box mt-3 p-3" style="background-color:#F8FAFC">
                                    {{qna.title}}
                                    </div>
                                </div>
                                <div class="my-3">
                                    <p>문의 내용</p>
                                    <div class="box mt-3 p-3" style="background-color:#F8FAFC;height:5rem">
                                    {{qna.content}}
                                    </div>
                                </div>
                                <div class="mt-5">
                                    <p>답변 내용</p>
                                    <div class="box mt-3 p-3" style="background-color:#FFF8F8;height:5rem">
                                    {{qna.comment}}
                                    </div>
                                </div>
                            </b-col>    
                            <b-col cols="2">
                                <div>
                                    
                                </div>
                            </b-col>  
                            </b-row>  

                        </div>
                        <div class="mt-3" style="text-align:center">
                            <!-- 취소 완료 버튼 -->
                            <b-button variant="secondary" class="mr-5" @click="updateQuestion">문의 내용 수정</b-button>
                            <b-button variant="danger" class="ml-5" @click="deleteQuestion">문의 삭제</b-button>
                        </div>
                    </div>
                </div>
                <!-- 모바일버전 -->
                <!-- <Board></Board> -->
            </div>
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
export default {
  name: 'MyAnswer',
  data() {
        return {
            qnaid : this.$route.params.qnaid,
            qna : null,
            windowWidth: window.innerWidth,
        };
    },
    created() {
        axios_contact({
            method : "get",
            url : "/qna?qnaId="+this.qnaid
        }).then(({data})=>{
            // console.log(data)
            this.qna = data.qnaDetail
        })
    },
    mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
        }
    },
}
</script>

<style scoped>
    .box{
        font-size: 0.9em;
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
    .btn-danger{
    width : 12em;
    background-color: #FF0000 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
    }

    .btn-secondary{
    width : 12em;
    background-color: #C4C4C4 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #C4C4C4;
    border-radius: 2em;
    }
    .photo{
    background-color: white;
    width:180px;
    height: 180px;
    border: 1px solid black;
}


</style>