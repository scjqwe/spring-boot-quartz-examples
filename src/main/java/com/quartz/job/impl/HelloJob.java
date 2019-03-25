package com.quartz.job.impl;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quartz.job.BaseJob;

/*
 * 简单任务
 */
public class HelloJob implements BaseJob{
	private static final Logger logger = LoggerFactory.getLogger(HelloJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("当前任务执行时间:{}, {}", new Date(), "sayHello");
	}

}
