package lk.ijse.gdse65.posjavaee.TEST;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse65.posjavaee.dto.CustomerDTO;

import java.util.Arrays;

public class test_2 {
    public static void main(String[] args) {
        CustomerDTO customer_1 = new CustomerDTO("C001","Amani",
                "Gomez","amani@gmail.com","Galle");
        CustomerDTO customer_2 = new CustomerDTO("C002","Yasen",
                "Perera","yasen@gmail.com","Dabulla");
        CustomerDTO customer_3 = new CustomerDTO("C003","Shamini",
                "Fonseka","shamini@gmail.com","Jaffna");
        CustomerDTO customer_4 = new CustomerDTO("C004","Kumara",
                "Jayapala","kumara@gmail.com","Ambalangoda");
        CustomerDTO customer_5 = new CustomerDTO("C005","Dinithi",
                "Kaluarachchi","dinithi@gmail.com","Kirinda");

        // create JSON objects
        Jsonb jsonb = JsonbBuilder.create();
        String customer_1_JSON = jsonb.toJson(customer_1);
        String customer_2_JSON = jsonb.toJson(customer_2);
        String customer_3_JSON = jsonb.toJson(customer_3);
        String customer_4_JSON = jsonb.toJson(customer_4);
        String customer_5_JSON = jsonb.toJson(customer_5);

        String arr[] = new String[]{customer_1_JSON,customer_2_JSON,customer_3_JSON,customer_4_JSON,customer_5_JSON};
        System.out.println("String obj array : "+Arrays.toString(arr));

        // convert the array to JSON

        String arrJSON = jsonb.toJson(arr);
        System.out.println("JSON object array : "+arrJSON);
    }
}
