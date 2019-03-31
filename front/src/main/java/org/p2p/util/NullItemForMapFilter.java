package org.p2p.util;

import java.util.Iterator;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class NullItemForMapFilter implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		if (arg1 instanceof JSONObject) {
			JSONObject jo = (JSONObject)arg1;
			@SuppressWarnings("rawtypes")
			Iterator it = jo.keys();
			while(it.hasNext()) {
				Object key = it.next();
				Object value = jo.get(key);
				if ( value instanceof JSONNull){
					jo.put(key, "");
				}
			}
				
		}
		return arg1;
	}

}
