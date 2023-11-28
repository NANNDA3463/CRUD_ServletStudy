package com.nhnacademy.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH =
            "/Users/minsoo/NHN/ServletStudy/CRUD/src/main/json/student.json";

    public JsonStudentRepository() {
        objectMapper = new ObjectMapper();
        //LocalDatetime json 직열화/역직렬화 가능하도록 설정
        objectMapper.registerModule(new JavaTimeModule());
        //todo JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제 합니다.

        File file = new File(JSON_FILE_PATH);
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
    }

    private synchronized List<Student> readJsonFile() {
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴

        //json read & 역직렬화 ( json string -> Object )
        File file = new File(JSON_FILE_PATH);

        if (!file.exists() && !file.isDirectory()) {
            List<Student> list = new ArrayList<>();
            return list;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            List<Student> students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {
            });
            return students;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<Student> studentList) {
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH);

        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter, studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        //json String -> Object 형태로 변화 (직렬화)
        List<Student> students = readJsonFile();
        //List에 student 추가
        students.add(student);
        //List<Student>객체를 -> json String 형태로 저장(직렬화)
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {

        if (this.existById(student.getId())) {

            Student fileStudent = this.getStudentById(student.getId());

            this.deleteById(fileStudent.getId());

            fileStudent.setAge(student.getAge());
            fileStudent.setGender(student.getGender());
            fileStudent.setName(student.getName());
            this.save(fileStudent);
        }

    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();

        Iterator<Student> studentIterator = students.iterator();

        while (studentIterator.hasNext()) {
            Student fileStudent = studentIterator.next();
            if (fileStudent.getId().equals(id)) {
                studentIterator.remove();
                writeJsonFile(students);
                return;
            }
        }

        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();

        for (Student fileStudent : students) {
            if (fileStudent.getId().equals(id)) {
                return fileStudent;
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();

        for (Student fileStudent : students) {
            if (fileStudent.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    //todo 나머지 method는 직접 구현하기
}