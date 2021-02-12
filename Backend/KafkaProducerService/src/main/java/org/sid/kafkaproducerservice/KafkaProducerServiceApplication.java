package org.sid.kafkaproducerservice;

import org.sid.kafkaproducerservice.web.ProducerController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableKafka
@EnableFeignClients
public class KafkaProducerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProducerController producerController) {
        return args -> {
            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
                String topic = "OPERATIONS";
                producerController.send(topic);
                System.out.println("Operations sent ...");
            }, 1000, 1000, TimeUnit.MILLISECONDS);
        };
    }

}
