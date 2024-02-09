package lk.ijse.gdse65.posjavaee.dto;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO implements Serializable {
    private String customer_id;
    private String customer_first_name;
    private String customer_last_name;
    private String customer_email;
    private String customer_address;
}
