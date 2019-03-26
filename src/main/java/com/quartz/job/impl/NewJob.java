package com.quartz.job.impl;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quartz.job.BaseJob;

/**
 * 
 * 新任务 <br>
 * 版权：Copyright (c) 2016-2019<br>
 * 作者：孙常军<br>
 * 版本：1.0<br>
 * 创建日期：2019年3月27日<br>
 */
public class NewJob implements BaseJob{
	private static final Logger logger = LoggerFactory.getLogger(NewJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("当前任务执行时间:{}, {}", new Date(), "newJob");
	}

}
