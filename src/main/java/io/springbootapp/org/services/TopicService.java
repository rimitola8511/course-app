package io.springbootapp.org.services;

import io.springbootapp.org.domains.Topic;
import io.springbootapp.org.infraestructure.customexcetions.TopicNotFoundException;
import io.springbootapp.org.infraestructure.jparepository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add); //Java 8
        return topics;
    }

    public Topic getTopic(String id) {
        return topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(Topic topic) {
        Topic updateTopic = topicRepository.findById(topic.getId()).orElseThrow(() -> new TopicNotFoundException(topic.getId()));
        if (updateTopic.getId() != null) {
            topicRepository.save(topic);
        }
    }

    public void deleteTopic(String id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
        if (topic.getId() != null) {
            topicRepository.delete(topic);
        }
    }
}
