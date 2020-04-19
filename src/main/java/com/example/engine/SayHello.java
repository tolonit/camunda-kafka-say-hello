package com.example.engine;

import com.google.gson.Gson;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;

public class SayHello {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void execute(ExecutionEntity executionEntity) {

        HashMap<String, String> hashMapMessage = new HashMap<String, String>();

        String bKey = executionEntity.getBusinessKey();
        String topic = (String) executionEntity.getVariable("topic");
        String message = (String) executionEntity.getVariable("message");

        hashMapMessage.put("businessKey", bKey);
        hashMapMessage.put("message", message);

        Gson JSON = new Gson();
        String kafkaMessaxge = JSON.toJson(hashMapMessage);

        kafkaTemplate.send(topic, kafkaMessaxge);

    }

}
