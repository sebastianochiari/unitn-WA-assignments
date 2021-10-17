package it.unitn.disi.webarch.sebac.filters;

import it.unitn.disi.webarch.sebac.webchat.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

@WebFilter(
        filterName = "AuthenticationFilter",
        value = "/*",
        initParams = {
                @WebInitParam(name = "admin", value = "password"),
                @WebInitParam(name = "usersFilePath", value = "/Users/sebac/dev/web-architectures/WebChat/utilities/credentials.txt")
        }
)
public class AuthenticationFilter implements Filter {

    protected FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

        ArrayList<User> users = new ArrayList<User>();

        // set admin user
        String adminPassword = this.filterConfig.getInitParameter("admin");
        User tmp = new User("admin", adminPassword, true);
        users.add(tmp);

        // get and set users from file
        String fileName = this.filterConfig.getInitParameter("usersFilePath");
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                String user = stringTokenizer.nextToken();
                String password = stringTokenizer.nextToken();
                tmp = new User(user, password, false);
                users.add(tmp);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // pass the users arraylist to the servlet context
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

        // skip CSS files
        if(uri.endsWith(".css")) {
            chain.doFilter(request,response);
            return;
        }

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
