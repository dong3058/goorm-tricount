package hello.Tricount.domain.Excdeption.errors;

import hello.Tricount.domain.Excdeption.errors.Status;
import lombok.Data;

@Data
public class IdExistError extends RuntimeException{
    private Status status;
    public IdExistError(Status status,String msg) {
        super(msg);
        this.status = status;

    }
}
