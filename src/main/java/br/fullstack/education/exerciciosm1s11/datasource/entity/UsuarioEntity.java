package br.fullstack.education.exerciciosm1s11.datasource.entity;


import br.fullstack.education.exerciciosm1s11.controller.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity
@Table(name="usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) //nome de usuário deve ser único
    private String nomeUsuario;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_papel", nullable = false)
    private PapelEntity papel;

    //valida logins com a senha encriptada salva no banco
    public boolean senhaValida(
            LoginRequest loginRequest,
            BCryptPasswordEncoder bCryptEncoder
    ) {
        return bCryptEncoder.matches(
                loginRequest.senha(),
                this.senha
        );
    }
}