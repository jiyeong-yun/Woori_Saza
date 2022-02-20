package project.woori_saza.model.service;

import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.dto.ArticleAndPartyRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.repo.*;
import project.woori_saza.util.GeoLocationUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private PartyRepo partyRepo;

    @Autowired
    private MemberInfoRepo memberInfoRepo;

    @Autowired
    private UserProfileRepo userProfileRepo;

    @Autowired
    private GeoLocationUtil geoLocationUtil;

    @Autowired
    private ChatRoomRepo chatRoomRepo;

    @Autowired
    private ChatRoomJoinRepo chatRoomJoinRepo;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ZzimRepo zzimRepo;

    @Override
    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepo.getById(articleId);
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

        return articleResponseDto;
    }

    // 게시글 전체 조회
    @Override
    public List<ArticleResponseDto> getArticleList(String profileId, String category, String range, String keyword) {

        List<Article> articles = null;

        // 로그인 안했을때 범위는 고려 X
        if (profileId.equals("null")) {
            if (category == null && keyword == null) {
                articles = articleRepo.findAllByUserProfileIsNotNullOrderByCreatedAtDesc();
            } else if (keyword == null) {
                articles = articleRepo.findByUserProfileIsNotNullAndCategoryOrderByCreatedAtDesc(Category.valueOf(category));
            } else if (category == null) {
                articles = articleRepo.findByUserProfileIsNotNullAndTitleContainingOrContentContainingOrderByCreatedAtDesc(keyword, keyword);
            } else {
                articles = articleRepo.findByKeywordAndCategory(keyword, category);
            }
            return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
        }

        Double[] lnglat = geoLocationUtil.parseLocationToLngLat(userProfileRepo.getById(profileId).getAddress());
        //1. 전부 없을때
        if (category == null && range == null && keyword == null) {
            articles = articleRepo.findAllByUserProfileIsNotNullOrderByCreatedAtDesc();
        }
        //2. 카테고리만 있을때
        else if (range == null && keyword == null) {
            articles = articleRepo.findByUserProfileIsNotNullAndCategoryOrderByCreatedAtDesc(Category.valueOf(category));
        }
        //3. 범위만 있을때
        else if (category == null && keyword == null) {
            articles = articleRepo.findByRange(lnglat, range);
        }
        //4. 검색어만 있을때
        else if (category == null && range == null) {
            articles = articleRepo.findByUserProfileIsNotNullAndTitleContainingOrContentContainingOrderByCreatedAtDesc(keyword, keyword);
        }
        //5. 카테고리, 범위
        else if (keyword == null) {
            articles = articleRepo.findByCategoryAndRange(category, lnglat, range);
        }
        //6. 카테고리, 검색어
        else if (range == null) {
            articles = articleRepo.findByKeywordAndCategory(keyword, category);
        }
        //7. 범위, 검색어
        else if (category == null) {
            articles = articleRepo.findByKeywordAndRange(keyword, lnglat, range);
        }
        //8. 전부있을때
        else {
            articles = articleRepo.findByAllCondition(keyword, category, lnglat, range);
        }
        return articles.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArticleResponseDto insertArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto) {

        if (articleAndPartyRequestDto.getTotalRecruitMember() <= articleAndPartyRequestDto.getAmount()) {
            throw new RuntimeException("전체 인원수보다 선택 인원수가 더 많습니다.");
        } else {
            Party party = new Party();
            party.setDeadline(LocalDateTime.parse(articleAndPartyRequestDto.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
            party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
            party.setProduct(articleAndPartyRequestDto.getProduct());
            party.setTotalPrice(articleAndPartyRequestDto.getTotalPrice());
            party.setTotalProductCount(articleAndPartyRequestDto.getTotalProductCount());
            party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
            party.setPenalty(articleAndPartyRequestDto.getPenalty());
            party.setCurrentRecruitMember(articleAndPartyRequestDto.getAmount());
            party.setFormChecked(false);
            party.setIsClosed(false);
            party = partyRepo.save(party);


            Article article = new Article();

            UserProfile userProfile = userProfileRepo.getById(articleAndPartyRequestDto.getProfileId());
            article.setUserProfile(userProfile);
            article.setTitle(articleAndPartyRequestDto.getTitle());
            article.setContent(articleAndPartyRequestDto.getContent());
            article.setLink(articleAndPartyRequestDto.getLink());
            article.setPic(articleAndPartyRequestDto.getPic());
            article.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
            article.setCategory(articleAndPartyRequestDto.getCategory());
            article.setTag(null);
            article.setParty(party);
            article = articleRepo.save(article);


            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setIsBoss(true);
            memberInfo.setAmount(articleAndPartyRequestDto.getAmount());
            int calprice = (int) (articleAndPartyRequestDto.getTotalPrice() / articleAndPartyRequestDto.getTotalRecruitMember()) * articleAndPartyRequestDto.getAmount();
            memberInfo.setPrice(calprice);
            memberInfo.setParty(party);
            memberInfo.setUserProfile(userProfile);
            memberInfoRepo.save(memberInfo);

            /**
             * 채팅방 생성
             */
            ChatRoom chatRoom = chatRoomService.createChatRoom(article.getId(), article.getTitle());
            ChatRoomJoin chatRoomJoin = chatRoomService.createChatRoomJoin(chatRoom, userProfile);
            chatRoomRepo.save(chatRoom);
            chatRoomJoinRepo.save(chatRoomJoin);

            return new ArticleResponseDto(article);

        }
    }


    @Override
    @Transactional
    public ArticleResponseDto updateArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto, Long articleId) {
        if (articleAndPartyRequestDto.getTotalRecruitMember() <= articleAndPartyRequestDto.getAmount()) {
            throw new RuntimeException("전체 인원수보다 선택 인원수가 더 많습니다.");
        } else {
            Article article = articleRepo.getById(articleId);

            Party party = article.getParty();
            party.setDeadline(LocalDateTime.parse(articleAndPartyRequestDto.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")));
            party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
            party.setProduct(articleAndPartyRequestDto.getProduct());
            party.setTotalPrice(articleAndPartyRequestDto.getTotalPrice());
            party.setTotalProductCount(articleAndPartyRequestDto.getTotalProductCount());
            party.setTotalRecruitMember(articleAndPartyRequestDto.getTotalRecruitMember());
            party.setPenalty(articleAndPartyRequestDto.getPenalty());
            party.setCurrentRecruitMember(articleAndPartyRequestDto.getAmount());
            party = partyRepo.save(party);

            article.setTitle(articleAndPartyRequestDto.getTitle());
            article.setContent(articleAndPartyRequestDto.getContent());
            article.setLink(articleAndPartyRequestDto.getLink());
            article.setPic(articleAndPartyRequestDto.getPic());
            article.setCategory(articleAndPartyRequestDto.getCategory());
            article.setTag(null);
            article.setParty(party);
            article = articleRepo.save(article);


            List<MemberInfo> memberInfos = memberInfoRepo.findAllByParty(party);
            for (MemberInfo memberInfo : memberInfos) {
                if (memberInfo.getIsBoss() == true) {
                    memberInfo.setAmount(articleAndPartyRequestDto.getAmount());
                    int calprice = (int) (articleAndPartyRequestDto.getTotalPrice() / articleAndPartyRequestDto.getTotalRecruitMember()) * articleAndPartyRequestDto.getAmount();
                    memberInfo.setPrice(calprice);
                    memberInfoRepo.save(memberInfo);
                }
            }
            return new ArticleResponseDto(article);
        }
    }
    @Override
    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepo.getById(articleId);
        List<Comment> comments = commentRepo.findByArticle(article);
        List<Zzim> Zzims = zzimRepo.findByArticle(article);

        for (Comment comment : comments) {
            commentRepo.deleteById(comment.getId());
        }
        for (Zzim zzim : Zzims) {
            zzimRepo.deleteById(zzim.getZzimId());
        }

        articleRepo.deleteById(articleId);
    }
}
