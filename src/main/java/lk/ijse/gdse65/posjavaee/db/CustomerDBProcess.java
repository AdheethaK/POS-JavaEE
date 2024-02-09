package lk.ijse.gdse65.posjavaee.db;

import lk.ijse.gdse65.posjavaee.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDBProcess {
    final static Logger logger = LoggerFactory.getLogger(CustomerDBProcess.class);

    private static final String SAVE = "INSERT INTO customer (id,first_name,last_name,email,address) VALUES(?,?,?,?,?)";
    private static final String UPDATE = "UPDATE customer SET first_name=?,last_name=?,email=?,address=? WHERE id=?";
    private static final String SEARCH = "SELECT * FROM customer WHERE id=?";
    private static final String DELETE = "DELETE FROM customer WHERE id=?";

    public void save(CustomerDTO customerDTO, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE);
            ps.setString(1, customerDTO.getCustomer_id());
            ps.setString(2, customerDTO.getCustomer_first_name());
            ps.setString(3, customerDTO.getCustomer_last_name());
            ps.setString(4, customerDTO.getCustomer_email());
            ps.setString(5, customerDTO.getCustomer_address());

            if (ps.executeUpdate() != 0){
                System.out.println("customer successfully saved :)");
            }else{
                System.out.println("error in customer save :(");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void update(CustomerDTO customerDTO,Connection connection){

    }

    public void search(String id,Connection connection){

    }

    public void delete(String id,Connection connection){

    }

}
