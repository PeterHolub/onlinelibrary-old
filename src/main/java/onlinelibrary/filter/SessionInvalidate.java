package onlinelibrary.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//Invalidates session after redirect to login.jsp & register.jsp
@WebFilter(filterName = "SessionInvalidate")
public class SessionInvalidate implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        session.invalidate();
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
    }
        @Override
    public void destroy() {
    }
}

