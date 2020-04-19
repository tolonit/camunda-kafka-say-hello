package com.example.engine;

import com.google.gson.Gson;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;

public class SayHelloDelegate implements JavaDelegate {

    private Expression topic;
    private Expression message;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;   // allways null

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        HashMap<String, String> hashMapMessage = new HashMap<String, String>();

        String bKey = execution.getBusinessKey();
        String topic = (String) execution.getVariable("topic");
        String message = (String) execution.getVariable("message");

        hashMapMessage.put("businessKey", bKey);
        hashMapMessage.put("message", message);

        Gson JSON = new Gson();
        String kafkaMessaxge = JSON.toJson(hashMapMessage);

        System.out.println(" !!!!  The class implements JavaDelegate won’t work with autowired !!! ");

        // kafkaTemplate.send(topic, kafkaMessaxge);
    }

}
