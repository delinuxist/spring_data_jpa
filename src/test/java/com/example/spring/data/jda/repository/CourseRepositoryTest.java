package com.example.spring.data.jda.repository;

import com.example.spring.data.jda.entity.Course;
import com.example.spring.data.jda.entity.Guardian;
import com.example.spring.data.jda.entity.Student;
import com.example.spring.data.jda.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;
    
    @Test
    public void getCourses() {
        List<Course> courseList = courseRepository.findAll();
        System.out.println("courseList = " + courseList);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Martina")
                .lastName("Pierce")
                .build();

        Course coursePostgres = Course.builder()
                .title("Postgres Tutorial")
                .credit(18)
                .teacher(teacher)
                .build();

        courseRepository.save(coursePostgres);
    }
    
    @Test
    public void findAllRepository() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1,2);
        
        List<Course> courses =  courseRepository.findAll(secondPageWithTwoRecords).getContent();
        
        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();
        
        long totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();


        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(
                0,
                2,
                Sort.by("title")
        );

        Pageable sortByCreditDesc = PageRequest.of(
                0,
                2,
                Sort.by("credit")
                        .descending()
        );
        
        Pageable sortByTitleAndCredit = PageRequest.of(
                0,
                4,
                Sort.by("title")
                .descending()
                        .and(Sort.by("credit")
                                .descending()
                        )
        );

        List<Course> coursesSortedByTitle = courseRepository.findAll(sortByTitle).getContent();
        List<Course> coursesSortByCredit = courseRepository.findAll(sortByCreditDesc).getContent();
        List<Course> coursesSortByTitleAndCredit = courseRepository.findAll(sortByTitleAndCredit).getContent();

        System.out.println("coursesSortByCredit = " + coursesSortByCredit);
        System.out.println("coursesSortedByTitle = " + coursesSortedByTitle);
        System.out.println("coursesSortByTitleAndCredit = " + coursesSortByTitleAndCredit);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Ana")
                .lastName("Queen")
                .build();

        Guardian guardian = Guardian.builder()
                .email("Steve@gmail.com")
                .name("Steve")
                .mobile("0272741467")
                .build();

        Student student = Student.builder()
                .firstName("George")
                .lastName("Smith")
                .email("george@gmail.com")
                .guardian(guardian)
                .build();

        Course courseMysql = Course.builder()
                .title("Mysql Tutorial")
                .credit(18)
                .teacher(teacher)
                .build();

        courseMysql.addStudents(student);

        courseRepository.save(courseMysql);
    }
}