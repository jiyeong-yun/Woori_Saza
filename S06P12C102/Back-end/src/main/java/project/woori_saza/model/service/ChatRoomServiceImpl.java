package project.woori_saza.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.dto.ChatRoomDto;
import project.woori_saza.model.repo.ChatRoomJoinRepo;
import project.woori_saza.model.repo.ChatRoomRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    ChatRoomRepo chatRoomRepo;

    @Autowired
    ChatRoomJoinRepo chatRoomJoinRepo;

    // 내가 가진 채팅방 가져오기
    @Override
    public List<String> findAllRoom(String profileId) {
        UserProfile user = userProfileRepo.getById(profileId);
        List<ChatRoomJoin> joinList = user.getChatRoomJoinList();
        List<String> chatRoomList = new ArrayList<>();
        for (ChatRoomJoin chatRoomJoin : joinList) {
            // 객체로 넣으며 안됨 -> 무한참조
            chatRoomList.add(chatRoomJoin.getChatRoom().getId());
        }
        return chatRoomList;
    }

    // 채팅방 이름 가져오기
    @Override
    public ChatRoomDto findRoomByRoomID(String roomId) {
        ChatRoom findChatRoom = chatRoomRepo.getById(roomId);
        ChatRoomDto chatRoomDto = new ChatRoomDto(findChatRoom);
        return chatRoomDto;
    }

    // 채팅방 생성하기
    @Override
    public ChatRoom createChatRoom(Long articleId, String articleTitle) {
        ChatRoom chatRoom = ChatRoom.create(articleId, articleTitle);

        return chatRoom;
    }

    // 채팅-유저 조인 생성 (article에서) + 채팅방 입장(enterChatRoom)
    @Override
    public ChatRoomJoin createChatRoomJoin(ChatRoom chatRoom, UserProfile user){
        ChatRoomJoin chatRoomJoin = ChatRoomJoin.create(chatRoom,user);

        return chatRoomJoin;
    }

    @Override
    public void deleteChatRoomJoin(ChatRoom chatRoom, UserProfile userProfile) {
        chatRoomJoinRepo.deleteChatRoomJoinByChatRoomAndUserProfile(chatRoom, userProfile);
    }

}
