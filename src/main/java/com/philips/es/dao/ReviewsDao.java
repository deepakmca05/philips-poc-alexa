package com.philips.es.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ReviewsDao {

    private final String INDEX = "reviews";
    private final String TYPE = "alexa";

    private RestHighLevelClient restHighLevelClient;
    @Value("${index.name}")
    private String index;
    @Value("${index.type}")
    private String type;
    private SearchResponse searchResponse = null;
    @SuppressWarnings("unused")
	private ObjectMapper objectMapper;
    
    private SearchHit[] searchHits = null;
    private SearchHits hits = null;
    public ReviewsDao( ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

  

    public Map<String, Object> getReviewsByDocID(String id){
        GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
        GetResponse getResponse = null;
        
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }
    
    
	public List<Map<String, Object>>  getReviewsByRating(String rating){
    	SearchRequest searchRequest = new SearchRequest(index);
    	searchRequest.types(type);
    	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    	sourceBuilder.query(QueryBuilders.termQuery("rating", rating));
    	searchRequest.source(sourceBuilder);
    	Map<String, Object> sourceAsMap = null;
    	List<Map<String,Object>> mySourceAsMap=new ArrayList<Map<String, Object>>();;
    	try {
			searchResponse = restHighLevelClient.search(searchRequest);
			hits = searchResponse.getHits();
			searchHits = hits.getHits();
			
			for (SearchHit hit : searchHits) {
				sourceAsMap = hit.getSourceAsMap();
				mySourceAsMap.add(sourceAsMap);
				//System.out.println(hit.getSourceAsMap().keySet());
				//innerObject.put(sourceAsMap.get("innerObject")., value)
				 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mySourceAsMap;
    	//return innerObject;
    }
    
   	public List<Map<String, Object>>  getReviewsByDate(String date){
       	SearchRequest searchRequest = new SearchRequest(index);
       	searchRequest.types(type);
       	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
       	sourceBuilder.query(QueryBuilders.termQuery("reviewed_date", date));
       	searchRequest.source(sourceBuilder);
       	Map<String, Object> sourceAsMap = null;
       	List<Map<String,Object>> mySourceAsMap=new ArrayList<Map<String, Object>>();
       	try {
   			searchResponse = restHighLevelClient.search(searchRequest);
   			hits = searchResponse.getHits();
   			searchHits = hits.getHits();
   			
   			for (SearchHit hit : searchHits) {
   				sourceAsMap = hit.getSourceAsMap();
   				mySourceAsMap.add(sourceAsMap);
   				//innerObject.put(sourceAsMap.get("innerObject")., value)
   				 
   			}
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
       	return mySourceAsMap;
       	//return innerObject;
       }
   
    
   	public List<Map<String,Object>> getReviewsByAuthor(String author){
       	SearchRequest searchRequest = new SearchRequest(index);
       	searchRequest.types(type);
       	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
       	sourceBuilder.query(QueryBuilders.termQuery("author", author));
       	searchRequest.source(sourceBuilder);
       	Map<String, Object> sourceAsMap = null;
       	List<Map<String,Object>> mySourceAsMap=new ArrayList<Map<String, Object>>();
       	try {
   			searchResponse = restHighLevelClient.search(searchRequest);
   			hits = searchResponse.getHits();
   			searchHits = hits.getHits();
   			
   			for (SearchHit hit : searchHits) {
   				sourceAsMap = hit.getSourceAsMap();
   				//innerObject.put(sourceAsMap.get("innerObject")., value)
   				mySourceAsMap.add(sourceAsMap);
   			}
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
       	
		return mySourceAsMap;
       	//return innerObject;
       }
    
	public List<Map<String, Object>>  getReviewsAll(){
    	SearchRequest searchRequest = new SearchRequest(index);
    	searchRequest.types(type);
    	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    	//sourceBuilder.query(QueryBuilders.termQuery("rating", rating));
    	searchRequest.source(sourceBuilder);
    	Map<String, Object> sourceAsMap = null;
    	List<Map<String,Object>> mySourceAsMap=new ArrayList<Map<String, Object>>();;
    	try {
			searchResponse = restHighLevelClient.search(searchRequest);
			hits = searchResponse.getHits();
			searchHits = hits.getHits();
			
			for (SearchHit hit : searchHits) {
				sourceAsMap = hit.getSourceAsMap();
				mySourceAsMap.add(sourceAsMap);
				//System.out.println(hit.getSourceAsMap().keySet());
				//innerObject.put(sourceAsMap.get("innerObject")., value)
				 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mySourceAsMap;
    	//return innerObject;
    }

}
