package one.digitalinnovation.personal.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personal.api.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data       //gera getters e setters para todos os campos
@Builder
@AllArgsConstructor //gera o construtor da classe
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //deixa a obrigatoriedade de incrementacao do id ao banco de dados
    private Long id;

    @Enumerated(EnumType.STRING)    //vem de PhoneType com a string conforme lá
    @Column(nullable = false)       //regra BD -> not null
    private PhoneType type;

    @Column(nullable = false)       //regra BD -> not null
    private String number;
}
