package cn.center;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.center.solr.utils.BaseSolr;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {
	private static Logger logger = LoggerFactory.getLogger(RepositoryTests.class);
	
	@Autowired
	CloudSolrClient cloudSolrClient;
	@Autowired
    BaseSolr baseSolr;
	@Test
	public void queryIndex() throws Exception {
		//创建一个SolrQuery对象。
		SolrDocumentList st = null;
		SolrQuery query = new SolrQuery();
		//设置查询条件。
		query.setQuery( "*:*" );
		query.setRows(5);
		QueryResponse rsp = null;
		try {
			//执行查询，QueryResponse对象。
			QueryRequest req = new QueryRequest( query,METHOD.POST );
			cloudSolrClient.setDefaultCollection("collection1");
			rsp = req.process(cloudSolrClient);
			st = rsp.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//遍历文档列表，从取域的内容。
		for (SolrDocument solrDocument : st) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}
}
