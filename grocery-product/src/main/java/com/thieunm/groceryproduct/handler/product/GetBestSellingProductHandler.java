package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.client.payment.IPaymentClient;
import com.thieunm.groceryproduct.dto.request.product.GetBestSellingProductRequest;
import com.thieunm.groceryproduct.dto.response.product.GetBestSellingProductResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBestSellingProductHandler extends QueryHandler<GetBestSellingProductRequest, GetBestSellingProductResponse> {

    private static final int RECENT_DAYS = 30;
    private static final int SIZE = 10;

    private final ProductRepository productRepository;
    private final IPaymentClient paymentClient;
    private final ProductUtil productUtil;

    @Override
    public GetBestSellingProductResponse handle(GetBestSellingProductRequest requestData) {
        List<Integer> productIdList = paymentClient.getBestSellingProductIdList(RECENT_DAYS, SIZE).getProductIdList();
        List<Product> productList = productRepository.findAllById(productIdList);
        List<ProductResponse> productResponseList = productList.stream()
                .map(productUtil::mapProductToProductResponse)
                .toList();
        return new GetBestSellingProductResponse(productResponseList);
    }
}
