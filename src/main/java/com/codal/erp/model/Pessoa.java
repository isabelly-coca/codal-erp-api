package com.codal.erp.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "(?!^(\\d)\\1{10}$)\\d{11}", message = "CPF inválido")
    private String cpf;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve ter 10 ou 11 números")
    private String telefone;

    @NotBlank//apenas para string
    @Email
    private String email;

    @NotBlank
    private String endereco;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private Boolean status=true;
}

