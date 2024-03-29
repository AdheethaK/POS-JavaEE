package lk.ijse.gdse65.posjavaee.api;

import jakarta.json.Json;
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
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(name = "customer",urlPatterns = "/customer")
public class Customer extends HttpServlet {
    final static Logger logger = LoggerFactory.getLogger(Customer.class);
    Connection connection;

    public void init(){
        logger.info("Init the Customer Servlet");

        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            System.out.println(" DataSource pool : "+pool);
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override // save
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else {

//            System.out.println(req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(),CustomerDTO.class);
            CustomerDBProcess customerDBProcess = new CustomerDBProcess();
            boolean isSaved = customerDBProcess.save(customerDTO,connection);

            if (isSaved){
                logger.info("customer successfully saved :)");
            } else {
                logger.info("error in customer save :(");
            }

        }
    }

    @Override // search
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDBProcess customerDBProcess = new CustomerDBProcess();
        String id = req.getParameter("customer_id");

        if (id == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else {
            CustomerDTO customerDTO = customerDBProcess.search(id,connection);
//            System.out.println(customerDTO);

            //send data
            Jsonb jsonb = JsonbBuilder.create();
            String customerJSON = jsonb.toJson(customerDTO);
//            System.out.println(customerJSON);
            resp.getWriter().write(customerJSON);
        }
    }

    @Override // update
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else {

//            System.out.println(req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(),CustomerDTO.class);
            CustomerDBProcess customerDBProcess = new CustomerDBProcess();
            customerDBProcess.update(customerDTO,connection);
        }
    }

    @Override // delete
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDBProcess customerDBProcess = new CustomerDBProcess();
        String id = req.getParameter("id");

        if (id == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else {
            boolean isDeleted = customerDBProcess.delete(id,connection);
            logger.info("customer deleted "+isDeleted+" :)");
        }
    }
}
