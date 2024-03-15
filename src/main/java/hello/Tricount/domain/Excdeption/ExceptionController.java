package hello.Tricount.domain.Excdeption;


import hello.Tricount.domain.Excdeption.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler(IdExistError.class)
    public ResponseEntity<ErrorForm> IdExist(IdExistError idExistError){
        log.info("Error {}",idExistError);
        Status s=MakeStatus(idExistError.getStatus(),idExistError.getMessage());
        log.info("{}{}",s.getCode(),s.getCode_msg());
        ErrorForm errorForm=MakeErrorForm(s);
        return new ResponseEntity<>(errorForm, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PassWordNotMatch.class)
    public ResponseEntity<ErrorForm> PassWordNoMatch(PassWordNotMatch passWordNotMatch){
        Status s=MakeStatus(passWordNotMatch.getStatus(),"password not match");

        return new ResponseEntity<>(MakeErrorForm(s), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotMatch.class)
    public ResponseEntity<ErrorForm> IdNotMatch(IdNotMatch idNotMatch){
        Status s=MakeStatus(idNotMatch.getStatus(),idNotMatch.getMessage());
        ErrorForm errorForm=MakeErrorForm(s);
        return new ResponseEntity<>(errorForm, HttpStatus.BAD_REQUEST);
    }





    private Status MakeStatus(Status s,String msg){
        s.setCode_msg(msg);
        return s;
    }

    private ErrorForm MakeErrorForm(Status s){

        return new ErrorForm(s);

    }
}
