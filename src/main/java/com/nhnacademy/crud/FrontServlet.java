package com.nhnacademy.crud;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        try {
            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            String view = command.execute(req, resp);

//            RequestDispatcher rd = req.getRequestDispatcher(view);
//            rd.include(req, resp);
            log.info("현재 뷰 : " + view);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                // todo  `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length() + 1));

            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                log.info(view + "로 이동!");
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception ex) {
            //todo 공통 error 처리 - ErrorServlet 참고해서 처리
            throw new RuntimeException();

            //todo  forward - /error.jsp

        }
    }

    private Command resolveCommand(String servletPath, String method) {
        Command command = null;
        if ("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentListController();
        } else if ("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentViewController();
            log.info("view 확인!");
        } else if ("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new StudentDeleteController();
        } else if ("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentUpdateFormController();
        } else if ("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new StudentUpdateController();
        } else if ("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentRegisterFormController();
        } else if ("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new StudentRegisterController();
        }
        return command;
    }

}