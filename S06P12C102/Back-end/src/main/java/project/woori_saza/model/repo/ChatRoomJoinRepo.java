package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.domain.ChatRoomJoin;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface ChatRoomJoinRepo extends JpaRepository<ChatRoomJoin, Long> {

    void deleteChatRoomJoinByChatRoomAndUserProfile(ChatRoom chatRoom, UserProfile userProfile);
    List<ChatRoomJoin> findAllByChatRoom(ChatRoom chatRoom);
    List<ChatRoomJoin> findAllByUserProfile(UserProfile userProfile);
}
