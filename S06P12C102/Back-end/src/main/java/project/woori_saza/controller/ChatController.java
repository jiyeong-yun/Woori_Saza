package project.woori_saza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.dto.ChatMessageDto;
import project.woori_saza.model.repo.ChatMessageRepo;
import project.woori_saza.model.repo.ChatRoomRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class ChatController {

    @Autowired
    ChatMessageRepo chatMessageRepo;

    @Autowired
    ChatRoomRepo chatRoomRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    private final SimpMessagingTemplate template; // 특정 브로커로 메시지 전달

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {

        // dto로 받아온 채팅 메시지 DB저장 및 전송
        ChatRoom chatRoom = chatRoomRepo.getById(message.getRoomId());

        String content = message.getContent().trim(); // 공백제거
        // 내용이 비어있으면 DB 저장 및 전송 안함
        if(!content.equals("")){
            String time = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
            ChatMessage chatMessage = ChatMessage.createChatMessage(chatRoom, message.getType(), message.getContent(), message.getSender(), time);
            message.setTime(time);

            chatMessageRepo.save(chatMessage);

            template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        }
    }
}
