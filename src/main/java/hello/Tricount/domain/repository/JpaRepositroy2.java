package hello.Tricount.domain.repository;

import hello.Tricount.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Repository

public class JpaRepositroy2  {

    private final EntityManager em;


    public void makeSettle(Settlement settlement,HttpServletRequest req){
        HttpSession session=req.getSession(false);
        String id=(String) session.getAttribute("user_id");
        Settlement settlement1=settlement;
        MemberSettle memberSettle=new MemberSettle();
        Balance b=new Balance();
        try{
            String query="select m from Member m join fetch m.membersettlelist where m.user_id=:id";
        Member member=em.createQuery(query,Member.class)
                .setParameter("id",id)
                .getSingleResult();


        b.setSettlement_balance(settlement1);
        memberSettle.setSettlement(settlement1);
        memberSettle.setMember(member);

        member.getMembersettlelist().add(memberSettle);}
        catch(NoResultException e){
            String query="select m from Member m  where m.user_id=:id";
            Member member=em.createQuery(query,Member.class)
                    .setParameter("id",id)
                    .getSingleResult();

            memberSettle.setSettlement(settlement1);
            memberSettle.setMember(member);
            b.setSettlement_balance(settlement1);
            member.getMembersettlelist().add(memberSettle);

        }
        finally {

            em.persist(b);
            em.persist(settlement1);
            em.persist(memberSettle);
        }

    }


    public void joinsettle(String title,HttpServletRequest req){
        String user_id=getsession(req);


        String query="select s from Settlement s where s.title=:title";
        Settlement s=em.createQuery(query,Settlement.class)
                .setParameter("title",title)
                .getSingleResult();
        String query2="select m from Member m where m.user_id=:id";

        Member m=em.createQuery(query2,Member.class)
                .setParameter("id",user_id)
                .getSingleResult();


        MemberSettle memberSettle=new MemberSettle();
        memberSettle.setSettlement(s);
        memberSettle.setMember(m);
        //m.getMembersettlelist().add(memberSettle);

        em.persist(memberSettle);


    }
    public void makeExpense(CreateExpense expense, HttpServletRequest req){
        HttpSession session=req.getSession(false);
        String id=(String) session.getAttribute("user_id");
        Expense expense1=new Expense();
        expense1.setCost(expense.getCost());

        String query="select s from MemberSettle s join fetch s.settlement settle join fetch s.member m where m.user_id=:id and settle.title=:title";


        MemberSettle s=em.createQuery(query,MemberSettle.class)
                .setParameter("title",expense.getSettle_title())
                        .setParameter("id",id)
                                .getSingleResult();
        /*String query="select m from Member m where m.user_id=:id";

        Member m=em.createQuery(query,Member.class)
                .setParameter("id",id)
                .getSingleResult();
        String query2="select s from Settlement s where s.title=:title";

        Settlement s=em.createQuery(query2,Settlement.class)
                .setParameter("title",expense.getSettle_title())
                .getSingleResult();*/

        //s.getExpenselist().add(expense1);
        expense1.setSettlement(s.getSettlement());
        expense1.setMember(s.getMember());
        expense1.setCost_date(LocalDateTime.now());
        em.persist(expense1);


    }

    public List<ExpenseToShow> getExpenseList(HttpServletRequest req){
        String user_id=getsession(req);


        String query="select new ExpenseToShow(e.settlement_expense.title,e.member_expense.user_id,e.expense_id,e.cost,e.cost_date) from Expense e where e.member_expense.user_id=:id";
        List<ExpenseToShow> ex=em.createQuery(query,ExpenseToShow.class)
                .setParameter("id",user_id)
                .getResultList();

        return ex;
    }

