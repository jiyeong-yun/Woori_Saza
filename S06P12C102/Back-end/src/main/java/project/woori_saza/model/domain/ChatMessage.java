package project.woori_saza.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="msg_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @NotNull
    private String content;

    @NotNull
    private String sender;

    @NotNull
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private ChatRoom chatRoom;

    public static ChatMessage createChatMessage(ChatRoom chatRoom, MessageType type, String content, String sender, String time){
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .type(type)
                .content(content)
                .sender(sender)
                .time(time)
                .build();
        return chatMessage;
    }

    // 입장, 퇴장 메시지 변경을 위해
    public void setContent(String content){
        this.content=content;
    }

}
