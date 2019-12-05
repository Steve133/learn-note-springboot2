package cn.center.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cs_test")
public class CsTest {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	@Column(name = "name",nullable = true,length = 200)
    private String userid;
	@Column(length = 200)
    private Integer score;
	@Column(length = 200)
    private String classid;

	
    public CsTest() {
		super();
	}

	public CsTest(String userid, Integer score) {
		super();
		this.userid = userid;
		this.score = score;
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.classid = "12";
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }
}