package io.springbootapp.org.infraestructure.jparepository;

import io.springbootapp.org.domains.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {

}
