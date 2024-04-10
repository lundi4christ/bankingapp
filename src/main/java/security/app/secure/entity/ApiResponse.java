package security.app.secure.entity;

import lombok.Data;

@Data
public class ApiResponse {
    private Long id;
    private Integer status;
    private Integer message;
    private Object result;
}
