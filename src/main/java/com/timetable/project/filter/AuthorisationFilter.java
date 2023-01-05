package com.timetable.project.filter;

import com.timetable.project.entity.Student;
import com.timetable.project.entity.Timetabler;
import com.timetable.project.student.StudentService;
import com.timetable.project.timetabler.TimetablerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
@WebFilter(urlPatterns = {"/*"})
public class AuthorisationFilter implements Filter
{
    private final StudentService studentService;
    private final TimetablerService timetablerService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI().toLowerCase();

        if (requestURI.contains("checkcredentials") ||
                requestURI.contains("create"))
        {
            // No authorisation required
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (timetablerIsAuthorised(request))
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (studentIsAuthorised(request))
        {
            System.out.println(servletResponse);
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean timetablerIsAuthorised(HttpServletRequest request)
    {
        String requestURI = request.getRequestURI().toLowerCase();
        System.out.println("TIMETABLER REQUEST URI: " + requestURI);
        String token = request.getHeader("AUTHORIZATION");

        Timetabler timetabler = timetablerService.checkCredentials(token);

        if (timetabler != null)
        {
            if (requestURI.contains("modulecatalogue") || requestURI.contains("scheduleclass") ||
                    requestURI.startsWith("/timetabler/delete/") ||
                    requestURI.startsWith("/timetabler/get/") ||
                    requestURI.startsWith("/timetabler/update/"))
            {
                return true;
            }
            else if (requestURI.startsWith("/timetabler/logout/"))
            {
                String[] parts = requestURI.substring(1).split("/");
                int id = Integer.parseInt(parts[2]);
                return timetabler.getId() == id;
            }
        }

        return false;
    }

    private boolean studentIsAuthorised(HttpServletRequest request)
    {
        String requestURI = request.getRequestURI().toLowerCase();
        System.out.println("STUDENT REQUEST URI: " + requestURI);
        String token = request.getHeader("AUTHORIZATION");

        Student student = studentService.checkCredentials(token);

        if (student != null)
        {
            if (requestURI.startsWith("/student/get/") ||
                    (requestURI.startsWith("/student/modules/")) ||
                    (requestURI.startsWith("/student/timetable"))
            )
            {
                String[] parts = requestURI.substring(1).split("/");
                int id = Integer.parseInt(parts[2]);
                return student.getId() == id;
            }
        }

        return false;
    }
}
