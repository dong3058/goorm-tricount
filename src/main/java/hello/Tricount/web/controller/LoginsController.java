package hello.Tricount.web.controller;

import hello.Tricount.domain.*;
import hello.Tricount.domain.Excdeption.errors.IdNotMatch;
import hello.Tricount.domain.Excdeption.errors.PassWordNotMatch;
import hello.Tricount.domain.Excdeption.errors.Status;
import hello.Tricount.domain.repository.JpaRepositroy;
import hello.Tricount.domain.repository.JpaRepositroy2;
import hello.Tricount.domain.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.extract.spi.SchemaExtractionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginsController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> loginstage(@ModelAttribute Login login,HttpServletRequest req) {

        Optional<Member> m=memberService.checkid(login.getUser_id());
        if(!m.isPresent()){
            throw new IdNotMatch(new Status("zzz",HttpStatus.BAD_REQUEST.value()),"zzzzzz");


        } else if (memberService.checkpassword(login.getPassword())){
            throw new PassWordNotMatch(new Status("aaa",HttpStatus.BAD_REQUEST.value()),"aaaaa");

        }

        HttpSession session = req.getSession();
        session.setAttribute("user_id", login.getUser_id());
        log.info("{}",(String) session.getAttribute("user_id"));


        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutstage(HttpServletRequest req){
        HttpSession session=req.getSession(false);

        if(session!=null){
            session.invalidate();
        }


        return new ResponseEntity<>("ok",HttpStatus.OK);

    }


    @PostMapping("/settlement")
    public ResponseEntity<String> settlementmake(@ModelAttribute Settlement settlement,
                                              HttpServletRequest req){

        memberService.settlementmake(settlement,req);
        return new ResponseEntity<>("ok",HttpStatus.OK);

    }
    @PostMapping("/expense")
    public ResponseEntity<String> makeexpense(@ModelAttribute CreateExpense expense,
                                              HttpServletRequest req){

        memberService.makexpense(expense,req);
        return new ResponseEntity<>("ok",HttpStatus.OK);

    }
    @PostMapping("/expenselist")
    public ResponseEntity<List<ExpenseToShow>> returnexpenselist(HttpServletRequest req){


        return new ResponseEntity<>(memberService.returnexpenselist(req),HttpStatus.OK);

    }
    @PostMapping("/settlelist")
    public ResponseEntity<List<SettlementToShow>> returnsettlelist(HttpServletRequest req){




        return new ResponseEntity<>(memberService.returnsettlements(req),HttpStatus.OK);

    }

    @PostMapping("/settlement/{title}")
    public ResponseEntity<SettlementListToShow> searchsettlment(@PathVariable(name="title") String title){

        return new ResponseEntity<>(memberService.searchsettlement(title),HttpStatus.OK);
    }

    @PostMapping("/settlement/join/{title}")
    @ResponseBody
    public String joinsettle(@PathVariable(name="title") String title,HttpServletRequest req){
        memberService.joinsettle(title,req);


        return "ok";

    }

    @PostMapping("/settlement/{title}/balance")
    public ResponseEntity<List<BalancingLog>> returnbalance(@PathVariable(name="title") String title){

        return new ResponseEntity<>(memberService.getbalance(title),HttpStatus.OK);
    }

}
