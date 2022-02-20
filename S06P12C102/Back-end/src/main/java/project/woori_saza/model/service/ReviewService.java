package project.woori_saza.model.service;

import project.woori_saza.model.dto.*;

import java.util.List;

public interface ReviewService {

    List<ReviewResponseDto> getReviewList(String profileId);
    List<ReviewResponseDto> getMyReviewList(String profileId);
    void insertReview(ReviewRequestDto reviewRequestDto);
    void updateReview(Long reviewId, ReviewRequestDto reviewRequestDto);
    void deleteReview(Long reviewId);
}
