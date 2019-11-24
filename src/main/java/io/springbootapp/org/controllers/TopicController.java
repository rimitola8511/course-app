package io.springbootapp.org.controllers;

import io.springbootapp.org.domains.Topic;
import io.springbootapp.org.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public ResponseEntity<Object> getAllTopics() {
        return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Object> getTopic(@PathVariable String id) {
        return new ResponseEntity<>(topicService.getTopic(id), HttpStatus.OK);
    }

    @PostMapping("/topics")
    public ResponseEntity<Object> addTopic(@Valid @RequestBody Topic topic) {
        topicService.addTopic(topic);
        return new ResponseEntity<>("Topic is created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Object> updateTopic(@Valid @RequestBody Topic topic, @PathVariable String id) {
        topicService.updateTopic(topic);
        return new ResponseEntity<>("Topic "+topic.getId()+" was updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Object> deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>("Topic was delete successfully", HttpStatus.OK);
    }
}
