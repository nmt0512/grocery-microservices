package com.thieunm.grocerybase.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thieunm.grocerybase.enums.SystemStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QueryResponseData extends ResponseData {
    @JsonIgnore
    private SystemStatus status;
}
