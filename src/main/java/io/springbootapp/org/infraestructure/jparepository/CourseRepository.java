package io.springbootapp.org.infraestructure.jparepository;

import io.springbootapp.org.domains.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

    List<Course> findByTopicId(String topicId);
}
