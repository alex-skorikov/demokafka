package ru.skorikov.demoKafka.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class AmountTransferDto implements Message{
    private String userEmail;
    private String partnerId;
    private String region;
    private BigDecimal amount;
}
