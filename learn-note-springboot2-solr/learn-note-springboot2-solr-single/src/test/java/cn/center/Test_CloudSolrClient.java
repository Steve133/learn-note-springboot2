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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test_CloudSolrClient {

	@Autowired
	private CloudSolrClient cloudSolrClient;

	/**
	 * @description: 查询
	 * @throws Exception
	 * @author song
	 * @date 2019年11月27日 下午6:13:58
	 */
	@Test
	public void queryIndex() throws Exception {
		// 创建一个SolrQuery对象。
		SolrDocumentList st = null;

		SolrQuery query = new SolrQuery();
		// 设置查询条件。
		query.setQuery("*:*");
		// 高亮
		// 打开开关
		query.setHighlight(true);
		// 指定高亮域
		query.addHighlightField("id");

		// 设置前缀
		query.setHighlightSimplePre("<span style='color:red'>");
		// 设置后缀
		query.setHighlightSimplePost("</span>");

		query.setRows(5);
		// 执行查询，QueryResponse对象。
		cloudSolrClient.setDefaultCollection("collection1");
		QueryRequest req = new QueryRequest(query, METHOD.POST);
		QueryResponse rsp = req.process(cloudSolrClient);
		
		st = rsp.getResults();
		// 遍历文档列表，从取域的内容。
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