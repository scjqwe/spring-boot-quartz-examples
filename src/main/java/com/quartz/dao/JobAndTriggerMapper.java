package com.quartz.dao;

import java.util.List;

import com.quartz.entity.JobAndTrigger;

/**
 * 	
 * 任务数据访问类 <br>
 * 版权：Copyright (c) 2016-2019<br>
 * 作者：孙常军<br>
 * 版本：1.0<br>
 * 创建日期：2019年3月24日<br>
 */
public interface JobAndTriggerMapper {

	public List<JobAndTrigger> getJobAndTriggerDetails();

}
