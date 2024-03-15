package hello.Tricount.domain.Excdeption.errors;

import lombok.Data;

@Data
public class PassWordNotMatch extends RuntimeException{
    private Status status;
    public PassWordNotMatch(Status status,String msg) {
        super(msg);
        this.status = status;

    }

}
