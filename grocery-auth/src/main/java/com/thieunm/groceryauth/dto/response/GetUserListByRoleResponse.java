package com.thieunm.groceryauth.dto.response;

import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetUserListByRoleResponse extends QueryResponseData {
    private List<UserResponse> userResponseList;
}
