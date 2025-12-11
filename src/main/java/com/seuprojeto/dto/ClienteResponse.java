
package com.seuprojeto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
