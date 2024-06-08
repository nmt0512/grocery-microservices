package com.thieunm.grocerynotify.kafka.message;

import com.thieunm.grocerybase.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotifyRequest {
    private NotifyType type;
    private String title;
    private String body;
    private String to;
}
