package lk.ijse.gdse65.posjavaee.db;

import lk.ijse.gdse65.posjavaee.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDBProcess {
    final static Logger logger = LoggerFactory.getLogger(ItemDBProcess.class);

    private static final String SAVE = "INSERT INTO item (code,type,url,name,qty,price) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE item SET type=?,url=?,name=?,qty=?,price=? WHERE code=?";
    private static final String SEARCH = "SELECT * FROM item WHERE code=?";
    private static final String GET_ALL = "SELECT * FROM item";
    private static final String DELETE = "DELETE FROM item WHERE code=?";

    public boolean save(ItemDTO itemDTO, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE);
            ps.setString(1,itemDTO.getItem_code());
            ps.setString(2,itemDTO.getItem_type());
            ps.setString(3,itemDTO.getItem_image());
            ps.setString(4,itemDTO.getItem_name());
            ps.setString(5,itemDTO.getItem_quantity());
            ps.setString(6,itemDTO.getItem_price());
            return (ps.executeUpdate()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
