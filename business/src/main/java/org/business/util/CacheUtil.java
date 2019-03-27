package org.business.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.business.system.CategoryService;
import org.domain.system.Category;
import org.domain.system.CategoryAttr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.vo.DropDownDto;

@Component("cacheUtil")
public class CacheUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CacheUtil.class);

	private static CacheManager cacheManager;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	public void setCacheManager(CacheManager cacheManager) {
		CacheUtil.cacheManager = cacheManager;
	}

	@PostConstruct
	@Transactional
	public void init() {
		logger.debug("1.环境初始化开始");

		List<Category> categories = this.categoryService.findAll();
		for (Category category : categories) {
			Set<CategoryAttr> attrs = category.getAttrs();
			if ((attrs != null) && (!attrs.isEmpty())) {

				if (category.getConfiged().intValue() == 1) {
					for (CategoryAttr categoryAttr : attrs) {
						if (categoryAttr.getActualval() != null) {
							cacheManager.getCache("category").put(
									categoryAttr.getAttrCd(),
									categoryAttr.getActualval());
						}
						logger.debug("{}={}", categoryAttr.getAttrCd(),
								categoryAttr.getActualval());
					}
				} else
					try {
						List<DropDownDto> attrList = new ArrayList<DropDownDto>();
						attrList.add(new DropDownDto("", "全部"));
						for (CategoryAttr attr : attrs) {
							attrList.add(new DropDownDto(attr.getAttrCd(), attr
									.getAttrNm(), attr.isEnabled(), attr
									.getActualval(), attr.getDefaultVal()));
						}
						cacheManager.getCache("category").put(
								category.getCategoryCd(), attrList);
						logger.debug("{}={}", category.getCategoryCd(), attrList);
					} catch (Exception e) {
						logger.error("读取配置错误:{}", e.getMessage());
					}
			}
		}
		logger.debug("1.环境初始化完成");
	}

	public void refresh() {
		cacheManager.getCache("category").clear();
		init();
	}

	public static String getProperty(String key, String defaultValue) {
		String value = cacheManager.getCache("category").get(key,
				String.class);
		return value == null ? defaultValue : value;
	}

	public static String getConfig(String key) {
		return getProperty(key);
	}

	public static String getStr(String key) {
		return getProperty(key);
	}

	public static String getStr(String key, String defValue) {
		return getProperty(key, defValue);
	}

	public static Boolean getBoolean(String key) {
		String s1 = getProperty(key);
		return s1 != null ? Boolean.valueOf("1".equals(s1)) : null;
	}

	public static Boolean getBoolean(String key, Boolean defValue) {
		String s1 = getProperty(key);
		return Boolean.valueOf(s1 != null ? "1".equals(s1) : defValue
				.booleanValue());
	}

	public static Integer getInt(String key) {
		String s1 = getProperty(key);
		return s1 != null ? Integer.valueOf(Integer.parseInt(s1)) : null;
	}

	public static Integer getInt(String key, Integer defValue) {
		String s1 = getProperty(key);
		return Integer.valueOf(s1 != null ? Integer.parseInt(s1) : defValue
				.intValue());
	}

	public static Double getDouble(String key) {
		String s1 = getProperty(key);
		return s1 != null ? Double.valueOf(Double.parseDouble(s1)) : null;
	}

	public static Double getDouble(String key, Double defValue) {
		String s1 = getProperty(key);
		return Double.valueOf(s1 != null ? Double.parseDouble(s1) : defValue
				.doubleValue());
	}

	public static String getConfig(String key, String defaultValue) {
		String value = cacheManager.getCache("category").get(key,
				String.class);
		return value == null ? defaultValue : value;
	}

	public static List<DropDownDto> getConfigList(String key) {
		try {
			@SuppressWarnings("unchecked")
			List<DropDownDto> jsonStr = cacheManager.getCache("category").get(
					key, List.class);
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DropDownDto>();
	}

	public static DropDownDto getConfigList(String key, String attrCd) {
		List<DropDownDto> dtos = getConfigList(key);
		for (DropDownDto d : dtos) {
			if ((d.getAttrCd() != null) && (attrCd != null)
					&& (d.getAttrCd().equals(attrCd))) {
				return d;
			}
		}
		return null;
	}

	public static void setProperty(String key, String value) {
		cacheManager.getCache("category").put(key, value);
	}

	public static String getProperty(String key) {
		return getProperty(key, "");
	}
}