    public List<SettlementToShow> getSettlementList(HttpServletRequest req){

        String user_id=getsession(req);

        String query="select new SettlementToShow(s.settlement.settle_id,s.settlement.title) from MemberSettle s where s.member.user_id=:user_id";

        List<SettlementToShow> s=em.createQuery(query,SettlementToShow.class)
                .setParameter("user_id",user_id)
                .getResultList();

        //String query="select distinct m from Member m join fetch m.settlelist where m.user_id=:id ";
        /*String query="select new SettlementToShow(s.settle_id,s.title) from Settlement s where s.member_settle.user_id=:id";
        List<SettlementToShow>s=em.createQuery(query,SettlementToShow.class)
                .setParameter("id",user_id)
                .getResultList();*/
        /*List<SettlementToShow> settlementlist=m.getSettlelist().stream()
                .map(x->new SettlementToShow(x.getSettle_id(),x.getTitle()))
                .toList();*/

        return s;
    }

    public SettlementListToShow searchsettle(String title){


        /*String query="select new SettleMentToShow2(s.settlement,s.member.user_id) from MemberSettle s join fetch s.settlement.expenselist where s.settlement.title=:title";

        List<SettleMentToShow2> s=em.createQuery(query,SettleMentToShow2.class)
                .setParameter("title",title)
                .getResultList();
        for(SettleMentToShow2 x:s){
            log.info("Settlementtoshow2:{},{}",x.getUser_id(),x.getS());
            for(Expense e:x.getS().getExpenselist()){
                log.info("check:{}",e);
            }


        }


        List<SettleMentToShow2> s2=s.stream().distinct().toList();
        for(SettleMentToShow2 x:s2){
            log.info("Settlementtoshow2:{},{}",x.getUser_id(),x.getS());
            for(Expense e:x.getS().getExpenselist()){
                log.info("{}",e);
            }


        }

        List<ExpenseToShow> ex2 = s2.stream()
                .flatMap(x -> x.getS().getExpenselist()
                        .stream()
                        .map(y -> new ExpenseToShow(y.getExpense_id(), x.getUser_id(), y.getCost(), y.getCost_date())))
                .toList();

        return new SettlementListToShow(title,ex2);*/
        String query="select distinct s from Settlement s join fetch s.expenselist where s.title=:title";
        Settlement s=em.createQuery(query,Settlement.class)
                .setParameter("title",title)
                .getSingleResult();

        List<ExpenseToShow>e =s.getExpenselist().stream()
                .map(x->new ExpenseToShow(x.getExpense_id(),x.getMember_expense().getUser_id(),x.getCost(),x.getCost_date()))
                .toList();

        return new SettlementListToShow(s.getTitle(),e);
    }

    public List<BalancingLog> returnbalancelog(String title){

        String query="select distinct b from Balance b join fetch b.settlement_balance s join fetch s.expenselist e join fetch e.member_expense where b.settlement_balance.title=:title";
        //별칭을 주는게 일관성 즉 가져와야할 데이터들을 전부다 가져오는 경우에 한해서는 써도 그리 큰 문제는 없는듯하다.
        //일관성은 가져오는 데이터가 db와 db간의 관계와 일치함을 즉 컬렉션의 경우에는 해당 테이블에
        //연관된 모든 데이터를 가져온다는걸 의미하는걸로 보임.
        //즉 fetch join의 경우 가져오는걸 한정으로볼때에는 별칭을 정해줘도 상관없어보임.

        //여기선 balance 목록에서 expenselistn와 member객체를 가져디가쓰기에 전부다 한꺼번에 가져오는걸로 하였다.
        Balance b=em.createQuery(query,Balance.class)
                .setParameter("title",title)
                .getSingleResult();


        Settlement s=b.getSettlement_balance();


        List<BalancingLog> balancingLogs=new ArrayList<>();

        PriorityQueue<ObjectForBalance> minus_cost = new PriorityQueue<>(new ComparatorForBalance());

        PriorityQueue<ObjectForBalance> plus_cost = new PriorityQueue<>(new ComparatorForBalance());

        b.makebalancelist(s,plus_cost,minus_cost);

        b.makebalancelog(plus_cost,minus_cost,balancingLogs);

        return balancingLogs;


    }

    public String getsession(HttpServletRequest req){

        HttpSession session=req.getSession(false);

        return (String) session.getAttribute("user_id");
    }

}
