package uz.pdp.myroleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.myroleandpermission.dto.LoginDto;
import uz.pdp.myroleandpermission.dto.RegisterDto;
import uz.pdp.myroleandpermission.entity.RoleEntity;
import uz.pdp.myroleandpermission.entity.UserEntity;
import uz.pdp.myroleandpermission.enums.RoleName;
import uz.pdp.myroleandpermission.payload.ApiResponses;
import uz.pdp.myroleandpermission.repository.RoleRepository;
import uz.pdp.myroleandpermission.repository.UserRepository;
import uz.pdp.myroleandpermission.security.JwtProvider;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RoleRepository roleRepository;

    public ApiResponses register(RegisterDto registerDto) {

        boolean username = userRepository.existsByUsername((registerDto.getUsername()));
        if (username) {
            return new ApiResponses("We have such a user", false);
        }
        UserEntity user = new UserEntity();
        user.setFullName(registerDto.getFullName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(roleRepository.findByRoleName(RoleName.USER.name()));
//        user.setUpdatedAt(new Timestamp(123456));
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponses("Successfully registered", true);
    }

    public ApiResponses login(LoginDto loginDto) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()
        ));
        UserEntity userObject = (UserEntity) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(userObject.getUsername(), Collections.singletonList(userObject.getRoles()));
        return new ApiResponses("Welcome to website " + userObject.getFullName(), true, token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return (UserDetails) new UsernameNotFoundException("User not found");
        }
    }
}
