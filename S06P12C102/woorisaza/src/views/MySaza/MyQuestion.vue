<template>
    <div class="mt-3" style="background-color : #F6FBF6; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container v-if="windowWidth>=1000">
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;" v-if="qna==null">
                                <div class="p-5">
                                    <h2>1:1 문의</h2>
                                    <div class="my-5">
                                      <b-row>       
                                        <b-col cols="2">
                                            <div>
                                                
                                            </div>
                                        </b-col>
                                        <b-col cols="8">
                                            <div class="my-3">
                                                <p>문의 유형</p>
                                                <b-form-select v-model="category" :options="categories" size="sm" style="width:60%"></b-form-select>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 제목</p>
                                                <b-form-textarea
                                                  id="title"
                                                  v-model="title"
                                                  size="sm"
                                                  placeholder="제목을 입력하세요"
                                                ></b-form-textarea>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 내용</p>
                                                <b-form-textarea
                                                  id="context"
                                                  placeholder="내용을 입력하세요"
                                                  v-model="content"
                                                  rows="8"
                                                ></b-form-textarea>
                                            </div>
                                            
                                        </b-col>    
                                        <b-col cols="2">
                                            <div>
                                            </div>
                                        </b-col>  
                                      </b-row>                                        
                                    </div>
                                    <div class="mt-5" style="text-align:center">
                                        <!-- 취소 완료 버튼 -->
                                        <b-button variant="secondary" class="mr-3" @click="cancelQuestion">작성 취소</b-button>
                                        <b-button variant="warning" class="ml-3" @click="createQuestion">작성 완료</b-button>
                                    </div>
                                </div>
                            </div>




                            <div :class="{box:true}" style="overflow-y:auto;" v-if="qna!=null">
                                <div class="p-5">
                                    <h2>1:1 문의</h2>
                                    <div class="my-5">
                                      <b-row>       
                                        <b-col cols="2">
                                            <div>
                                                
                                            </div>
                                        </b-col>
                                        <b-col cols="8">
                                            <div class="my-3">
                                                <p>문의 유형</p>
                                                <b-form-select v-model="qna.category" :options="categories" size="sm" style="width:60%" disabled></b-form-select>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 제목</p>
                                                <b-form-textarea
                                                  id="title"
                                                  v-model="qna.title"
                                                  size="sm"
                                                  placeholder="제목을 입력하세요"
                                                  disabled
                                                ></b-form-textarea>
                                            </div>
                                            <div class="my-3">
                                                <p>문의 내용</p>
                                                <b-form-textarea
                                                  id="context"
                                                  placeholder="내용을 입력하세요"
                                                  v-model="qna.content"
                                                  rows="8"
                                                  disabled
                                                ></b-form-textarea>
                                            </div>
                                            <div>
                                                <p>답변 내용</p>
                                                <b-form-textarea
                                                  id="context"
                                                  placeholder="내용을 입력하세요"
                                                  v-model="qna.comment"
                                                  rows="8"
                                                ></b-form-textarea>
                                            </div>
                                        </b-col>    
                                        <b-col cols="2">
                                            <div>
                                            </div>
                                        </b-col>  
                                      </b-row>                                        
                                    </div>
                                    <div class="mt-5" style="text-align:center">
                                        <!-- 취소 완료 버튼 -->
                                        <b-button variant="secondary" class="mr-3" @click="cancelAnswer">답변 취소</b-button>
                                        <b-button variant="warning" class="ml-3" @click="createAnswer">답변 완료</b-button>
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
                                <div>
                                    
                                </div>
                            </b-col>
                            <b-col cols="8">
                                <div class="my-3">
                                    <p>문의 유형</p>
                                    <b-form-select v-model="category" :options="categories" size="sm" style="width:60%"></b-form-select>
                                </div>
                                <div class="my-3">
                                    <p>문의 제목</p>
                                    <b-form-textarea
                                        id="title"
                                        v-model="title"
                                        size="sm"
                                        placeholder="제목을 입력하세요"
                                    ></b-form-textarea>
                                </div>
                                <div class="my-3">
                                    <p>문의 내용</p>
                                    <b-form-textarea
                                        id="context"
                                        placeholder="내용을 입력하세요"
                                        v-model="content"
                                        rows="8"
                                    ></b-form-textarea>
                                </div>
                                
                            </b-col>    
                            <b-col cols="2">
                                <div>
                                </div>
                            </b-col>  
                            </b-row>                                        
                        </div>
                        <div class="mt-5" style="text-align:center">
                            <!-- 취소 완료 버튼 -->
                            <b-button variant="secondary" class="mr-3" @click="cancelQuestion">작성 취소</b-button>
                            <b-button variant="warning" class="ml-3" @click="createQuestion">작성 완료</b-button>
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
  name: 'MyQuestion',
  
  data() {
        return {
            category : null,
            categories : [
                {value:null, text:"카테고리를 선택하세요."},
                {value:"팀원이 잠수 탔어요.", text:"팀원이 잠수 탔어요."},
                {value:"물건을 못 받았어요.", text:"물건을 못 받았어요."},
                {value:"ETC", text:"기타"},
            ],
            title : null,
            content : null,
            profileId : this.$cookie.get('id'),
            pic : [],
            windowWidth: window.innerWidth,
            qnaid : this.$route.params.qnaid,
            qna : null,
        };
    },
    created() {
        if(this.qnaid!="null"){
            axios_contact({
                method : "get",
                url : "/qna?qnaId="+this.qnaid
            }).then(({data})=>{
                // console.log(data)
                this.qna=data.qnaDetail
            })
        }
    },
    mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
        }
    },
  methods: {
    cancelQuestion(){
        this.$router.push("/mypage")
    },
    createQuestion(){
        axios_contact({
            method : "post",
            url : "/qna",
            data : {
                "category": this.category,
                "comment": "",
                "content": this.content,
                "id": "",
                "pic": this.pic,
                "profileId": this.profileId,
                "title": this.title
            }
        }).then(({data})=>{
            data
            this.$router.push("/mypage")
        })
    },

    cancelAnswer(){
        this.$router.push("/admin")
    },
    createAnswer(){
        console.log(this.qna)
        axios_contact({
            method : "put",
            url : "/qna/admin",
            data : this.qna
        }).then(({data})=>{
            console.log(data)
            this.$router.push("/admin")
        })
    },
  }
}
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
    .btn-warning{
    width : 10em;
    background-color: #F1A501 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
    }

    .btn-secondary{
    width : 10em;
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