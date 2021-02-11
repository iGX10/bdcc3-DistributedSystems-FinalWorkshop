package org.sid.kafkaconsumerservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private BillRepository billRepository;

    @KafkaListener(topics = "FACTURATION", groupId = "ensetm")
    public void onMessage(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        Bill bill = makeBillFromJsonString(consumerRecord.value());
        billRepository.save(bill);
        System.out.println("Bill received : "+bill.toString());
    }

    private Bill makeBillFromJsonString(String jsonBill) throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.readValue(jsonBill, Bill.class);
    }
}
