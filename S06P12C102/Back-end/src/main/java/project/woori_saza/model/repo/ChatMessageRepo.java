package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.ChatMessage;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {

}