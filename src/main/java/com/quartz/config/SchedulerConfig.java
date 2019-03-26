package com.quartz.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class SchedulerConfig {

	/**
	 * 配置任务工厂实例
	 * 
	 * @param applicationContext
	 *            spring上下文实例
	 * @return
	 */
	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		CustomJobFactory jobFactory = new CustomJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws IOException {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		// 将spring管理job自定义工厂交由调度器维护
		schedulerFactoryBean.setJobFactory(jobFactory);
		// 设置覆盖已存在的任务
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		// 项目启动完成后，等待10秒后开始执行调度器初始化
		schedulerFactoryBean.setStartupDelay(10);
		// 设置调度器自动运行
		schedulerFactoryBean.setAutoStartup(true);
		// 设置数据源，使用与项目统一数据源
		schedulerFactoryBean.setDataSource(dataSource);
		// 设置上下文spring bean name
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
		// 设置配置文件位置
		schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
		return schedulerFactoryBean;
	}

	/**
	 * 继承org.springframework.scheduling.quartz.SpringBeanJobFactory<br>
	 * 实现任务实例化方式
	 */
	public class CustomJobFactory extends SpringBeanJobFactory {

		@Autowired
		private AutowireCapableBeanFactory capableBeanFactory;

		@Override
		protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
			// 调用父类的方法
			Object jobInstance = super.createJobInstance(bundle);
			// 进行注入
			capableBeanFactory.autowireBean(jobInstance);
			return jobInstance;
		}
	}

}
