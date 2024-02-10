package lk.ijse.gdse65.posjavaee.db;

import lk.ijse.gdse65.posjavaee.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, customerDTO.getCustomer_first_name());
            ps.setString(2, customerDTO.getCustomer_last_name());
            ps.setString(3, customerDTO.getCustomer_email());
            ps.setString(4, customerDTO.getCustomer_address());
            ps.setString(5, customerDTO.getCustomer_id());

            if (ps.executeUpdate() != 0){
                logger.info("customer successfully saved :)");
            }else{
                logger.info("error in customer save :(");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CustomerDTO search(String id,Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement(SEARCH);
            ps.setString(1,id);

            ResultSet rst = ps.executeQuery();

            while (rst.next()){
                return new CustomerDTO(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String id,Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setString(1,id);

            return (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
