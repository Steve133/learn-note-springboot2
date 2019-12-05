package cn.center;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test_SolrClient {

	@Autowired
	private SolrClient client;

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
		query.setQuery("id:605616");
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
		QueryResponse rsp = client.query("collection1", query);

		SolrDocumentList results = rsp.getResults();

		// 获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
		Map<String, Map<String, List<String>>> highlight = rsp.getHighlighting();

		for (SolrDocument result : results) { // 将高亮结果合并到查询结果中
			result.remove("keyword");
			highlight.forEach((k, v) -> {
				if (result.get("id").equals(k)) {
					v.forEach((k1, v1) -> {
						if (!k1.equals("keyword"))
							result.setField(k1, v1); // 高亮列合并如结果
					});
				}
			});
		}

		// 遍历文档列表，从取域的内容。
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
		
		st = results;
	}
}