package lk.ijse.gdse65.posjavaee.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ItemDTO implements Serializable {
    private String item_code;
    private String item_type;
    private String item_image;
    private String item_name;
    private String item_quantity;
    private String item_price;
}
