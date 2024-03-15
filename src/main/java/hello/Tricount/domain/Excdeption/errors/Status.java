package hello.Tricount.domain.Excdeption.errors;

import lombok.Data;

@Data
public class Status {

    private String Code_msg;
    private int Code;


    public Status(String Code_msg,int code) {
        this.Code_msg=Code_msg;
        Code = code;
    }
}
