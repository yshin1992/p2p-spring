package org.business.system.impl;

import java.util.List;

import org.business.system.CategoryService;
import org.dao.hibernate.system.CategoryDao;
import org.domain.system.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

}
