<template>
    <div class="chat p-2">
            <div style="position: fixed; width:380px">
                <!-- 채팅창 헤더 -->
                <b-row>
                    <b-col sm="2"><img src="@/assets/saza.png" style="width:30px"></b-col>
                    <b-col sm="8" style="text-align:left; padding-left:0px; padding-top:5px">사자 채팅</b-col>
                    <b-col sm="2" style="text-align:right"><b-icon-x-circle @click="offChat" style="cursor:pointer"></b-icon-x-circle></b-col>
                </b-row>
            </div>

            <div class="pt-5">
                <!-- 채팅방 리스트 -->
                <div v-if="chatRoomId==null">
                    <!-- 검색창 -->
                    <div class="mt-2">
                        <b-form-input v-model="party" placeholder="검색"></b-form-input>
                    </div>
                    <hr>
                    <!-- 파티 채팅 리스트 -->
                    <div style="overflow-y:scroll" class="clist">
                        <div v-for="(item, index) in rooms" :key="index" >
                            <Chatpreview :room=item></Chatpreview>
                        </div>
                    </div>
                </div>

                <!-- 채팅 방 -->
                <div v-if="chatRoomId!=null">
                    <ChatRoom :roomId=chatRoomId @bactToList="bactToList"></ChatRoom>
                </div>
            </div>

    </div>
</template>

<script>
import Chatpreview from "@/components/ThisSaza/Chat/ChatPreview.vue"
import ChatRoom from "@/components/ThisSaza/Chat/ChatRoom.vue"
import { EventBus } from "@/event-bus.js"
import {axios_contact} from "@/common.js"

export default {
    name: 'Chatlist',
    components :{
        Chatpreview,
        ChatRoom,
    },
    data() {
        return {
            profileId : this.$cookie.get("id"),
            party : "",
            chatList : [512,156,4185,1651,1244,1237,845,1034,15762,1265],
            rooms:[],
            chatRoomId:null,
        };
    },

    created(){
        EventBus.$on("selectRoom",selectRoom=>{
            // console.log(selectRoom)
            this.chatRoomId = selectRoom
        })

        axios_contact({
            method : "get",
            url : "/chat/room/"+this.profileId,
        }).then(({data})=>{
            // console.log(data.roomList)
            for(let index = 0; index<data.roomList.length; index++){
                // console.log(data.roomList[index])
                this.rooms.push(data.roomList[index])
            }
        })
    },

    mounted() {
        
    },

    methods: {
        offChat(){
            this.$emit('chatShowFromChild',true)
        },
        bactToList(){
            this.chatRoomId=null
        },
    },
};
</script>

<style scoped>
.chat{
    background-color:white; 
    box-shadow: 0px 0px 5px 0.1px grey; 
    border-radius: 0.5em;
    width:300px;
    height:500px;
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
    
}
.chat::-webkit-scrollbar{ display:none; }

.clist{
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
    height:500px;
}
.clist::-webkit-scrollbar{ display:none; }
</style>