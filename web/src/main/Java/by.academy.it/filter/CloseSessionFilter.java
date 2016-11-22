package by.academy.it.filter;

import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CloseSessionFilter implements Filter {
    final Logger LOG = Logger.getLogger(CloseSessionFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Session session = HibernateUtil.getInstance().getSession();
        HibernateUtil.getInstance().releaseSession(session);
        chain.doFilter(request, response);
        LOG.info("Hibernate session closed successfully");
    }

    public void destroy() {
    }
}
