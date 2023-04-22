package security.app.secure.tdto;

import lombok.Data;

@Data
public class SignUpDto {

    private String name;
    private String username;
    private String email;
    private String password;
}
