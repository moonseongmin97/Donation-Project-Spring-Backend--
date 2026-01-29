package com.example.demo.donate.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object message) {
        kafkaTemplate.send(topic, message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        //log.info("메시지 전송 성공: {}", result.getRecordMetadata().offset());
                    } else {
                        //log.error("메시지 전송 실패", ex);
                    }
                });
    }
}