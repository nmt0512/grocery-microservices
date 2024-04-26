package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.cqrs.query.PageSupport;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetProductByCategoryIdPagingRequest;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetProductByCategoryIdPagingController extends BaseController {

    @GetMapping
    ResponseEntity<BaseResponse<PageSupport<ProductResponse>>> getProductByCategoryIdPaging(
            @RequestParam(value = "categoryId") Integer categoryId,
            @RequestParam(value = "page") @Min(1) int pageNumber,
            @RequestParam(value = "size") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        GetProductByCategoryIdPagingRequest request = GetProductByCategoryIdPagingRequest.builder()
                .categoryId(categoryId)
                .pageable(pageable)
                .build();
        return executePagination(request, ProductResponse.class);
    }
}
