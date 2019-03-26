package com.quartz.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * 测试<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年3月25日<br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobAndTriggerMapperTest {
	private static final Logger logger = LoggerFactory.getLogger(JobAndTriggerMapperTest.class);

	@Autowired
	private JobAndTriggerMapper mapper;

	@Test
	public void test1() throws Exception {
		logger.info("{}", mapper.getJobAndTriggerDetails(null, null));
	}

}
