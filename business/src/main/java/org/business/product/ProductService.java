package org.business.product;

import org.business.AbstractService;
import org.domain.product.Product;
import org.vo.product.ProductDto;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ProductService extends AbstractService<Product> {

    PageResponse<Product> queryByPage(ProductDto productDto, PageRequest pageRequest, String desc) throws Exception;


}
