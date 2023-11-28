package com.nhnacademy.crud;

import java.time.LocalDateTime;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new JsonStudentRepository();

        for (int i = 1; i <= 10; i++) {
            studentRepository.save(
                    new Student(
                            "Student" + i,
                            "아카데미" + i,
                            Gender.getRandomEnum(),
                            new Random().nextInt(11) + 20,
                            LocalDateTime.now()));
        }

        context.setAttribute("studentRepository", studentRepository);
    }
}
