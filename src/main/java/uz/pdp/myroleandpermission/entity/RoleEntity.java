package uz.pdp.myroleandpermission.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.myroleandpermission.enums.Permissions;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoleEntity extends BaseEntity  {

    @Column(nullable = false,unique = true)
    private String roleName;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permissions> permissions;



}
