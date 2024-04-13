package security.app.secure.tdto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String username;

    public UserDto(Long id, String email, String name, String password, String username){
        this.id=id;
        this.email=email;
        this.name=name;
        this.password=password;
        this.username=username;
    }
}
