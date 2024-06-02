package com.thieunm.groceryproduct.dto.response.category;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCategoryByIdResponse extends CommandResponseData {
    private int deletedCategoryId;
}
