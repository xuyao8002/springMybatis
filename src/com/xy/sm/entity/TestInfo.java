package com.xy.sm.entity;


public class TestInfo extends Entity{

	private Integer id;
	
	private String name;
	
	private String gender;
	
	private String hobby;
	
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String getEntityName() {
		return "testInfo";
	}

	@Override
	public Integer getKey() {
		return id;
	}

	@Override
	public String toString() {
		return "TestInfo [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", hobby=" + hobby + ", age=" + age + "]";
	}
	
}
