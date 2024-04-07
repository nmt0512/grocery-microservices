package com.thieunm.groceryproduct.utils;

import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Image;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryutils.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ProductUtil {

    @Value("${cloudinary.product-url.regex}")
    private String cloudinaryProductUrlRegex;

    @Value("${cloudinary.product-url.resized-regex}")
    private String cloudinaryResizedProductUrlRegex;

    public String convertNameToCode(String name) {
        String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern
                .matcher(temp)
                .replaceAll("")
                .toLowerCase()
                .replaceAll(" ", "-")
                .replaceAll("đ", "d");
    }

    public String getResizedImageUrl(String imageUrl) {
        return imageUrl.replaceFirst(cloudinaryProductUrlRegex, cloudinaryResizedProductUrlRegex);
    }

    public ProductResponse mapProductToProductResponse(Product product) {
        ProductResponse productResponse = Mapper.map(product, ProductResponse.class);
        List<Image> imageList = product.getImageList();
        List<String> imageUrlList = imageList
                .stream()
                .map(Image::getUrl)
                .toList();
        productResponse.setImageUrlList(imageUrlList);
        return productResponse;
    }
}
