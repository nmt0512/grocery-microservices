package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.SearchProductRequest;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.dto.response.product.SearchProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProductHandler extends QueryHandler<SearchProductRequest, SearchProductResponse> {

    private final ProductRepository productRepository;
    private final ProductUtil productUtil;

    @Override
    public SearchProductResponse handle(SearchProductRequest requestData) {
        if (requestData.getQuery().isBlank()) {
            return new SearchProductResponse(new ArrayList<>());
        } else {
            Pageable pageable = PageRequest.of(0, 8);
            List<Product> productList = productRepository.findByNameContainingIgnoreCase(requestData.getQuery(), pageable);
            List<ProductResponse> productResponseList = productList
                    .stream()
                    .map(productUtil::mapProductToProductResponse)
                    .toList();
            return new SearchProductResponse(productResponseList);
        }
    }
}
