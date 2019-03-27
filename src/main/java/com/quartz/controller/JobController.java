package com.quartz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.quartz.entity.JobAndTrigger;
import com.quartz.job.BaseJob;
import com.quartz.service.IJobAndTriggerService;
import com.quartz.util.ResultUtils;

/**
 * 
 * 任务调度控制器<br>
 * 版权：Copyright (c) 2016-2019<br>
 * 作者：孙常军<br>
 * 版本：1.0<br>
 * 创建日期：2019年3月24日<br>
 */
@Controller
@RequestMapping("/job")
public class JobController {
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private IJobAndTriggerService iJobAndTriggerService;

	@Autowired
	private Scheduler scheduler;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@PostMapping(value = "/add")
	public String addJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
		jobDetail.isConcurrentExectionDisallowed();
		
		// 表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			logger.error("Exception:", e);
		}
		
		return "redirect:/index";
	}

	@PostMapping(value = "/pause")
	@ResponseBody
	public Object pauseJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		try {
			scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
			return ResultUtils.getSuccessResult("操作成功");
		} catch (Exception e) {
			logger.error("Exception:", e);
			return ResultUtils.getFailResult("抱歉，出现一点小故障");
		}

	}

	@PostMapping(value = "/resume")
	@ResponseBody
	public Object resumeJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		try {
			scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
			return ResultUtils.getSuccessResult("操作成功");
		} catch (Exception e) {
			logger.error("Exception:", e);
			return ResultUtils.getFailResult("抱歉，出现一点小故障");
		}

	}

	@PostMapping(value = "/reschedule")
	public String rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			logger.error("Exception:", e);
		}
		
		return "redirect:/index";
	}

	@PostMapping(value = "/delete")
	@ResponseBody
	public Object deleteJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
			scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
			scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
			return ResultUtils.getSuccessResult("操作成功");
		} catch (Exception e) {
			logger.error("Exception:", e);
			return ResultUtils.getFailResult("抱歉，出现一点小故障");
		}

	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object listJob(@RequestParam(value = "page") Integer page, @RequestParam(value = "limit") Integer limit, String jobClassName, String jobGroupName) {
		PageInfo<JobAndTrigger> pageInfo = iJobAndTriggerService.getJobAndTriggerDetails(page, limit, jobClassName, jobGroupName);
		Map<String, Object> result = ResultUtils.getSuccessResult();
		result.put("data", pageInfo.getList());
		result.put("count", pageInfo.getTotal());
		return result;
	}

	private BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}

}
