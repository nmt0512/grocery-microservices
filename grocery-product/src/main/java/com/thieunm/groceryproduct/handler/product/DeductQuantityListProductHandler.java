package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.dto.request.product.DeductProductRequest;
import com.thieunm.groceryproduct.dto.request.product.DeductQuantityListProductRequest;
import com.thieunm.groceryproduct.dto.response.product.DeductQuantityListProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeductQuantityListProductHandler extends CommandHandler<DeductQuantityListProductRequest, DeductQuantityListProductResponse> {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public DeductQuantityListProductResponse handle(DeductQuantityListProductRequest requestData) {
        List<DeductProductRequest> deductProductRequestList = requestData.getDeductProductRequestList();
        deductProductRequestList.forEach(deductProductRequest -> {
            Product product = productRepository.findById(deductProductRequest.getProductId()).get();
            int newQuantity = product.getQuantity() - deductProductRequest.getDeductingQuantity();
            product.setQuantity(newQuantity);
            productRepository.save(product);
        });
        List<Integer> deductedProductIdList = deductProductRequestList.stream()
                .map(DeductProductRequest::getProductId)
                .toList();
        return new DeductQuantityListProductResponse(deductedProductIdList);
    }
}
