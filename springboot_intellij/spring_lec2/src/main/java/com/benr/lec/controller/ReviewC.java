package com.benr.lec.controller;

import com.benr.lec.service.reviewService;
import com.benr.lec.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewC {

    @Autowired
    private reviewService reviewService;

    @GetMapping("/all")
    public String getAllReviews(Model model) {
        List<ReviewVO> reviews = reviewService.getAllReviews();
        System.out.println(reviews);
        model.addAttribute("reviews", reviews);
        return "review/reviews";
    }

    @GetMapping("/create")
    public String getReviewAdd() {
        return "review/review_add";
    }

    @PostMapping("/create")
    public String createReview(ReviewVO review) {
        reviewService.createReview(review);
        return "redirect:/review/all";
    }

    @GetMapping("/detail")
    public String getReviewDetail(Model model, int no) {
        System.out.println(no);
        model.addAttribute("review", reviewService.getReviewDetail(no));
        return "review/review_detail";
    }

    @GetMapping("/update")
    public String updateReview(Model model, int no) {
        model.addAttribute("review", reviewService.getReviewDetail(no));
        return "review/review_update";
    }

    @PostMapping("/update")
    public String updateReview(ReviewVO review) {
        reviewService.updateReview(review);
        return "redirect:detail?no=" + review.getR_no();
    }

    @GetMapping("/delete")
    public String deleteReview(@RequestParam int no) {
        reviewService.deleteReview(no);
        return "redirect:/review/all";
    }

    @GetMapping("/detail2")
    public String getReviewDetail2(Model model, int no) {
        model.addAttribute("review", reviewService.getReviewDetail(no));
        return "review/review_detail2";
    }

    @ResponseBody
    @PostMapping("/api/update")
    public int postReviewDetail2(ReviewVO review) {
        return reviewService.updateReview(review);
    }




}
