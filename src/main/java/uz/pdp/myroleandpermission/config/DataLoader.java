package uz.pdp.myroleandpermission.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.myroleandpermission.entity.RoleEntity;
import uz.pdp.myroleandpermission.entity.UserEntity;
import uz.pdp.myroleandpermission.enums.Permissions;
import uz.pdp.myroleandpermission.enums.RoleName;
import uz.pdp.myroleandpermission.repository.RoleRepository;
import uz.pdp.myroleandpermission.repository.UserRepository;

import java.util.Arrays;

import static uz.pdp.myroleandpermission.enums.Permissions.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            RoleEntity admin = roleRepository.save(new RoleEntity(
                    RoleName.ADMIN.name(),
                    Arrays.asList(Permissions.values())
            ));
            RoleEntity user = roleRepository.save(new RoleEntity(
                    RoleName.USER.name(),
                    Arrays.asList(DELETE_MY_COMMENT, VIEW_USERS, UPDATE_MY_COMMENT)
            ));

            UserEntity adminjon = userRepository.save(new UserEntity(
                    "Adminjon",
                    "andmin",
                    passwordEncoder.encode("admin777"),
                    true,
                    admin
            ));
            UserEntity userjon = userRepository.save(new UserEntity(
                    "Userjon",
                    "userjon",
                    passwordEncoder.encode("userjon777"),
                    true,
                    user
            ));
        }
    }
}
