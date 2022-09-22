package uz.pdp.myroleandpermission.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponses {
    private String message;
    private boolean success;
    private String token;

    public ApiResponses(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
