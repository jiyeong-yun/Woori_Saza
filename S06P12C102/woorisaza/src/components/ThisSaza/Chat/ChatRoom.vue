<template>
    <div class="mb-2">
        <!-- {{room}} -->
        <div class="title">
            <b-row>
                <b-col cols="1"><b-icon-chevron-left @click="backToList" style="cursor:pointer"></b-icon-chevron-left></b-col>
                <b-col cols="9">{{roomName}}</b-col>
                <b-col cols="2" style="text-align:right; color:#eb6927">{{roomCnt}}명</b-col>
            </b-row>
        </div><br><hr>
        <div class="chat" ref="chatMessages">
            <div v-for="(item, index) in roomChat" :key="index" class="mt-1">
                <!-- 내가 보낸거 -->
                <div v-if="item.sender == myName">
                    <b-row style="display:table">
                        <b-col style="vertical-align: bottom;text-align:right;display:table-cell">
                            {{ item.time }}
                        </b-col>
                        <b-col>
                            <div class="box">
                                {{ item.content }}
                            </div>
                        </b-col>
                    </b-row>
                    
                </div>
                <div v-if="item.sender != myName">
                    <b-row style="display:table">
                        <b-col>
                            {{item.sender}}
                            <div class="box2">
                                {{ item.content }}
                            </div>
                        </b-col>
                        <b-col style="vertical-align: bottom;text-align:left;display:table-cell">
                            {{ item.time }}
                        </b-col>
                    </b-row>
                </div>
            </div>
        </div>
        <div class="inputMessage">
            <b-form-input v-model="message" placeholder="" @keyup.enter="sendMessage" style="width : 90%; display:inline" class="mr-1" @click="scrollDown"></b-form-input>
            <img src="@/assets/sendMessage.png" alt="" style="width : 30px" @click="sendMessage">
        </div>
    </div>
</template>


<script>
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';
import {axios_contact} from "@/common.js"

export default {
    name: 'ChatRoom',
    props:["roomId"],
    data() {
        return {
            profileId : this.$cookie.get("id"),
            roomName:null,
            roomCnt:null,
            roomChat:[],
            myName:"",
            message:"",

            stompClient:null,
        };
    },
    watch : {
        roomChat(){
            const that = this;
            setTimeout(function() {
                that.scrollDown();
            }, 100);
            
        }
    },
    created(){
        axios_contact({
            method : "get",
            url : "/user/"+this.profileId,
        }).then(({data})=>{
            // console.log(data.profile.nickname)
            this.myName = data.profile.nickname;
            // 소켓 연결
            this.connect();
        })
        
        axios_contact({
            method : "get",
            url : "/chat/room/enter/"+this.roomId,
        }).then(({data})=>{
            // console.log(data);
            // 채팅방 제목 길이 조절
            if(data.chatRoom.name.length > 20){
                this.roomName = data.chatRoom.name.substring(0,14)+"..."
            }
            else{
                this.roomName = data.chatRoom.name
            }

            this.roomChat = data.chatRoom.msgList;
            this.roomCnt = data.chatRoom.count;
        })
    },

    mounted() {
        this.scrollDown()
    },

    methods: {
        scrollDown(){
            console.log(this.$refs.chatMessages)
            this.$refs.chatMessages.scrollTo({top : this.$refs.chatMessages.scrollHeight, behavior : 'smooth'})
            console.log(11)
        },
        sendMessage: function() {
            this.stompClient.send("/pub/chat/message", {}, JSON.stringify({type:'CHAT', content:this.message, roomId:this.roomId, sender:this.myName}));
            this.message = '';
        },
        recvMessage: function(recv) {
            // console.log(recv);
            this.roomChat.push({"sender":recv.sender,"content":recv.content,"time":recv.time})
        },
        connect(){
            const serverURL = "http://i6c102.p.ssafy.io:8080/ws-stomp";
            // const serverURL = "http://127.0.0.1:8080/ws-stomp";
            let socket = new SockJS(serverURL);
            // let socket = new SockJS("/ws-stomp");
            // const that = this;
            this.stompClient = Stomp.over(socket);
            this.reconnect = 0;
            
            this.stompClient.connect({}, (frame) => {
                // console.log("Connected: " + frame);
                frame
                this.stompClient.subscribe("/sub/chat/room/"+this.roomId, (message) => {
                    var recv = JSON.parse(message.body);
                    // console.log("구독으로 받은 메세지: " +recv.message);
                    this.recvMessage(recv);
                });
            }, (error) => {
                console.log("Fail: " + error);
                if(this.reconnect++ < 5) {
                    setTimeout(function() {
                        // console.log("connection reconnect");
                        let socket = new SockJS(serverURL);
                        this.stompClient = Stomp.over(socket);
                        this.connect();
                    },10*1000);
                }
            });
        },
        backToList(){
            console.log("뒤로가")
            this.$emit('bactToList',null);
        }
    },
};

</script>

<style scoped>
.col-3{
    padding-right: 0;
    padding-left: 0;
}
.col-6{
    padding-right: 0;
    padding-left: 0;
}
.col{
    max-width: 49%;
}

.chat{
    background-color:white; 
    padding-bottom: 10px;
    width:100%;
    height:440px;
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
    overflow: auto;
}
.chat::-webkit-scrollbar{ display:none; }

.title{
    position: fixed;
    width:380px;
}
.box{
    padding: 5px;
    background-color:white; 
    box-shadow: 0px 0px 5px 0.1px grey; 
    border-radius: 0.5em;
    width : 180px;
    height:100%;
}
.box2{
    padding: 5px;
    background-color:#FEEBB6; 
    box-shadow: 0px 0px 5px 0.1px #FEEBB6; 
    border-radius: 0.5em;
    width : 180px;
    height:100%;
}
</style>