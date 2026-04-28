package com.benr.lec.service;

import com.benr.lec.mapper.ReviewMapper;
import com.benr.lec.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class reviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public List<ReviewVO> getAllReviews() {
     List<ReviewVO> reviews = reviewMapper.selectReviews();
        System.out.println(reviews);
     return reviews;


    }

    public int createReview(ReviewVO review) {
        int row = reviewMapper.insertReview(review);
        if (row == 1) {
            System.out.println("Review creation success");
        }
        return row;
    }

    public ReviewVO getReviewDetail(int no) {
        return reviewMapper.selectReviewDetail(no);
    }


    public int updateReview(ReviewVO review) {
        int row = reviewMapper.updateReview(review);
        if (row == 1) {
            System.out.println("Review update success");
        } return row;
    }


    public int deleteReview(int no) {
        return reviewMapper.deleteReview(no);
    }


}
