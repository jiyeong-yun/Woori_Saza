package project.woori_saza.model.dto;

import lombok.Data;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.ChatRoom;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRoomDto extends ChatRoom {

    private String id;
    private String name;
    private int count;
    private List<ChatMessageDto> msgList = new ArrayList<>();// msg내용만

    public ChatRoomDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.count = chatRoom.getCount();
        for(ChatMessage s: chatRoom.getChatMessageList()){
            this.msgList.add(new ChatMessageDto(s));
        }
    }
}
