package com.quartz.entity;

import java.math.BigInteger;

/**
 * 
 * 任务实体类 <br>
 * 版权：Copyright (c) 2016-2019<br>
 * 作者：孙常军<br>
 * 版本：1.0<br>
 * 创建日期：2019年3月24日<br>
 */
public class JobAndTrigger {

	private String jobName;

	private String jobGroup;

	private String jobClassName;

	private String triggerName;

	private String triggerGroup;

	private BigInteger repeatInterval;

	private BigInteger timesTriggered;

	private String cronExpression;

	private String timeZoneId;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public BigInteger getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(BigInteger repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public BigInteger getTimesTriggered() {
		return timesTriggered;
	}

	public void setTimesTriggered(BigInteger timesTriggered) {
		this.timesTriggered = timesTriggered;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	@Override
	public String toString() {
		return "JobAndTrigger [jobName=" + jobName + ", jobGroup=" + jobGroup + ", jobClassName=" + jobClassName + ", triggerName=" + triggerName + ", triggerGroup="
				+ triggerGroup + ", repeatInterval=" + repeatInterval + ", timesTriggered=" + timesTriggered + ", cronExpression=" + cronExpression + ", timeZoneId=" + timeZoneId
				+ "]";
	}

}
