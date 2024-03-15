package hello.Tricount.domain.Excdeption.errors;

import lombok.Data;
import org.springframework.http.ResponseEntity;
@Data
public class ErrorForm  {
    private Status status;

    public ErrorForm(Status status) {
        this.status = status;
    }
}
