package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.client.cloudinary.ICloudinaryClient;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.DeleteImageClientRequest;
import com.thieunm.groceryproduct.dto.request.product.UpdateProductRequest;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.dto.response.product.UpdateProductResponse;
import com.thieunm.groceryproduct.entity.Category;
import com.thieunm.groceryproduct.entity.Image;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import com.thieunm.groceryproduct.repository.ImageRepository;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateProductHandler extends CommandHandler<UpdateProductRequest, UpdateProductResponse> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ICloudinaryClient cloudinaryClient;
    private final ProductUtil productUtil;

    @Override
    public UpdateProductResponse handle(UpdateProductRequest requestData) {
        Product updatingProduct = productRepository.findById(requestData.getId()).orElseThrow(NoSuchElementException::new);
        List<Image> imageList = updatingProduct.getImageList();

        Product updatedProduct = Mapper.map(requestData, updatingProduct);
        Category category = categoryRepository.findById(requestData.getCategoryId()).get();
        updatedProduct.setCategory(category);
        updatedProduct = productRepository.save(updatedProduct);

        List<Image> deletedImageList = imageList.stream()
                .filter(image -> !requestData.getImageUrlList().contains(image.getUrl()))
                .toList();
        // DELETE Images if the ImageURLList size in Request is less than old ImageURLList
        if (!deletedImageList.isEmpty()) {
            cloudinaryClient.deleteImage(new DeleteImageClientRequest(deletedImageList.stream()
                    .map(Image::getCloudinaryId)
                    .toList()));
            imageRepository.deleteAll(deletedImageList);
        }

        // SAVE Images of Product if Request contains new FileList of Images
        if (requestData.getFileList() != null) {
            productUtil.saveProductImages(updatedProduct, requestData.getFileList());
        }

        ProductResponse productResponse = productUtil.mapProductToProductResponse(updatedProduct);
        return new UpdateProductResponse(productResponse);
    }
}
