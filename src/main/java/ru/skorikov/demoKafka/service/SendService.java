package ru.skorikov.demoKafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.skorikov.demoKafka.dto.AmountTransferDto;
import ru.skorikov.demoKafka.dto.Message;
import ru.skorikov.demoKafka.dto.ReferalToPartnerDto;
import ru.skorikov.demoKafka.dto.UserEntity;
import ru.skorikov.demoKafka.dto.UserMapper;

@Component
public class SendService {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Value(value = "${bet.kafka.consumers.referal.topic}")
    private String topicReferal;
    @Value(value = "${bet.kafka.consumers.comission.topic}")
    private String topicComission;
    @Autowired
    private UserMapper userMapper;

    public void sendAmountTransfer(AmountTransferDto transferDto) {

        kafkaTemplate.send(topicComission, transferDto);
    }

    public void sendNewReferral(UserEntity userEntity) {
//        kafkaReferralTemplate.send(sendReferral.name(), referalDto);
        ReferalToPartnerDto referal = userMapper.userDtoFromUserEntity(userEntity);
        System.out.println("Отпарвляем в кафку:..." + referal);
        kafkaTemplate.send("referal", referal);
    }

    @KafkaListener(topics = "comission", containerFactory = "amountTransferListenerContainerFactory")
    public void listenAmountTransfer(AmountTransferDto amountTransferDto) {
        //TODO: расчитать, записать комиссию партнеру(партнерам)
        System.out.println("Получено:.... " + amountTransferDto);
    }

    @KafkaListener(topics = "referal", containerFactory = "newReferralListenerContainerFactory")
    public void listenNewReferal(ReferalToPartnerDto referalToPartnerDto) {
        //TODO: Добавить партнеру его реферала.
        System.out.println("Получено из кафки:.... " + referalToPartnerDto);
        UserEntity userEntity = userMapper.userEntityFromUserDto(referalToPartnerDto);
        System.out.println("Cформировали ентити:...." + userEntity.getRegion());
    }
}
