package com.nhnacademy.crud;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();

        StudentRepository studentRepository =
                (StudentRepository) servletContext.getAttribute("studentRepository");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        log.info("값 가져오기!" + id + name + gender + age);

        Student tempStudent = studentRepository.getStudentById(id);

        Student student =
                new Student(id, name, Gender.getGender(gender), Integer.parseInt(age), tempStudent.getCreatedAt());

        log.warn(student.getId() + student.getName());
        studentRepository.update(student);

        servletContext.setAttribute("studentRepository", studentRepository);

        return "redirect:/student/view.do?id=" + id;
    }
}