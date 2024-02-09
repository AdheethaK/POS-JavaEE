package lk.ijse.gdse65.posjavaee.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "customer",urlPatterns = "/customer")
public class Customer extends HttpServlet {
    @Override // create
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("          POST works :)         ");
        System.out.println("*******************************");
    }

    @Override // read  search
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("          GET works :)          ");
        System.out.println("*******************************");
    }

    @Override // update
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("          PUT works :)          ");
        System.out.println("*******************************");
    }

    @Override // delete
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("          DELETE works :)          ");
        System.out.println("*******************************");
    }
}
