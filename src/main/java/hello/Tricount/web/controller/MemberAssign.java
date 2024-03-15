package hello.Tricount.web.controller;


import hello.Tricount.domain.Excdeption.errors.IdExistError;
import hello.Tricount.domain.Excdeption.errors.Status;
import hello.Tricount.domain.Member;
import hello.Tricount.domain.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberAssign {

    private final MemberService memberService;
    @PostMapping("/add")
    public ResponseEntity<String> MemberAssigns(@ModelAttribute Member member) {


        Optional<Member> m=memberService.checkid(member.getUser_id());
        if (m.isPresent()) {

            throw new IdExistError(new Status("id exist",HttpStatus.BAD_REQUEST.value()),"id exist");

        }


        memberService.save(member);


        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
