package cn.center;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
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

import com.alibaba.fastjson.JSONObject;

import cn.center.common.utils.RestResult;
import cn.center.pojo.TbItem;
import cn.center.pojo.TbSolrItem;
import cn.center.solr.pagehelper.PageInfo;
import cn.center.solr.pagehelper.RowBounds;
import cn.center.solr.utils.BaseSolr;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
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
	
	@Test
	public RestResult getItemPage(TbSolrItem entity, int pn, int limit) throws Exception {
		String key = String.format(
				" item_title:%s AND item_cid:%s " , 
				StringUtils.isNotEmpty(entity.getTitle())?entity.getTitle():"*" , 
				entity.getCategory()!=0?entity.getCategory():"*" 
			);
		
		if(entity.getPriceFrom()!=null && entity.getPriceTo()!=null && entity.getPriceTo()>=entity.getPriceFrom()) {
			key += " AND item_price:[" + entity.getPriceFrom() + " TO " + entity.getPriceTo() + "]" ;
		}
		
		SolrQuery query = new SolrQuery();
		query.set("collection", "collection1");
		query.setQuery(key);
		
        //设置分页
        query.setStart((pn - 1) * limit);
        query.setRows(limit);
        
        //自定义排序列
//        if(StringUtils.isNotEmpty(entity.getField()) && StringUtils.isNotEmpty(entity.getOrder())) {
//        	query.addSort(entity.getField(), "desc".equals(entity.getOrder())?SolrQuery.ORDER.desc:SolrQuery.ORDER.asc);
//        }

        Map<String,Object> map = new HashMap<String,Object>();
        long total = 0 ;
        try {
			QueryResponse response = cloudSolrClient.query(query);
			SolrDocumentList results = response.getResults();
			total = results.getNumFound() ;
	        
			List<TbItem> items = new ArrayList<TbItem>();
//			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			for (SolrDocument result : results) {
				TbItem item = new TbItem();
				
				item.setId(Long.valueOf(result.get("id").toString()));
				item.setCode(Long.valueOf(result.get("item_code").toString()));
				item.setTitle(result.get("item_title").toString());
				item.setImage(result.get("item_image").toString());
				item.setCid(Long.parseLong(result.get("item_cid").toString()));
				item.setPrice(Float.valueOf(result.get("item_price").toString()));
				
				items.add(item);
			}
			map.put("list", items) ;
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
        
		map.put("total", total) ;
		return RestResult.ok(map);
	}
	
	
	
	/**
     * 使用 cloudSolrClient 方式新增
     *
     * @throws Exception
     */
    @Test
    public void testAddCloudSolrClient() throws IOException, SolrServerException {

    	TbItem ymq = new TbItem();
        ymq.setId(3L);
        ymq.setCode(645645756L);
        ymq.setTitle("www_ymq_io");
        ymq.setImage("ymqContent");

        cloudSolrClient.setDefaultCollection("test_collection");
        cloudSolrClient.connect();

        cloudSolrClient.addBean(ymq);
        cloudSolrClient.commit();
    }
	
	/**
     * SolrQuery 语法查询
     *
     * @throws Exception
     */
    @Test
    public void testYmqSolrQuery() throws Exception {

        SolrQuery query = new SolrQuery();

        String ymqTitle = "阿尔卡特";

        query.setQuery(" item_title:*" + ymqTitle + "* ");

        cloudSolrClient.setDefaultCollection("collection1");
        cloudSolrClient.connect();
        QueryResponse response = cloudSolrClient.query(query);

        List<TbItem> list = response.getBeans(TbItem.class);

        for (TbItem item : list) {
            System.out.println("SolrQuery 语法查询响应 :" + JSONObject.toJSONString(item));
        }
    }
	
	
	/**
     * 使用 baseSolr 工具类 查询
     *
     * @throws Exception
     */
    @Test
    public void testBaseSolrQuery() throws Exception {
        SolrQuery query = new SolrQuery();

        String itemTitle = "阿尔卡特";
        String defaultCollection = "collection1";

        query.setQuery(" item_title:*" + itemTitle + "* ");

        List<TbItem> list = baseSolr.query(defaultCollection, TbItem.class, query);

        for (TbItem item : list) {
            System.out.println("baseSolr 工具类  查询响应 :" + JSONObject.toJSONString(item));
        }
    }


    /**
     * 使用 baseSolr 工具类 分页 查询
     *
     * @throws Exception
     */
    @Test
    public void testBaseSolrPageInfoQuery() throws Exception {
        SolrQuery query = new SolrQuery();

        String itemTitle = "阿尔卡特";
        String defaultCollection = "collection1";

        query.setQuery(" item_title:*" + itemTitle + "* ");

        PageInfo pageInfo = baseSolr.query(defaultCollection, TbItem.class, query,new RowBounds(0,2));

        System.out.println("使用 baseSolr 工具类 分页 查询响应 :" + JSONObject.toJSONString(pageInfo));
    }
}