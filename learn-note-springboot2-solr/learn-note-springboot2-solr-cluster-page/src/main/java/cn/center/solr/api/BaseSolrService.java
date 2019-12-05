package cn.center.solr.api;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

import cn.center.solr.pagehelper.Page;
import cn.center.solr.pagehelper.PageInfo;
import cn.center.solr.pagehelper.PageInfoFacet;
import cn.center.solr.pagehelper.RowBounds;

@Component
public class BaseSolrService {
    private static final Logger logger = LoggerFactory.getLogger(BaseSolrService.class);

    @Autowired
    private CloudSolrClient cloudSolrClient;

    /**
     * 添加数据
     *
     * @param defaultCollection solr 库
     * @param bean              对象
     * @throws IOException
     * @throws SolrServerException
     */
    public void add(String defaultCollection, Object bean) throws IOException, SolrServerException {
        logger.info("solr-add defaultCollection:{},bean:{}", defaultCollection, bean.getClass().getName());

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        cloudSolrClient.addBean(bean);
        cloudSolrClient.commit();
    }

    /**
     * 添加一组数据
     *
     * @param defaultCollection solr 库
     * @param beans             list集合数据添加
     * @throws IOException
     * @throws SolrServerException
     */
    public void adds(String defaultCollection, Collection<?> beans) throws IOException, SolrServerException {
        logger.info("solr-adds defaultCollection:{},beans：{}", defaultCollection, beans.getClass().getName());

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        cloudSolrClient.addBeans(beans);
        cloudSolrClient.commit();
    }

    /**
     * 根据多个id删除数据
     *
     * @param defaultCollection
     * @param ids
     * @throws IOException
     * @throws SolrServerException
     */
    public void deleteByIds(String defaultCollection, List<String> ids) throws IOException, SolrServerException {
        logger.info("solr-delete defaultCollection:{},ids:{}", defaultCollection, JSON.toJSONString(ids));

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        cloudSolrClient.deleteById(ids);
        cloudSolrClient.commit();
    }

    /**
     * 根据ID删除数据
     *
     * @param defaultCollection solr 库
     * @param id                要删除的文档的id
     * @throws IOException
     * @throws SolrServerException
     */
    public void deleteById(String defaultCollection, String id) throws IOException, SolrServerException {
        logger.info("solr-delete-id defaultCollection:{},ids:{}", defaultCollection, id);

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        cloudSolrClient.deleteById(id);
        cloudSolrClient.commit();
    }


    /**
     * 根据指定索引(字段)模糊删除数据
     *
     * @param defaultCollection solr 库
     * @param field
     * @param fieldValue
     * @throws IOException
     * @throws SolrServerException
     */
    public void deleteByField(String defaultCollection, String field, String fieldValue) throws IOException, SolrServerException {
        logger.info("solr-delete-Field-id defaultCollection:{},field:{},fieldValue:{}", defaultCollection, field, fieldValue);

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        cloudSolrClient.deleteByQuery(field + ":" + fieldValue);
        cloudSolrClient.commit();
    }

    /**
     * 查询数据
     *
     * @param defaultCollection solr 库
     * @param clazz             对象Po
     * @param query             查询条件
     * @param <T>               返回查询集合
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public <T> List<T> query(String defaultCollection, Class<T> clazz, SolrQuery query) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},field:{},query{}", defaultCollection, clazz.getClass().getName(), query.getQuery());

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        QueryResponse response = cloudSolrClient.query(query);
        return response.getBeans(clazz);
    }

    /**
     * 查询
     *
     * @param defaultCollection solr 库
     * @param query             查询条件
     * @return 返回response对象
     * @throws IOException
     * @throws SolrServerException
     */
    public QueryResponse query(String defaultCollection, SolrQuery query) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},query:{}", defaultCollection, query.getQuery());

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        QueryResponse response = cloudSolrClient.query(query);
        return response;
    }

    /**
     * @param defaultCollection solr 库
     * @param clazz             查询的数据对应的对象
     * @param query             查询条件
     * @param rowBounds         分页参数
     * @param <T>
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public <T> PageInfo query(String defaultCollection, Class<T> clazz, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {

        logger.info("solr-query-rowBounds defaultCollection:{},field:{},query:{}", defaultCollection, clazz.getClass().getName(), query.getQuery());

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        if (rowBounds != null) {
            query.setStart(rowBounds.getOffset());
            query.setRows(rowBounds.getLimit());
        } else {
            Integer start = query.getStart();
            if (start == null) {
                start = 0;
            }
            Integer rows = query.getRows();
            Assert.notNull(start, "请设置分页条数rows");
            rowBounds = new RowBounds(start, rows);
        }
        QueryResponse response = cloudSolrClient.query(query);
        SolrDocumentList documentList = response.getResults();
        List content = new DocumentObjectBinder().getBeans(clazz, documentList);
        Page page = new Page();
        page.addAll(content);
        page.setPageNum(rowBounds.getOffset());
        page.setPageSize(rowBounds.getLimit());
        page.setTotal(documentList.getNumFound());
        PageInfo pageInfo = new PageInfo(page);
        return pageInfo;
    }

    /**
     * 查询数据
     *
     * @param defaultCollection solr 库
     * @param query             查询条件
     * @param rowBounds         分页
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public PageInfo query(String defaultCollection, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},query:{}", defaultCollection, query.getQuery());

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        if (rowBounds != null) {
            query.setStart(rowBounds.getOffset());
            query.setRows(rowBounds.getLimit());
        } else {
            Integer start = query.getStart();
            if (start == null) {
                start = 1;
            }
            Integer rows = query.getRows();
            Assert.notNull(start, "请设置分页条数rows");
            rowBounds = new RowBounds(start, rows);
        }
        QueryResponse response = cloudSolrClient.query(query);
        SolrDocumentList documentList = response.getResults();
        List list = (List) documentList;


        Page page = new Page(rowBounds.getOffset(), rowBounds.getLimit());
        page.setTotal(documentList.getNumFound());
        page.addAll(list);


        PageInfo pageInfo = new PageInfo(page);

        return pageInfo;
    }

    /**
     * solrj的facet结果集查询
     *
     * @param defaultCollection solr 库
     * @param query             查询条件
     * @param rowBounds         分页数
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public PageInfoFacet queryFacet(String defaultCollection, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},query:{}", defaultCollection, defaultCollection);

        cloudSolrClient.setDefaultCollection(defaultCollection);
        cloudSolrClient.connect();
        if (rowBounds != null) {
            query.setStart(rowBounds.getOffset() - 1);
            query.setRows(rowBounds.getLimit());
        } else {
            Integer start = query.getStart();
            if (start == null) {
                start = 1;
            }
            Integer rows = query.getRows();
            Assert.notNull(start, "请设置分页条数rows");
            rowBounds = new RowBounds(start, rows);
        }
        QueryResponse response = cloudSolrClient.query(query);
        SolrDocumentList documentList = response.getResults();
        List list = (List) documentList;
        Page page = new Page(rowBounds.getOffset(), rowBounds.getLimit());
        page.setTotal(documentList.getNumFound());
        page.addAll(list);
        PageInfo pageInfo = new PageInfo(page);
        PageInfoFacet pageInfoFacet = new PageInfoFacet();
        pageInfoFacet.setPageInfo(pageInfo);
        pageInfoFacet.setFacetFieldList(response.getFacetFields());
        return pageInfoFacet;
    }
}
