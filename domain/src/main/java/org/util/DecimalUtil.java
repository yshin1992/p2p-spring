package org.util;

import java.math.BigDecimal;

import org.springframework.util.ObjectUtils;

public class DecimalUtil {

	protected static BigDecimal movePoint(BigDecimal value, boolean nullable, int n, boolean moveLeft){
		if(ObjectUtils.isEmpty(value)){
			return nullable?null:new BigDecimal("0.00");
		}
		BigDecimal copy = new BigDecimal(value.toString());
		return moveLeft?copy.movePointLeft(n):copy.movePointRight(n);
	}
	
	public static BigDecimal toPercent(BigDecimal rate, boolean nullable){
		return movePoint(rate, nullable, 2, false);
	}
	
	public static BigDecimal toPercent(BigDecimal value){
		return toPercent(value,true);
	}
	
	public static BigDecimal fromPercent(BigDecimal percent, boolean nullable){
		return movePoint(percent, nullable, 2, true);
	}
	
	public static BigDecimal fromPercent(BigDecimal percent){
		return fromPercent(percent,true);
	}
}
