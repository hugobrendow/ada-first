package br.com.ada.ifome.usuario;

import br.com.ada.ifome.endereco.Endereco;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String email;
    @OneToOne
    private Endereco endereco;

}
