package cn.center.service;

import java.util.List;

import cn.center.domain.CsTest;

public interface TransactionTestService1 {

	void save(CsTest tb);

	List<CsTest> getTestPage(Integer pageNumber, Integer pageSize, CsTest csTest);

}
