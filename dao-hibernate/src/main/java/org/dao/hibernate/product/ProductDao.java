package org.dao.hibernate.product;

import org.dao.hibernate.AbstractDao;
import org.domain.product.Product;
import org.vo.product.ProductDto;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ProductDao extends AbstractDao<Product> {

    public PageResponse<Product> querByPage(ProductDto dto, PageRequest request,String desc) throws Exception;

}
