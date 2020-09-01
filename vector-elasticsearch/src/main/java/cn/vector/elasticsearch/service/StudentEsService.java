package cn.vector.elasticsearch.service;

import cn.vector.elasticsearch.entity.Student;
import cn.vector.elasticsearch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

/**
 * Create By ahrenJ
 * Date: 2020-07-27
 */
@Service
public class StudentEsService {

    @Autowired
    private ElasticsearchRestTemplate esRestTemplate;

    public Student queryById(String id) {
        return esRestTemplate.get(id, Student.class, IndexCoordinates.of("student_1"));
    }
}
