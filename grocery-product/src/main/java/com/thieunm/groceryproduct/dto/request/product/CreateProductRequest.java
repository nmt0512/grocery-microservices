package com.thieunm.groceryproduct.dto.request.product;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest extends CommandRequestData {
    private int categoryId;
    private String name;
    private int quantity;
    private int unitPrice;
    private String description;
    private List<MultipartFile> fileList;
}
