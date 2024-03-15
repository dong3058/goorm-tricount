package hello.Tricount.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Entity
public class Balance {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="settle_id")
    private Settlement settlement_balance;


    public Settlement getSettlement_balance() {
        return settlement_balance;
    }

    public void setSettlement_balance(Settlement settlement_balance) {
        this.settlement_balance = settlement_balance;
    }

    public Long return_balance_money(Settlement s, List<Expense> e){

        Long sums=e.stream().map(x->x.getCost()).toList().stream()
                .reduce(0L,(x,y)->x+y);
        return sums/e.size();
    }
    public void makebalancelist(Settlement s,PriorityQueue<ObjectForBalance> plus_cost,PriorityQueue<ObjectForBalance> minus_cost){
        System.out.println("=========expenselist 가져오기======");
        List<Expense> e=s.getExpenselist();
        System.out.println("=========expenselist 가져오기 끝======");
        Long sums=return_balance_money(s,e);
        for(Expense x:e){
            Long remain=x.getCost()-sums;
            if(remain>=0){
                plus_cost.offer(new ObjectForBalance(x.getMember_expense().getUser_id(),remain));
            }
            else{
                minus_cost.offer(new ObjectForBalance(x.getMember_expense().getUser_id(),remain));
            }


        }

    }
    public void balanceing(ObjectForBalance p,ObjectForBalance m,List<BalancingLog> balancingLogs){
        if(p.getCost()+m.getCost()>0){
            balancingLogs.add(new BalancingLog(p.getUser_name(),Math.abs(m.getCost()),m.getUser_name()));
            p.setCost(p.getCost()+m.getCost());
            m.setCost(0L);

        } else if (p.getCost()+m.getCost()==0){
            balancingLogs.add(new BalancingLog(p.getUser_name(),Math.abs(m.getCost()),m.getUser_name()));
            p.setCost(0L);
            m.setCost(0L);

        }
        else{
            balancingLogs.add(new BalancingLog(p.getUser_name(),p.getCost(),m.getUser_name()));
            m.setCost(p.getCost()+m.getCost());

            p.setCost(0L);


        }
    }


    public void makebalancelog(PriorityQueue<ObjectForBalance> plus_cost,PriorityQueue<ObjectForBalance> minus_cost,List<BalancingLog> balancingLogs){
        int count=0;
        ObjectForBalance p=null;
        ObjectForBalance m=null;

        while(!plus_cost.isEmpty()||!minus_cost.isEmpty()){
            if (count==0) {
                p = plus_cost.poll();
                m= minus_cost.poll();
                balanceing(p,m,balancingLogs);
                count+=1;

            }
            else{
                if(p.getCost()==0L){
                    p=plus_cost.poll();}
                if(m.getCost()==0L){
                    m=minus_cost.poll();
                }
                balanceing(p,m,balancingLogs);
            }


        }
    }
}
