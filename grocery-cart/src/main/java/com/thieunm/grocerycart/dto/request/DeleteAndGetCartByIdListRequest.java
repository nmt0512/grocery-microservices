package com.thieunm.grocerycart.dto.request;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAndGetCartByIdListRequest extends CommandRequestData {
    private List<String> idList;
}
