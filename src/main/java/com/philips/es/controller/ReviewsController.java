package com.philips.es.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.philips.es.dao.ReviewsDao;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
	 @Autowired
    private ReviewsDao reviewsDao;

    public ReviewsController(ReviewsDao reviewsDao) {
        this.reviewsDao = reviewsDao;
    }

    

   

    
    
    @RequestMapping(value = "/api/docbyrating")

    List<Map<String, Object>> getReviewsByRating(@RequestParam(value = "rating", defaultValue = "1") String rating) {
    	System.out.println(rating);
        return reviewsDao.getReviewsByRating(rating);

    }
    @RequestMapping(value = "/api/docbyid")
    Map<String, Object> getReviewsByID(@RequestParam(value = "id", defaultValue = "1") String id) {
    	System.out.println(id);
    	return reviewsDao.getReviewsByDocID(id);
        

    }
    
    @RequestMapping(value = "/api/docbydate")
    List<Map<String, Object>> getReviewsByStoreType(@RequestParam(value = "date", defaultValue = "2017-12-07") String date) {
    	System.out.println(date);
    	return reviewsDao.getReviewsByDate(date);
        

    }
    @RequestMapping(value = "/api/docbyauthor")
    List<Map<String, Object>> getReviewsByAuthorName(@RequestParam(value = "author", defaultValue = "markbruce44") String author) {
    	System.out.println(author);
    	return reviewsDao.getReviewsByAuthor(author);
        

    }
    @RequestMapping(value = "/api")
    List<Map<String, Object>> getReviewsAll() {
    	
    	return reviewsDao.getReviewsAll();
        

    }
}
