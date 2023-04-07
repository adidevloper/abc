package com.example.blog.Exception;

public class ResourceNotFoundExcpetion extends RuntimeException {

	String resourcename;
	String fieldname;
	long fieldvalue;
	public ResourceNotFoundExcpetion(String resourcename, String fieldname, long fieldvalue) {
		super(String.format("%s not found with %s : %s",resourcename,fieldname,fieldvalue));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
		}
	
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public long getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	 
}
