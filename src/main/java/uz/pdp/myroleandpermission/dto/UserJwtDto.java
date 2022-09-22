package uz.pdp.myroleandpermission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.myroleandpermission.enums.RoleName;

@AllArgsConstructor
@Data
public class UserJwtDto {
    private String username;
    private RoleName roles;
}
