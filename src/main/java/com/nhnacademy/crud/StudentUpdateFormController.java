package com.nhnacademy.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentUpdateFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository =
                (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        Student student = studentRepository.getStudentById(req.getParameter("id"));
        req.setAttribute("student", student);

        return "/student/register.jsp";
    }
}