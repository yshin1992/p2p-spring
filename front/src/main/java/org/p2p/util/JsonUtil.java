package org.p2p.util;

import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

public class JsonUtil {

	/**
	 * 将JSON字符串转换成指定的JavaBean或者Map<br/>
	 * 转换失败返回null
	 * @param jsonStr JSON格式字符串
	 * @param type 转换目标类型
	 * @return objE 转换后的JavaBean
	 */
	public static <E> E toBeanOrMap(String jsonStr,Class<E> type){
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateProcessor());
		jsonConfig.registerJsonValueProcessor("data", new NullItemForMapFilter());
		jsonConfig.registerJsonValueProcessor(Object.class, new NullItemForMapFilter());
		jsonConfig.registerJsonValueProcessor(HashMap.class, new NullItemForMapFilter());
		JSONObject jsonObj = JSONObject.fromObject(jsonStr,jsonConfig);
		
		Object resultObject = JSONObject.toBean(jsonObj, type);
		
		E objE;
		if (resultObject != null) {
			objE = (E)type.cast(resultObject);
			return objE;
		} else {
			return null;
		}
	}
	
}
