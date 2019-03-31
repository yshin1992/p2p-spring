package org.p2p.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang.StringUtils;
import org.p2p.util.DateUtil.DateFormatStr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDateProcessor implements JsonValueProcessor {

	 private static Logger logger = LoggerFactory.getLogger(JsonDateProcessor.class);

	    private DateFormat dateFormat;
	    
	    private void initialization(){
			if (logger.isInfoEnabled()){
				logger.info("JsonDateProcessor.initialization");
			}
			this.dateFormat = new SimpleDateFormat();
		}
		
		public JsonDateProcessor(DateFormatStr datePattern){
			if(logger.isInfoEnabled()){
				logger.info("constoractor.constoractor parameter:"+datePattern);
			}
			try {
				dateFormat = new SimpleDateFormat(datePattern.getValue());
			} catch (Exception e) {
				logger.error("constoractor.constoractor error:" + e.getMessage());
				initialization();
			}
		}
		
		public JsonDateProcessor(){
			initialization();
		}
	    
	    @Override
	    public Object processArrayValue(Object arrayValue, JsonConfig arg1) {
	        if(logger.isDebugEnabled()) {
	            logger.debug("JsonDateProcessor.processArrayValue.parameter:"+arrayValue.getClass().getName());
	        }
	        String[] strDateArray = {};
	        if (arrayValue instanceof Date[]){
	            Date[] dates = (Date[])arrayValue;
	            strDateArray = new String[dates.length];
	            for (int i=0; i < dates.length; i++){
	                strDateArray[i] = this.dateFormat.format(dates[i]);
	            }
	        }
	        if (arrayValue instanceof Timestamp[]) {
	            Timestamp[] tmstps = (Timestamp[])arrayValue;
	            strDateArray = new String[tmstps.length];
	            for (int i=0; i < tmstps.length; i++){
	                strDateArray[i] = this.dateFormat.format(tmstps[i]);
	            }
	        }
	        if (logger.isDebugEnabled()) {
	            logger.debug("JsonDateProcessor.processArrayValue.returnValue.length:"+strDateArray.length);
	        }
	        return strDateArray;
	    }

	    /**
	     * 
	     * */
	    @Override
	    public Object processObjectValue(String key, Object value, JsonConfig arg2) {
	        if (logger.isDebugEnabled()) {
	            logger.debug("JsonDateProcessor.processObjectValue.parameter:"+key+","+(value==null?"null":value.toString()));
	        }
	        String strDate = StringUtils.EMPTY;
	        if (value instanceof Date){
	            strDate = this.dateFormat.format((Date)value);
	        }
	        if(value instanceof Timestamp) {
	            strDate = this.dateFormat.format((Timestamp)value);
	        }
	        if (logger.isDebugEnabled()) {
	            logger.debug("JsonDateProcessor.processObjectValue.returnValue:"+strDate);
	        }
	        return strDate;
	    }

}
