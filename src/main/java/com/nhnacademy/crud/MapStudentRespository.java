package com.nhnacademy.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRespository implements StudentRepository {
    private final Map<String, Student> studentMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        this.studentMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        save(student);
    }

    @Override
    public void deleteById(String id) {
        this.studentMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        return this.studentMap.get(id);
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(this.studentMap.values());
    }

    @Override
    public boolean existById(String id) {
        return this.studentMap.containsKey(id);
    }
}
