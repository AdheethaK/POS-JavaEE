package lk.ijse.gdse65.posjavaee.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CORSFilter extends HttpFilter {
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("CORS origin :)");

        System.out.println("CORSFilter HTTP request : "+request.getMethod());

//        //Allow all domains to consume the content
//        res.addHeader("Access-Control-Allow-Origin","*");
//        res.addHeader("Access-Control-Allow-Methods","GET,HEAD,POST,PUT,DELETE,CONNECT,OPTIONS,TRACE,PATCH");
//
//        System.out.println("origin : "+req.getHeader("origin"));
//
//        //For HTTP OPTIONS verb/method reply with ACCEPTED status code - per CORS handshake
//        if (req.getMethod().equals("GET,HEAD,POST,PUT,DELETE,CONNECT,OPTIONS,TRACE,PATCH")){
//            res.setStatus(res.SC_ACCEPTED);
//            return;
//        }
//
//        //pass the request along the filter chain
//        chain.doFilter(req,res);

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,CONNECT,OPTIONS,TRACE,PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        chain.doFilter(request, response);

        System.out.println("----------------------------------");
    }
}
