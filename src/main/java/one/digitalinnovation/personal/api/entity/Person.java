package one.digitalinnovation.personal.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity     //para criar as classes @Id, @Column, @OnetoMany...
@Data       //faz já os Getters/Setters automaticamnete das variaveis de classe
@Builder    //faz a consturção de objetos
@AllArgsConstructor     //insere os contrutores já da classe
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //deixa a obrigatoriedade de incrementacao do id ao banco de dados
    private Long id;

    @Column(nullable = false)       //regra BD -> not null
    private String firstName;

    @Column(nullable = false)       //regra BD -> not null
    private String lastName;

    @Column(nullable = false, unique = true)        //nao se pode repetir valor do cpf -> rejeita inserção duplicada
    private String cpf;

    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})      //fetch.lazy (ser permofatico) e cascade serve para ir para o cadastro de pessoas diretamente e não puxar o telefone primeiro
    private List<Phone> phone;

}
