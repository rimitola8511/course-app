package io.springbootapp.org.infraestructure.customexcetions;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String id) {
        super("Could not find Topic " + id);
    }
}
