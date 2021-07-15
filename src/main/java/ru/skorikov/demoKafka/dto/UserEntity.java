package ru.skorikov.demoKafka.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    private String registrationCode;
    private BigDecimal amount;
    private String keycloackUserId;
    private String partnerLink;
    private String phoneNumber;
    private String region;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private PartnerShipEntity partnerShip;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<CreditCardEntity> creditCards;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<PartnerOperationEntity> operations;
//
//    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
//    private List<ReferalEntity> referals;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserEntity other = (UserEntity) obj;
        return id != null && id.equals(other.getId());
    }
}
