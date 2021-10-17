package it.unitn.disi.webarch.sebac.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "AdminFilter",
        value = "/admin/*"
)
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // check if user owning the session is admin
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");

        // user is an admin
        if(isAdmin) {
            chain.doFilter(request, response);
        } else { // user is not an admin
            httpResponse.sendRedirect("/home");
        }
    }

}
