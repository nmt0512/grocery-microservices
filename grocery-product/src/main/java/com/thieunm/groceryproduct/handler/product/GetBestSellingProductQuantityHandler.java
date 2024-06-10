package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.client.payment.IPaymentClient;
import com.thieunm.groceryproduct.client.payment.dto.response.GetBestSellingProductQuantityClientResponse;
import com.thieunm.groceryproduct.client.payment.dto.response.ProductQuantityClientResponse;
import com.thieunm.groceryproduct.dto.request.product.GetBestSellingProductQuantityRequest;
import com.thieunm.groceryproduct.dto.response.product.BestSellingProductResponse;
import com.thieunm.groceryproduct.dto.response.product.GetBestSellingProductQuantityResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBestSellingProductQuantityHandler extends QueryHandler<GetBestSellingProductQuantityRequest, GetBestSellingProductQuantityResponse> {

    private static final int RECENT_DAYS = 30;
    private static final int SIZE = 7;

    private final IPaymentClient paymentClient;
    private final ProductRepository productRepository;

    @Override
    public GetBestSellingProductQuantityResponse handle(GetBestSellingProductQuantityRequest requestData) {
        GetBestSellingProductQuantityClientResponse clientResponse = paymentClient.getBestSellingProductQuantity(RECENT_DAYS, SIZE);
        List<ProductQuantityClientResponse> productQuantityClientResponseList = clientResponse.getProductQuantityClientResponseList();
        List<Product> productList = productRepository.findAllById(productQuantityClientResponseList
                .stream()
                .map(ProductQuantityClientResponse::getId)
                .toList());
        List<BestSellingProductResponse> bestSellingProductResponseList = productQuantityClientResponseList
                .stream()
                .map(productQuantityClientResponse -> {
                    BestSellingProductResponse bestSellingProductResponse =
                            Mapper.map(productQuantityClientResponse, BestSellingProductResponse.class);
                    for (Product product : productList) {
                        if (product.getId() == bestSellingProductResponse.getId()) {
                            bestSellingProductResponse.setName(product.getName());
                            break;
                        }
                    }
                    return bestSellingProductResponse;
                })
                .toList();
        return new GetBestSellingProductQuantityResponse(bestSellingProductResponseList);
    }
}
