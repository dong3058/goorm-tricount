package hello.Tricount.domain.repository;

import hello.Tricount.domain.Member;

import java.util.Optional;

public interface Repositorys {


    void save(Member member);
    Optional<Member> findbyid(String id);
    boolean checkpassword(String password);



}
