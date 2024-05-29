package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.client.payment.IPaymentClient;
import com.thieunm.groceryproduct.dto.request.product.GetRecommendedProductRequest;
import com.thieunm.groceryproduct.dto.response.product.GetRecommendedProductResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRecommendedProductHandler extends QueryHandler<GetRecommendedProductRequest, GetRecommendedProductResponse> {

    private static final int RECENT_DAYS = 30;
    private static final int SIZE = 10;

    private final ProductRepository productRepository;
    private final IPaymentClient paymentClient;
    private final ProductUtil productUtil;

    @Override
    public GetRecommendedProductResponse handle(GetRecommendedProductRequest requestData) {
        List<Integer> productIdList = paymentClient
                .getRecommendedProductIdList(
                        requestData.getAccessToken(),
                        RECENT_DAYS,
                        SIZE
                )
                .getProductIdList();
        List<Product> productList = productIdList.isEmpty()
                ?
                productRepository.findTopDistinctByCategory(SIZE)
                :
                productRepository.findAllById(productIdList);
        List<ProductResponse> productResponseList = productList.stream()
                .map(productUtil::mapProductToProductResponse)
                .toList();
        return new GetRecommendedProductResponse(productResponseList);
    }
}
