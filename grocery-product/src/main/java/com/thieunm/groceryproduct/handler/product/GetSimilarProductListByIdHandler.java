package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.client.payment.IPaymentClient;
import com.thieunm.groceryproduct.dto.request.product.GetSimilarProductListByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.GetSimilarProductListByIdResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetSimilarProductListByIdHandler extends QueryHandler<GetSimilarProductListByIdRequest, GetSimilarProductListByIdResponse> {

    private static final int RECENT_DAYS = 30;
    private static final int SIZE = 10;
    private static final int PRODUCT_LIST_SIZE = 5;

    private final ProductRepository productRepository;
    private final ProductUtil productUtil;

    private final IPaymentClient paymentClient;

    @Override
    public GetSimilarProductListByIdResponse handle(GetSimilarProductListByIdRequest requestData) {
        List<Integer> bestSellingProductIdList = paymentClient
                .getBestSellingProductIdList(RECENT_DAYS, SIZE)
                .getProductIdList();
        List<Product> bestSellingProductList = productRepository.findAllById(bestSellingProductIdList);
        Product requestProduct = productRepository.findById(requestData.getId()).orElseThrow(NoSuchElementException::new);
        int requestProductCategoryId = requestProduct.getCategory().getId();
        bestSellingProductList.removeIf(product -> product.getCategory().getId() != requestProductCategoryId
                || product.getId() == requestData.getId());
        if (bestSellingProductList.size() < PRODUCT_LIST_SIZE) {
            int querySize = PRODUCT_LIST_SIZE - bestSellingProductList.size();
            List<Product> addingProductList = productRepository.findTopRandomByCategoryIdAndIdNot(
                    requestProductCategoryId,
                    requestData.getId(),
                    querySize);
            addingProductList.forEach(product -> {
                if (!bestSellingProductList.contains(product)) {
                    bestSellingProductList.add(product);
                }
            });
        }
        List<ProductResponse> productResponseList = bestSellingProductList
                .stream()
                .map(productUtil::mapProductToProductResponse)
                .toList();
        return new GetSimilarProductListByIdResponse(productResponseList);
    }
}
