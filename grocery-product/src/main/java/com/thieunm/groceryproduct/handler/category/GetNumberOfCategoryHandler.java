package com.thieunm.groceryproduct.handler.category;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.category.GetNumberOfCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.GetNumberOfCategoryResponse;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetNumberOfCategoryHandler extends QueryHandler<GetNumberOfCategoryRequest, GetNumberOfCategoryResponse> {

    private final CategoryRepository categoryRepository;

    @Override
    public GetNumberOfCategoryResponse handle(GetNumberOfCategoryRequest requestData) {
        return new GetNumberOfCategoryResponse(categoryRepository.count());
    }
}
