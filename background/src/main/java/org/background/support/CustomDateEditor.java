package org.background.support;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

public class CustomDateEditor extends PropertyEditorSupport {

    private static final String DEFAULT_PARTTEN = "yyyy-MM-dd HH:mm:ss";

    private static final String SHORT_PATTREN = "yyyy-MM-dd";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getAsText() {
        Object value = getValue();
        return value==null? "": DateFormatUtils.format((Date)value,DEFAULT_PARTTEN);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        logger.error("自定义日期属性编辑器：--->{}",text);
        if(StringUtils.hasText(text)){
            try {
                setValue(DateUtils.parseDate(text,DEFAULT_PARTTEN,SHORT_PATTREN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            setValue(null);
        }

    }
}
