package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.dto.request.product.DeductQuantityListProductRequest;
import com.thieunm.groceryproduct.dto.request.product.DeductingProduct;
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
        List<DeductingProduct> deductingProductList = requestData.getDeductingProductList();
        deductingProductList.forEach(deductingProduct -> {
            Product product = productRepository.findById(deductingProduct.getProductId()).get();
            int newQuantity = product.getQuantity() - deductingProduct.getDeductingQuantity();
            product.setQuantity(newQuantity);
            productRepository.save(product);
        });
        return new DeductQuantityListProductResponse(deductingProductList);
    }
}
