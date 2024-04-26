package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.PageSupport;
import com.thieunm.grocerybase.cqrs.query.PageSupportQueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetProductByCategoryIdPagingRequest;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductByCategoryIdPagingHandler extends PageSupportQueryHandler<GetProductByCategoryIdPagingRequest, ProductResponse> {

    private final ProductRepository productRepository;
    private final ProductUtil productUtil;

    @Override
    public PageSupport<ProductResponse> handle(GetProductByCategoryIdPagingRequest requestData) {
        Pageable pageable = requestData.getPageable();
        List<Product> productList = productRepository.findByCategoryId(requestData.getCategoryId(), pageable);
        List<ProductResponse> productResponseList = productList
                .stream()
                .map(productUtil::mapProductToProductResponse)
                .toList();
        return new PageSupport<>(
                productResponseList,
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                productResponseList.size()
        );
    }
}
