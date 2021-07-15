package ru.skorikov.demoKafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.skorikov.demoKafka.dto.AmountTransferDto;
import ru.skorikov.demoKafka.dto.ReferalToPartnerDto;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${bet.kafka.producer.bootstrap}")
    private String bootstrapAddress;

    public ConsumerFactory<String, String> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    public ConsumerFactory<String, AmountTransferDto> amountTransferConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(AmountTransferDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AmountTransferDto> amountTransferListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AmountTransferDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(amountTransferConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, ReferalToPartnerDto> newReferralConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(ReferalToPartnerDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReferalToPartnerDto> newReferralListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReferalToPartnerDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newReferralConsumerFactory());
        return factory;
    }

}
