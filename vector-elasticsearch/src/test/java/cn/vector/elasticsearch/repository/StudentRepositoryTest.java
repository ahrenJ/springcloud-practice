package cn.vector.elasticsearch.repository;

import cn.vector.elasticsearch.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void common() {
    }

    @Test
    void testSave() {
        Student student = new Student();
        student.setId(1);
        student.setAge(14);
        student.setName("王霸");
        studentRepository.save(student);
    }

    @Test
    void testFindByName() {
        List<Student> students = studentRepository.findByNameEquals("李");
        students.forEach(System.out::println);
    }

    @Test
    void testFindByAgeBetween() {
        List<Student> students = studentRepository.findByAgeBetween(10, 15);
        students.forEach(System.out::println);
    }
}