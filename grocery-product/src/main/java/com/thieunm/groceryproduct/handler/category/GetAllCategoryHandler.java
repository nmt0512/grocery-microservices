package com.thieunm.groceryproduct.handler.category;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.category.GetAllCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.CategoryResponse;
import com.thieunm.groceryproduct.dto.response.category.GetAllCategoryResponse;
import com.thieunm.groceryproduct.entity.Category;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCategoryHandler extends QueryHandler<GetAllCategoryRequest, GetAllCategoryResponse> {

    private final CategoryRepository categoryRepository;

    @Override
    public GetAllCategoryResponse handle(GetAllCategoryRequest requestData) {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = Mapper.mapList(categoryList, CategoryResponse.class);
        return GetAllCategoryResponse
                .builder()
                .categoryResponseList(categoryResponseList)
                .build();
    }
}
