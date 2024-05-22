package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.client.cloudinary.ICloudinaryClient;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.DeleteImageClientRequest;
import com.thieunm.groceryproduct.dto.request.product.DeleteProductByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.DeleteProductByIdResponse;
import com.thieunm.groceryproduct.entity.Image;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ImageRepository;
import com.thieunm.groceryproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteProductByIdHandler extends CommandHandler<DeleteProductByIdRequest, DeleteProductByIdResponse> {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ICloudinaryClient cloudinaryClient;

    @Override
    @Transactional
    public DeleteProductByIdResponse handle(DeleteProductByIdRequest requestData) {
        Optional<Product> optionalProduct = productRepository.findById(requestData.getId());
        if (optionalProduct.isEmpty()) {
            throw new NoSuchElementException();
        }
        Product product = optionalProduct.get();
        List<String> cloudinaryIdList = product.getImageList()
                .stream()
                .map(Image::getCloudinaryId)
                .toList();
        cloudinaryClient.deleteImage(new DeleteImageClientRequest(cloudinaryIdList));
        imageRepository.deleteAll(product.getImageList());
        productRepository.delete(product);
        return new DeleteProductByIdResponse(requestData.getId());
    }
}
