package com.quartz.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 返回结果工具类<br>
 * 版权：Copyright (c) 2016-2019<br>
 * 作者：孙常军<br>
 * 版本：1.0<br>
 * 创建日期：2019年3月26日<br>
 */
public class ResultUtils {
	/** 返回成功结果 */
	public static Map<String, Object> getSuccessResult() {
		return getSuccessResult("success");
	}

	/** 返回成功结果 */
	public static Map<String, Object> getSuccessResult(String msg) {
		return getSuccessResult(msg, null);
	}

	/** 返回成功结果 */
	public static Map<String, Object> getSuccessResult(Map<String, Object> data) {
		return getSuccessResult("success", data);
	}

	/** 返回成功结果 */
	public static Map<String, Object> getSuccessResult(List<Object> data) {
		return getSuccessResult("success", data);
	}

	/** 返回成功结果 */
	public static Map<String, Object> getSuccessResult(String msg, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}

	/** 返回失败结果 */
	public static Map<String, Object> getFailResult() {
		return getFailResult("fail");
	}

	/** 返回失败结果 */
	public static Map<String, Object> getFailResult(String msg) {
		return getFailResult(msg, null);
	}

	/** 返回失败结果 */
	public static Map<String, Object> getFailResult(Map<String, Object> data) {
		return getFailResult("fail", data);
	}

	/** 返回失败结果 */
	public static Map<String, Object> getFailResult(String msg, Map<String, Object> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}

	/** 是否成功 */
	public static Boolean isSuccess(Map<String, Object> result) {
		if (result == null) {
			return Boolean.FALSE;
		}

		Integer code = (Integer) result.get("code");
		return code == null || code != 1 ? Boolean.FALSE : Boolean.TRUE;
	}

	/** 是否失败 */
	public static Boolean isFail(Map<String, Object> result) {
		return !isSuccess(result);
	}
}
