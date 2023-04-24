package com.example.spring.data.jda.repository;

import com.example.spring.data.jda.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Student findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName,String lastName);

    // ?1 - first parameter (JPQL)
    @Query("select s from Student s where s.email=?1")
    Student getStudentByEmailAddress(String email);

    //native sql query
    @Query(
            nativeQuery = true,
            value = "select * from tbl_student s where s.email_address = ?1"
    )
    Student getStudentByEmailAddressNative(String email);

    // Native Named Param
    @Query(
            nativeQuery = true,
            value = "select * from tbl_student s where s.email_address=:email"
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("email")String email);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update tbl_student set first_name=:firstName where email_address=:email"
    )
    void updateStudentNameByEmailId(@Param("firstName")String firstName,@Param("email")String email);
}
