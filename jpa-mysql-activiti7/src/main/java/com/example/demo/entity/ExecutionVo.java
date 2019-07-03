package com.example.demo.entity;

public class ExecutionVo {
 
	public  String id;
	public  boolean isSuspended;//指示是否暂停执行。
	public  boolean isEnded;//指示是否已结束执行。
	public  String activityId;//返回当前执行所在的活动的id。如果执行不是“叶子”执行，则返回NULL(例如并发父执行)。
	public  String processInstanceId;//表示流程实例的执行树根的ID
	public  String parentId;//获取此执行的父级的id。如果为NULL，则执行表示流程实例。
	public  String superExecutionId;//获取此执行的超级执行的id。
	public  String rootProcessInstanceId;//表示没有超级执行的流程实例的执行树根的ID
	public  String parentProcessInstanceId;//返回与此执行的超级执行相关的流程实例的id。
	public  String tenantId;//此流程实例的租户标识符
	public  String name;//返回此执行的名称。
	public  String description;//返回此执行的说明。
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isSuspended() {
		return isSuspended;
	}
	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}
	public boolean isEnded() {
		return isEnded;
	}
	public void setEnded(boolean isEnded) {
		this.isEnded = isEnded;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSuperExecutionId() {
		return superExecutionId;
	}
	public void setSuperExecutionId(String superExecutionId) {
		this.superExecutionId = superExecutionId;
	}
	public String getRootProcessInstanceId() {
		return rootProcessInstanceId;
	}
	public void setRootProcessInstanceId(String rootProcessInstanceId) {
		this.rootProcessInstanceId = rootProcessInstanceId;
	}
	public String getParentProcessInstanceId() {
		return parentProcessInstanceId;
	}
	public void setParentProcessInstanceId(String parentProcessInstanceId) {
		this.parentProcessInstanceId = parentProcessInstanceId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
