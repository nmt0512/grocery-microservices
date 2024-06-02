package com.thieunm.groceryproduct.dto.request.category;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest extends CommandRequestData {
    private String name;
    private String code;
    private MultipartFile multipartFile;
}
