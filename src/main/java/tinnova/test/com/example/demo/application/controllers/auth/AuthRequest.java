package tinnova.test.com.example.demo.application.controllers.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotBlank
    @Schema(description = "Nome de usuário", example = "admin")
    private String username;

    @NotBlank
    @Schema(description = "Senha", example = "123456")
    private String password;
}
