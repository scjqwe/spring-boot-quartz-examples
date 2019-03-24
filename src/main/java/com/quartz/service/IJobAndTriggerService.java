package com.quartz.service;

import com.github.pagehelper.PageInfo;
import com.quartz.entity.JobAndTrigger;

public interface IJobAndTriggerService {
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
