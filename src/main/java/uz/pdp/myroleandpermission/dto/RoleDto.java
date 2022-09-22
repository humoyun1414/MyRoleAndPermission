package uz.pdp.myroleandpermission.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.myroleandpermission.enums.Permissions;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    private long id;
    @NotBlank
    private String roleName;

    @NotNull
    private List<Permissions> permissions;

}
