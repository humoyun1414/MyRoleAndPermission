package uz.pdp.myroleandpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.myroleandpermission.dto.LoginDto;
import uz.pdp.myroleandpermission.dto.RegisterDto;
import uz.pdp.myroleandpermission.payload.ApiResponses;
import uz.pdp.myroleandpermission.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto registerDto) {
        ApiResponses register = authService.register(registerDto);
        return ResponseEntity.status(register.isSuccess()?201:409).body(register);
    }
    @PostMapping("/login")
    public HttpEntity<?>login(@RequestBody LoginDto loginDto){
        ApiResponses login  = authService.login(loginDto);
        return ResponseEntity.status(login.isSuccess()?201:409).body(login);
    }

}
