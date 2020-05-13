package com.example.studentadministration.repositories;

import com.example.studentadministration.model.Student;

import java.util.List;

public interface IStudentRepository {
    // CRUD operations
    public Student create(Student student);

    public Student read(int id);

    public List<Student> readAll();

    public boolean update(Student student);

    public boolean delete(int id);
}
