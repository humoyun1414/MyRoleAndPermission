package uz.pdp.myroleandpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.myroleandpermission.enums.Permissions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = true, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RoleEntity roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new LinkedList<>();
        List<Permissions> permissions = this.roles.getPermissions();
//        for (Permissions permission : permissions) {
//            grantedAuthorityList.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return permission.name();
//                }
//            });
//        }
        for (Permissions permission : permissions) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorityList;
    }

    public boolean accountNonExpired = true;

    public boolean accountNonLocked = true;

    public boolean isCredentialsNonExpired = true;

    public UserEntity(String fullName, String username, String password, boolean enabled, RoleEntity roles) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }
}
