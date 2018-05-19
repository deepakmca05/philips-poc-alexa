package com.philips.es.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.philips.es.Elastic6Application;
import com.philips.es.bean.Reviews;
import com.philips.es.dao.ReviewsDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Elastic6Application.class)
public class Elastic6ApplicationTests {

	@Autowired
    private ReviewsDao reviewsDao;
	@Test
	public void testFindByID() {
		Map<String, Object> sourceAsMap=reviewsDao.getReviewsByDocID("1");
		assertEquals((String)sourceAsMap.get("author"),"WarcryxD");
		assertEquals((String)sourceAsMap.get("title"),"Terrible");
	}

}
