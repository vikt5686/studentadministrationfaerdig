package com.example.studentadministration.repositories;

import com.example.studentadministration.model.Student;
import com.example.studentadministration.util.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements IStudentRepository{



    private Connection conn;
    private static final String INSERT = "INSERT INTO Students(firstName, lastName, enrollmentDate, cpr) VALUES (?, ?, ?, ?)";



    public StudentRepositoryImpl(){
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }




    @Override
    public Student create(Student student){

    Student newStudent = new Student();
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO students(firstName, lastName, enrollmentDate, cpr) VALUES (?, ?, ?, ?");
            ResultSet rs = statement.executeQuery();
             {
                newStudent = new Student();
            newStudent.setFirstName(rs.getString(1));
            newStudent.setLastName(rs.getString(2));
            newStudent.setEnrollmentDate(rs.getDate(3));
            newStudent.setCpr(String.valueOf(rs.getInt(4)));

            }
        }catch (SQLException e) {
            e.printStackTrace();

        }
        return newStudent;
    }



    @Override
    public Student read(int id) {
        Student studentToReturn = new Student();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM students WHERE students_id=?");
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn = new Student();
                studentToReturn.setCpr(String.valueOf(rs.getInt(1)));
                studentToReturn.setFirstName(rs.getString(2));
                studentToReturn.setLastName(rs.getString(3));
                studentToReturn.setEnrollmentDate(rs.getDate(4));
                studentToReturn.setCpr(rs.getString(5));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return studentToReturn;
    }

    @Override
    public List<Student> readAll() {
        List<Student> allStudents = new ArrayList<Student>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student tempStudent = new Student();
                tempStudent.setId(rs.getInt(1));
                tempStudent.setFirstName(rs.getString(2));
                tempStudent.setLastName(rs.getString(3));
                tempStudent.setEnrollmentDate(rs.getDate(4));
                tempStudent.setCpr(rs.getString(5));
                allStudents.add(tempStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {


        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
