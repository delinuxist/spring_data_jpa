package com.example.spring.data.jda.repository;

import com.example.spring.data.jda.entity.Course;
import com.example.spring.data.jda.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseNextJs = Course.builder()
                .title("Nextjs Tutorial")
                .credit(8)
                .build();

        Course courseFramerMotion = Course.builder()
                .title("Framer Motion Tutorial")
                .credit(5)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Peter")
                .lastName("Amoako")
//                .courses(List.of(courseNextJs,courseFramerMotion))
                .build();

        teacherRepository.save(teacher);
    }
}