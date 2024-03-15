package hello.Tricount.domain.service;

import hello.Tricount.domain.*;
import hello.Tricount.domain.repository.JpaRepositroy;
import hello.Tricount.domain.repository.JpaRepositroy2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final JpaRepositroy repository;
    private final JpaRepositroy2 repository2;


    public void save(Member m){
        repository.save(m);
    }

    public Optional<Member> checkid(String id){
        return repository.findbyid(id);
    }

    public boolean checkpassword(String password){
        return repository.checkpassword(password);
    }

    public void settlementmake(Settlement settlement, HttpServletRequest req){
    repository2.makeSettle(settlement,req);}

    public void makexpense(CreateExpense expense, HttpServletRequest req){
        repository2.makeExpense(expense,req);
    }

    public List<ExpenseToShow> returnexpenselist(HttpServletRequest req){

        return repository2.getExpenseList(req);
    }


    public List<SettlementToShow> returnsettlements(HttpServletRequest req){

        return repository2.getSettlementList(req);
    }


    public SettlementListToShow searchsettlement(String title){

        return repository2.searchsettle(title);

    }

    public void joinsettle(String title,HttpServletRequest req){

        repository2.joinsettle(title,req);
    }

    public List<BalancingLog> getbalance(String title){
        return repository2.returnbalancelog(title);
    }

}
