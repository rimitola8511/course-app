package io.springbootapp.org.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.springbootapp.org.domains.Topic;
import io.springbootapp.org.infraestructure.customexcetions.TopicNotFoundException;
import io.springbootapp.org.services.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
public class TestTopicController {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TopicService topicService;

    @Test
    void getAllTopicsTest() throws Exception{
        when(topicService.getAllTopics()).thenReturn(new ArrayList<>());
        MvcResult result = mvc.perform(get("/topics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(topicService.getAllTopics().isEmpty());
        verify(topicService, times(2)).getAllTopics();
    }

    @Test
    void getTopicTest() throws Exception {
        given(topicService.getTopic("java"))
                .willReturn(new Topic("java", "", ""));
        mvc.perform(get("/topics/java"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getTopicAndReturnAExceptionTest () {
        when(topicService.getTopic(null))
                .thenThrow(TopicNotFoundException.class);
    }

    @Test
    void addTopicTest () throws Exception {
        Topic topic = new Topic("java", "Java Core", "Java Core Description");
        mvc.perform(post("/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topic)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void updateTopicTest () throws Exception{
        Topic topic = new Topic("java", "Nuevo", "Nuevo");
        mvc.perform(put("/topics/"+topic.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topic)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void deleteTopicTest () throws Exception {
        Topic topic = new Topic("java", "Nuevo", "Nuevo");
        mvc.perform(delete("/topics/"+topic.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
