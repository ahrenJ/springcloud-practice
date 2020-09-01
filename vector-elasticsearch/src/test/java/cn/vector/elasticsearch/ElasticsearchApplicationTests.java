package cn.vector.elasticsearch;

import cn.vector.elasticsearch.entity.Book;
import cn.vector.elasticsearch.entity.Student;
import cn.vector.elasticsearch.service.StudentEsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    private StudentEsService studentEsService;
    @Autowired
    private ElasticsearchRestTemplate esRestTemplate;
    @Autowired
    private ElasticsearchOperations esOperations;

    @Test
    void contextLoads() {
        Student student = studentEsService.queryById("1");
        System.out.println(student);
    }

    @Test
    void common() {
        Student student = new Student();
        student.setId(3);
        student.setName("小碧宰治");
        student.setAge(23);

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(student)
                .build();
        esRestTemplate.index(indexQuery, IndexCoordinates.of("student"));
    }

    @Test
    void common2() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termsQuery("tag", "Java", "数据库"))
                .build();
        SearchHits<Book> searchHits = esRestTemplate.search(query, Book.class);
        searchHits.get().forEach(hits -> {
            System.out.println(hits.getContent().toString());
        });
    }

    @Test
    void common3() throws JsonProcessingException {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders
                        .avg("avg_price")
                        .field("price"))
                .build();
        SearchHits<Book> searchHits = esRestTemplate.search(query, Book.class);
        ParsedAvg parsedAvg = searchHits.getAggregations().get("avg_price");
        System.out.println(parsedAvg.getValue());
    }
}
