package com.thieunm.groceryauth.dto.request;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class GetUserInfoRequest extends QueryRequestData {
}
