package ru.skorikov.demoKafka.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ReferalToPartnerDto implements Message{
    private String userId;
    private String registrationCode;
    private String referalLink;
    private String region;
    private Date registrationDate;
}
