package hello.Tricount.domain.Excdeption.errors;

import lombok.Data;

@Data
public class IdNotMatch extends RuntimeException{
    private Status status;
    public IdNotMatch(Status status,String msg) {
        super(msg);
        this.status = status;

    }

}
