package org.business.product.impl;

import org.business.AbstractServiceImpl;
import org.business.product.ProductService;
import org.dao.hibernate.product.ProductDao;
import org.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vo.product.ProductDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Service("productService")
public class ProductServiceImpl extends AbstractServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public PageResponse<Product> queryByPage(ProductDto productDto, PageRequest pageRequest, String desc) throws Exception {
        return productDao.querByPage(productDto,pageRequest,desc);
    }
}
