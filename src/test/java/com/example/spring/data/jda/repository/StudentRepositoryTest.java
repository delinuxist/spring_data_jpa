package com.example.spring.data.jda.repository;

import com.example.spring.data.jda.entity.Guardian;
import com.example.spring.data.jda.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .email("emma@gmail.com")
                .firstName("Emmanuel")
                .lastName("Obeng")
//                .guardianName("Jake")
//                .guardianEmail("jake@gmail.com")
//                .guardianMobile("0242444456")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .email("mike@gmail.com")
                .name("Mike")
                .mobile("0242444456")
                .build();

        Student student = Student.builder()
                .firstName("Ella")
                .lastName("Attuahene")
                .email("ella@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void getStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }
    
    @Test
    public void getStudentByFirstName(){
        Student student = studentRepository.findByFirstName("Ella");
        System.out.println("student = " + student);
    }

    @Test
    public void getStudentByFirstNameContaining(){
        List<Student> student = studentRepository.findByFirstNameContaining("E");
        System.out.println("students = " + student);
    }

    @Test
    public void getStudentByLastNameNotNull() {
        List<Student> studentList = studentRepository.findByLastNameNotNull();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void getStudentByGuardianName() {
        List<Student> student = studentRepository.findByGuardianName("Mike");
        System.out.println("students = " + student);
    }
    
    @Test
    public void getStudentByFirstNameAndLastName() {
        Student student = studentRepository.findByFirstNameAndLastName("Ella","Attuahene");
        System.out.println("student = " + student);
    }
    
    @Test
    public void getStudentByEmail() {
        Student student = studentRepository.getStudentByEmailAddress("emma@gmail.com");
        System.out.println("student = " + student);
    }
    
    @Test
    public void getStudentByEmailNativeQuery() {
        Student student = studentRepository.getStudentByEmailAddressNative("ella@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void getStudentByEmailNativeQueryNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("ella@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailId() {
        studentRepository.updateStudentNameByEmailId("Emmanuella","ella@gmail.com");
    }
}