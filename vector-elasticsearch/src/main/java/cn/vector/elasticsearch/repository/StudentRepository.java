package cn.vector.elasticsearch.repository;

import cn.vector.elasticsearch.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By ahrenJ
 * Date: 2020-07-28
 */
@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

    List<Student> findByNameEquals(String name);

    List<Student> findByIdAndName(String id, String name);

    List<Student> findByAgeBetween(Integer min, Integer max);
}
