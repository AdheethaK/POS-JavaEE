package lk.ijse.gdse65.posjavaee.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse65.posjavaee.db.CustomerDBProcess;
import lk.ijse.gdse65.posjavaee.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "customerGetAll" , urlPatterns = "/customerGetAll")
public class CustomerGetAll extends HttpServlet {
    final static Logger logger = LoggerFactory.getLogger(Customer.class);
    Connection connection;

    public void init(){
        logger.info("Init the CustomerGetAll Servlet");

        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            System.out.println(" DataSource pool : "+pool);
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDBProcess customerDBProcess = new CustomerDBProcess();
        ArrayList<CustomerDTO> customerDTOS = customerDBProcess.getAllCustomers(connection);

        ArrayList<String> customerJSONArray = new ArrayList<String>();
        // create JSON objects
        Jsonb jsonb = JsonbBuilder.create();
        for (CustomerDTO customerDTO : customerDTOS){
            customerJSONArray.add(jsonb.toJson(customerDTO));
        }

        // convert the array to JSON
        String customerJSONArrayJSON = jsonb.toJson(customerJSONArray);
        resp.getWriter().write(customerJSONArrayJSON);
    }
}
