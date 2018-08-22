package com.zy.entity;

public class Grade {
	private Integer id;
	private String gradeName;
	private Integer grade;
	private Integer parentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Grade [id=" + id + ", gradeName=" + gradeName + ", grade=" + grade + ", parentId=" + parentId + "]";
	}
	
	
}
