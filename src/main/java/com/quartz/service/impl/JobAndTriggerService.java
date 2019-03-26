package com.quartz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quartz.dao.JobAndTriggerMapper;
import com.quartz.entity.JobAndTrigger;
import com.quartz.service.IJobAndTriggerService;

/**
 * 
 * 任务操作业务类 <br>
 * 版权：Copyright (c) 2016-2019<br>
 * 作者：孙常军<br>
 * 版本：1.0<br>
 * 创建日期：2019年3月24日<br>
 */
@Service
public class JobAndTriggerService implements IJobAndTriggerService {

	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;

	@Override
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize, String jobClassName, String jobGroupName) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails(jobClassName, jobGroupName);
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}

}
