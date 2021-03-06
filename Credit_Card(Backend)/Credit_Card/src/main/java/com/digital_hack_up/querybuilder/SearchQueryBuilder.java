package com.digital_hack_up.querybuilder;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.digital_hack_up.bean.CreDitCardIndexBean;

@Component
public class SearchQueryBuilder {

	@Autowired ElasticsearchTemplate elasticsearchTemplate;
	
	public List<CreDitCardIndexBean> getAll(String text){
		      
		QueryBuilder query=QueryBuilders.boolQuery()
				.should(
						QueryBuilders.queryStringQuery(text)
						.lenient(true)
						.field("name")
						.field("id")//education,marriage,sex
						.field("education")
						.field("marriage")
						.field("sex")
						).should(QueryBuilders.queryStringQuery("*"+text+"*")
								.lenient(true)
								.field("name")
								.field("id")
								.field("education")
								.field("marriage")
								.field("sex"));
				 NativeSearchQuery build = new NativeSearchQueryBuilder()
						.withQuery(query)
						.build();
			return 	elasticsearchTemplate.queryForList(build, CreDitCardIndexBean.class);
	}
	
}
