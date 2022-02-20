<template>
    <div>
        <div style="text-align:center" >
            <b-button @click="toMyQuestion" variant="warning" class="ml-3" style="width:15%;display:inline;font-size:0.8em">문의하기</b-button>
          </div>
        <!-- 내가 쓴 후기 -->
        <div v-for="(item, index) in QnaNo" :key="index">
            <Qna :Qna="item"></Qna>
        </div>
    </div>
</template>

<script>
import Qna from "@/components/Mypage/Qna/Qna.vue";
import {axios_contact} from "@/common.js"
export default {
    name: 'Qnas',
    components:{
        Qna,
    },
    data() {
        return {
            QnaNo:[],
            id : this.$cookie.get("id"),
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/qna/"+this.id,
        }).then(({data})=>{
            console.log(data)
            this.QnaNo = data.myQnaList
        })
    },
    mounted() {
        
    },

    methods: {
        toMyQuestion(){
           this.$router.push("/mypage/question/null").catch(()=>{});;
       },
    },
};
</script>

<style  scoped>
.btn-warning{
    width : 10em;
    background-color: #F1A501 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
}
</style>