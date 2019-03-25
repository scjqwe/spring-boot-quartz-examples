package com.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 页面控制器<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 公司: 活力天汇<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年3月25日<br>
 */
@Controller
public class PageController {

	@RequestMapping({ "", "/", "/index" })
	public String index() {
		return "index";
	}

}
