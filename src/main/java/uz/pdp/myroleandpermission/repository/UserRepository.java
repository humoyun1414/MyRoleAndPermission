package uz.pdp.myroleandpermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.myroleandpermission.entity.RoleEntity;
import uz.pdp.myroleandpermission.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByUsername(String username);

    Optional<UserEntity>findByUsername(String username);

//    RoleEntity findByUsername(String username)
}
