package it.unitn.disi.webarch.sebac.memorygame.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(
        filterName = "AuthenticationFilter",
        value = "/*",
        initParams = {
                @WebInitParam(name = "usersFilePath", value = "/Users/sebac/dev/web-architectures/MemoryGame/utilities/users.txt")
        }
)
public class AuthenticationFilter implements Filter {

    protected FilterConfig filterConfig;

    @Override
    public void init(FilterConfig config) throws ServletException {
        // copy the filter configuration
        this.filterConfig = config;
        // create an empty array list for collecting users
        ArrayList<String> users = new ArrayList<String>();
        // get users from file
        String fileName = this.filterConfig.getInitParameter("usersFilePath");
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                users.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // set the users arraylist as servlet context variable
        this.filterConfig.getServletContext().setAttribute("users", users);
        this.filterConfig.getServletContext().setAttribute("usersFile", fileName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI().toString();

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = uri.equals("/login");

        // if user is logged in and asks /login, redirected to index
        if(loggedIn && loginRequest) {
            res.sendRedirect("IndexServlet");
            return;
        }
        // check if user is logged in and request
        if(loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/login");
        }
    }
}
