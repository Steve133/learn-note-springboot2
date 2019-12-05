package cn.center.service;

public interface TestService {

	String testCache(String id);

	String testCachePut(String id);

	void removeCache(String id);

}
