<template>
    <div class="mb-2">
        <!-- {{room}} -->
        <b-container class="bv-example-row">
            <b-row @click="clickRoom" style="cursor:pointer">
                <b-col cols="2">
                    <b-img  :src="data" v-bind="mainProps" rounded="circle" alt="Circle image" thumbnail ></b-img>
                </b-col>
                <b-col cols="10">
                    <b-col sm="12">
                        <!-- 채팅방 이름 (파티 제목) -->
                        <div>{{roomName}}</div>
                        <b-row>
                            <b-col sm="8" style="font-size:11px">
                                <!-- 채팅방 마지막 채팅 -->
                                {{lastChat}}
                            </b-col>
                            <b-col sm="4" style="text-align:right">
                                <div style="font-size:10px">
                                    <div v-if="this.lastLen >= 0">
                                        {{room.msgList[lastLen].time}}
                                    </div>
                                </div>
                            </b-col>
                        </b-row>
                    </b-col>
                </b-col>
            </b-row>

        </b-container>
        <hr>
    </div>
</template>

<script>
import Identicon from "identicon.js"
import { EventBus } from "@/event-bus.js"

export default {
    name: 'Chatpreview',
    props:["room"],
    data() {
        return {
            mainProps: { blank: false, blankColor: '#777', width: 50, height: 50, class: 'm1' },
            base64 : btoa(this.room.toString()+this.room.toString()+this.room.toString()+this.room.toString()+this.room.toString()+this.room.toString()+this.room.toString()+this.room.toString()+this.room.toString()+this.room.toString()),
            data : "",
            options : {
                foreground: [this.room%256,(this.room+50)%256,(this.room+120)%256,(this.room+200)%256],               // rgba black
                background: [255, 255, 255, 255],         // rgba white
                margin: 0.2,                              // 20% margin
                size: 50,                                // 420px square
                format: 'png'                             // use SVG instead of PNG
            },
            lastLen : this.room.msgList.length-1,
            roomName:"",
            lastChat : "",
        };
    },

    created(){
        // 채팅방 제목 길이 조절
        if(this.room.name.length > 20){
            this.roomName = this.room.name.substring(0,14)+"..."
        }
        else{
            this.roomName = this.room.name
        }

        // 채팅방 마지막 대화 길이 조절
        if(this.lastLen >= 0){
            if(this.room.msgList[this.lastLen].content.length > 20){
                this.lastChat = this.room.msgList[this.lastLen].content.substring(0,14)+"..."
            }else{
                this.lastChat = this.room.msgList[this.lastLen].content
            }
        }else{
            this.lastChat = "대화가 시작되지 않은 방입니다."
        }

    },

    mounted() {
        this.data="data:image/png;base64,"+new Identicon(this.base64,this.options).toString()
    },

    methods: {
        clickRoom(){
            EventBus.$emit("selectRoom",this.room.id);
        }
    },
};
</script>

<style scoped>
.col-2{
    padding-right: 0;
    padding-left: 0;
}
.col-10{
    padding-right: 0;
    padding-left: 0;
}

</style>