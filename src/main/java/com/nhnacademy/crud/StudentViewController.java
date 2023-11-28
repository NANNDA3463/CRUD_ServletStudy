package com.nhnacademy.crud;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentViewController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository =
                (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");

        if (Objects.isNull(id)) {
            try {
                resp.sendRedirect("/student/repository");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            try {
                resp.sendRedirect("/student/repository");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //todo student 조회
        log.info("student:{}", student.getId());
        req.setAttribute("student", student);

        //todo view attribute 설정 - /student/view.jsp

        return "/student/view.jsp";
    }
}