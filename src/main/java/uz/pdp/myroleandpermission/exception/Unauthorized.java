package uz.pdp.myroleandpermission.exception;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;

public class Unauthorized extends RuntimeException{
    public Unauthorized(String message) {
        super(message);
    }
}
