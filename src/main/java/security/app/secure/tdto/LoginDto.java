package security.app.secure.tdto;

import lombok.Data;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;
    private String token;

}
