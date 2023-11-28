package com.nhnacademy.crud;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentRegisterController implements Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository =
                (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        //todo null check
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");

        log.info(id);
        log.info(name);
        log.info(gender);
        log.info(age);

        if (Objects.isNull(id) || id.isEmpty() || Objects.isNull(name) || name.isEmpty() || Objects.isNull(gender) ||
                gender.isEmpty() || Objects.isNull(age) || age.isEmpty()) {
            try {
                RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
                rd.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        Student student = new Student(id, name, Gender.getGender(gender), Integer.parseInt(age), LocalDateTime.now());
        studentRepository.save(student);
        return "redirect:/student/view.do?id=" + student.getId();
    }
}
