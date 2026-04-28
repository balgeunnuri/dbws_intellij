package com.benr.lec.mapper;

import com.benr.lec.vo.ReviewVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("select * from review_test")
    List<ReviewVO> selectReviews();

    @Insert("insert into review_test values (review_test_seq.nextval, #{r_title}, #{r_txt}, sysdate)")
    int insertReview(ReviewVO review);

    @Select("select * from review_test where r_no = #{no}")
    ReviewVO selectReviewDetail(int no);

    @Update("update review_test set r_title = #{r_title}, r_txt = #{r_txt} where r_no = #{r_no}")
    int updateReview(ReviewVO review);

    @Delete("delete from review_test where r_no = #{no}")
    int deleteReview(int no);


}
