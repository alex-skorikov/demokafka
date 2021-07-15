package ru.skorikov.demoKafka;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import ru.skorikov.demoKafka.dto.AmountTransferDto;
import ru.skorikov.demoKafka.dto.UserEntity;
import ru.skorikov.demoKafka.service.SendService;

@SpringBootApplication
public class DemoKafkaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoKafkaApplication.class, args);

		SendService producer = context.getBean(SendService.class);

		for (int i = 1; i < 5; i++){

			UserEntity userE = new UserEntity();
			userE.setRegistrationCode("registrationcode");
			userE.setLogin("mail");
			userE.setPartnerLink("partner refer link");
			userE.setRegistrationDate(new Date(System.currentTimeMillis()));
			userE.setRegion("region_" + i);

			producer.sendNewReferral(userE);
		}

	}

}
