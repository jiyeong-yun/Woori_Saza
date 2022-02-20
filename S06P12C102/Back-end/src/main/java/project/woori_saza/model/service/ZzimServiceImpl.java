package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.domain.Zzim;
import project.woori_saza.model.dto.ZzimDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.model.repo.ZzimRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZzimServiceImpl implements ZzimService {

    @Autowired
    ZzimRepo zzimRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    ArticleRepo articleRepo;

    @Override
    public ZzimDto getZzim(String profileId, Long articleId) {
        Zzim zzim = zzimRepo.getById(new Zzim.ZzimId(profileId, articleId));
        System.out.println(zzim.getZzimId().getProfileId());
        return new ZzimDto(zzim);
    }

    @Override
    public List<ZzimDto> getZzimList(String profileId) {
        UserProfile userProfile = userProfileRepo.getById(profileId);
        return zzimRepo.findByUserProfileOrderByArticleDesc(userProfile).stream().map(ZzimDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ZzimDto> getZzimList(Long articleId) {
        Article article = articleRepo.getById(articleId);

        return zzimRepo.findByArticle(article).stream().map(ZzimDto::new).collect(Collectors.toList());
    }

    @Override
    public ZzimDto insertZzim(String profileId, Long articleId) {
        Zzim zzim = new Zzim(userProfileRepo.getById(profileId), articleRepo.getById(articleId));
        zzim = zzimRepo.save(zzim);
        return new ZzimDto(zzim);
    }

    @Override
    public void deleteZzim(String profileId, Long articleId) {
        zzimRepo.deleteById(new Zzim.ZzimId(profileId, articleId));
    }
}
