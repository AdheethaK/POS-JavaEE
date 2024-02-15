package lk.ijse.gdse65.posjavaee.TEST;

import jakarta.json.Json;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.Arrays;

public class test_1 {
    public static void main(String[] args) {
        // create the string array
        String arr_1[] = new String[]{"id : E001","name : kasun madushka","age : 45","address : galle"};

        System.out.print("String array :");
        System.out.println(Arrays.toString(arr_1));
        System.out.println("---------------------");
        Jsonb jsonb = JsonbBuilder.create();
        System.out.print("JSON array :");
        String arr_1JSON = jsonb.toJson(arr_1);
        System.out.println(arr_1JSON);
    }
}
