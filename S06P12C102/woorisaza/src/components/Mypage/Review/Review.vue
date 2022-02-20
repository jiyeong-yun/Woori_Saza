<template>
    <div>
        <!-- 내가 쓴 후기 -->
        <b-container>
          <div class="box mt-3 p-3" >
            <b-row>
                <b-col cols="8">
                    <div>
                        <div style="font-size:1.1em;font-weight:bold;" >{{review.toUser}}</div>
                        <div style="font-size:0.9em">{{review.content}}</div>
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-3">
                        <div style="font-size:1em;font-weight:bold;">{{review.date[0]}}.{{review.date[1]}}.{{review.date[2]}}</div>
                    </div>
                </b-col>
                
            </b-row>
            <div style="text-align:right">
              <b-button variant="danger" class="ml-3" style="width:15%;display:inline" @click="deleteReview">삭제</b-button>
              <!-- <b-button variant="warning" class="ml-3" style="width:15%;display:inline">수정</b-button> -->
            </div>
          </div>
        </b-container>
        
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
export default {
    name: 'Review',
    props:["review"],
    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        deleteReview(){
            // console.log("진짜 삭제함?")
            axios_contact({
                method : "delete",
                url : "/review/"+this.review.id
            }).then(({data})=>{
                data
                this.$router.go();
            })
        }
    },
};
</script>

<style scoped>
.btn-warning{
    width : 10em;
    background-color: #F1A501 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
}
.btn-danger{
    width : 10em;
    background-color: #FF0000 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #FF0000;
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
.box{
    border-radius:1em;
    background-color: #F6F9FB;
    box-shadow: 0.1em 0.1em gray;
}
</style>