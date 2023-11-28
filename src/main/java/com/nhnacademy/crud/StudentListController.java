package com.nhnacademy.crud;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentListController implements Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository =
                (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        //student list 구하기
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);

        //todo view attribute - /student/list.jsp
        return "/student/list.jsp";
    }
}
