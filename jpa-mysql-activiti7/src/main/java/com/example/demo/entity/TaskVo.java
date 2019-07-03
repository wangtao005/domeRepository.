package com.example.demo.entity;

import java.util.Date;
import java.util.Map;

public class TaskVo {

	public  String id;
	public  String name;//任务的名称或标题
	public  String description;//任务的文本描述
	public   int priority;//表明这项任务的重要性/紧迫性
	public  String owner;//负责此任务的人员的
	public  String assignee;//将此任务委托给的人员的
	public  String processInstanceId;//引用流程实例，如果它与流程实例无关，则为NULL。
	public  String executionId;//如果路径与流程实例无关，则引用执行路径或NULL。
	public  String processDefinitionId;//引用流程定义，如果它与流程无关，则为NULL。
	public   Date createTime;//创建此任务的日期/时间。
	public  String taskDefinitionKey;//流程中定义此任务的活动的id，如果这与进程无关，则为NULL。
	public   Date dueDate;//任务的截止日期。
	public  String category;//任务的类别。这是一个可选字段，允许将任务标记为属于特定类别的任务。
	public  String parentTaskId;//此任务为子任务的父任务。
	public  String tenantId;//此任务的租户标识符。
	public  String formKey;//
	public   Map<String, Object> taskLocalVariables;//如果任务查询中请求，则返回本地任务变量。
	public   Map<String, Object> processVariables;//如果任务查询中请求，则返回进程变量。
	public   Date claimTime;//此任务的索赔时间
	public   boolean isSuspended;//指示是否挂起此任务。
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	public Map<String, Object> getTaskLocalVariables() {
		return taskLocalVariables;
	}
	public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
		this.taskLocalVariables = taskLocalVariables;
	}
	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}
	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}
	public Date getClaimTime() {
		return claimTime;
	}
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}
	public boolean isSuspended() {
		return isSuspended;
	}
	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}
	  
}
