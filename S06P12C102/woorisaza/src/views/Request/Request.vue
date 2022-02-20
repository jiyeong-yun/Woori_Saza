<template>
    <div class="mt-3" style="background-color : #F6F9FB ; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container >
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;" v-if="windowWidth>=1000">
                                <div class="p-5">
                                    <component v-if="!fin" :is="selectComponent" :chooseNum="chooseNum"></component>
                                    <Finish v-if="fin" :chooseNum="chooseNum"></Finish>
                                </div>
                            </div>
                        </b-col>
                        <b-col>
                        </b-col>
                    </b-row>
                </b-container>
                <div :class="{box:true}" style="overflow-y:auto;" v-if="windowWidth<1000">
                    <div class="p-5">
                        <component v-if="!fin" :is="selectComponent" :chooseNum="chooseNum"></component>
                        <Finish v-if="fin" :chooseNum="chooseNum"></Finish>
                    </div>
                </div>
            </div>
    </div>
</template>

<script>
import RequestDetail from "@/components/Request/RequestDetail.vue";
import Bill from "@/components/Request/Bill.vue";
import Finish from "@/components/Request/Finish.vue";
import {EventBus} from "@/event-bus.js"
export default {
    name: 'Request',
    components : {
        RequestDetail,
        Bill,
        Finish,
    },
    data() {
        return {
            fin : false,
            selectComponent : "RequestDetail",
            chooseNum : null,
            windowWidth: window.innerWidth
        };
    },
    created(){
        EventBus.$on("chooseNum", chooseNum =>{
            this.chooseNum = chooseNum;
        })
        EventBus.$on("fin", fin =>{
            this.fin = fin;
        })
    },
    computed:{
        getChooseNum(){
            return this.chooseNum;
        },
        
    },
    watch:{
        getChooseNum(val){
            if(val!=null){
                this.selectComponent="Bill"
            }
        }
    },
    mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
        }
    },

    methods: {
        
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
</style>