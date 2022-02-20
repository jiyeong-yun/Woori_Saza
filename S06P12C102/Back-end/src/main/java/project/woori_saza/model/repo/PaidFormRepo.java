package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.PaidForm;
import project.woori_saza.model.domain.Party;

public interface PaidFormRepo extends JpaRepository<PaidForm, Long> {
    PaidForm findByParty(Party party);
}
