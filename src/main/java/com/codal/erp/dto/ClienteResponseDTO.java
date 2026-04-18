package com.codal.erp.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteResponseDTO {

        private Long id;
        private String nome;
        private String cpf;
        private String telefone;
        private String email;
        private String endereco;
        private LocalDate dataNascimento;
        private Boolean status;

}
