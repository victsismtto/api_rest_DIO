package one.digitalinnovation.personal.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personal.api.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data                                                       //create getters e setters for all fields
@Builder
@AllArgsConstructor                                         //rule to the class construct
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //deixa a obrigatoriedade de incrementacao do id ao banco de dados
    private Long id;

    @Enumerated(EnumType.STRING)                            //comes from PhoneType with the string mobile, personal...
    @Column(nullable = false)                               //database rule -> not null
    private PhoneType type;

    @Column(nullable = false)                               //database rule-> not null
    private String number;
}
