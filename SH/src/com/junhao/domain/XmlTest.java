package com.junhao.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlTest {
	private String name;
	private int salary;
	
	public XmlTest() {
		
	}
	public XmlTest(String name,int salary) {
		this.name=name;
		this.salary=salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
