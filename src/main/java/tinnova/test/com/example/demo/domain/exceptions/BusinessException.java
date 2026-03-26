package tinnova.test.com.example.demo.domain.exceptions;

import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
public class BusinessException extends RuntimeException {

    private final String code;
    private final List<String> details;

    public BusinessException(String message, List<String> details) {
        this((String)null, message, details);
    }

    public BusinessException(String code, String message, List<String> details) {
        super(message);
        this.code = code;
        this.details = details;
    }

    public String getCode() {
        return this.code;
    }

    public List<String> getDetails() {
        return this.details;
    }

}
