package iuh.fit.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    String orderId;
    String message;
    Date timestamp = new Date();
}
