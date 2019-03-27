package org.dao.hibernate.system;

import java.util.List;

import org.domain.system.Category;

public interface CategoryDao {
	
	public Category findByCd(String code);
	
	public List<Category> findAll();
}
