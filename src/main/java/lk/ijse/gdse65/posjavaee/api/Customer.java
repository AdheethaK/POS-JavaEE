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

//            Jsonb jsonb = JsonbBuilder.create();
//            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(),CustomerDTO.class);
//            CustomerDBProcess customerDBProcess = new CustomerDBProcess();
//            customerDBProcess.save(customerDTO,connection);

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
//            System.out.println("String obj array : "+ Arrays.toString(arr));

            // convert the array to JSON

            String arrJSON = jsonb.toJson(arr);
//            System.out.println("JSON object array : "+arrJSON);
            resp.getWriter().write(arrJSON);
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
//        String id = req.getParameter("customer_id");

        System.out.println("id : "+req.getParameter("customer_id"));

//        if (id == null){
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//        }else {
//////            boolean isDeleted = customerDBProcess.delete(id,connection);
//////            System.out.println(customerDTO);
////            logger.info("customer deleted "+isDeleted+" :)");
//        }
    }
}
