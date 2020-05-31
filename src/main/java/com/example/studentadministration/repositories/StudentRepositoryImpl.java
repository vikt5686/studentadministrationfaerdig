package com.example.studentadministration.repositories;

import com.example.studentadministration.model.Student;
import com.example.studentadministration.util.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Date.valueOf;
import static javax.swing.UIManager.getInt;
import static javax.swing.UIManager.getString;

public class StudentRepositoryImpl implements IStudentRepository{



    private Connection conn;



    public StudentRepositoryImpl(){
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }




    @Override
    public boolean create(Student student){


        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO students (student_id, first_name, last_name, enrollment_date, cpr_nr) VALUES (?, ?, ?, ?, ?)");

            SimpleDateFormat convert = new SimpleDateFormat("yyyy-MM-dd");
           String dateInCorrectFormat = convert.format(student.getEnrollmentDate());
            System.out.println(dateInCorrectFormat);
                statement.setInt(1, student.getId());
                statement.setString(2, student.getFirstName());
                statement.setString(3, student.getLastName());
                statement.setString(4, dateInCorrectFormat);
                statement.setString(5, student.getCpr());
                statement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);

        }
        return true;
    }

    private Date getDate(int i) {
        return null;
    }


    @Override
    public Student read(int id) {
        Student studentToReturn = new Student();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM students WHERE student_id="+id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn = new Student();
                studentToReturn.setId((rs.getInt(1)));
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


        try {
            PreparedStatement updatestatement = conn.prepareStatement("UPDATE students SET student_id = ?, first_name = ?, last_name = ?, enrollment_date = ?, cpr_nr = ? WHERE student_id = ?");

            SimpleDateFormat convert = new SimpleDateFormat("yyyy-MM-dd");
            String dateInCorrectFormat = convert.format(student.getEnrollmentDate());
            System.out.println(dateInCorrectFormat);

            updatestatement.setInt(1, student.getId());
            updatestatement.setString(2, student.getFirstName());
            updatestatement.setString(3, student.getLastName());
            updatestatement.setString(4, dateInCorrectFormat);
            updatestatement.setString(5, student.getCpr());
            updatestatement.setInt(6, student.getId());
            updatestatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public boolean delete(int id) {

        try {
            PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM students WHERE student_id = ?");

            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


}
