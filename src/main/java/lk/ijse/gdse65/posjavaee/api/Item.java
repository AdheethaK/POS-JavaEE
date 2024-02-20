package lk.ijse.gdse65.posjavaee.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse65.posjavaee.db.ItemDBProcess;
import lk.ijse.gdse65.posjavaee.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "item",urlPatterns = "/item")
public class Item extends HttpServlet {
    final static Logger logger = LoggerFactory.getLogger(Item.class);
    Connection connection;
    public void init(){
        logger.info("Init the Item Servlet");
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override //save
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else{
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(),ItemDTO.class);
            ItemDBProcess itemDBProcess = new ItemDBProcess();
            boolean isSaved = itemDBProcess.save(itemDTO,connection);
            if (isSaved){
                logger.info("Item successfully saved :)");
            }else{
                logger.info("error in item save :(");
            }
        }
    }
}
