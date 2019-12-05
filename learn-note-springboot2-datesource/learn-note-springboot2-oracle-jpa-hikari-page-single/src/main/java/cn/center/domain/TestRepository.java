package cn.center.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

//接口中定义的查询方法符合它的命名规则find、findBy、read、readBy、get、getBy
//IsNotNull		findByAgeNotNull		...  where x.age not null
//Like			findByNameLike			...  where x.name like ?1
//NotLike		findByNameNotLike		...  where x.name not like ?1
//StartingWith	findByNameStartingWith	...  where x.name like ?1(parameter bound with appended %)
//EndingWith	findByNameEndingWith	...  where x.name like ?1(parameter bound with prepended %)
//Containing	findByNameContaining	...  where x.name like ?1(parameter bound wrapped in %)
//OrderBy		findByAgeOrderByName	...  where x.age = ?1 order by x.name desc
//Not			findByNameNot			...  where x.name <> ?1
//In			findByAgeIn				...  where x.age in ?1
//NotIn			findByAgeNotIn		...  where x.age not in ?1
//True			findByActiveTrue	...  where x.avtive = true
//Flase			findByActiveFalse	...  where x.active = false
//And			findByNameAndAge	...  where x.name = ?1 and x.age = ?2
//Or			findByNameOrAge		...  where x.name = ?1 or x.age = ?2
//Between		findBtAgeBetween	...  where x.age between ?1 and ?2
//LessThan		findByAgeLessThan		...  where x.age  <  ?1
//GreaterThan	findByAgeGreaterThan	...  where x.age > ?1
//After/Before	...						...
//IsNull		findByAgeIsNull			...  where x.age is null
public interface TestRepository extends JpaRepository<CsTest, Long>, JpaSpecificationExecutor<CsTest> {
	
	/**
     * 调用存储过程
     * pluslinout 存储过程名字
     * @param arg
     * @return
     */
    @Procedure("pluslinout")
    Integer explicitlyNamedPluslinout(Integer arg);
    
	CsTest findByUserid(String userid);

	CsTest findByUseridAndScore(String userid, Integer score);

	
    @Query("from cs_test u where u.userid=:userid")
    CsTest findUser(@Param("userid") String userid);
}
