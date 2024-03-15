package hello.Tricount;

import hello.Tricount.domain.ComparatorForBalance;

import hello.Tricount.domain.ObjectForBalance;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BalanceTest {


    public PriorityQueue<ObjectForBalance> minus_cost = new PriorityQueue<>(new ComparatorForBalance());

    public PriorityQueue<ObjectForBalance> plus_cost = new PriorityQueue<>(new ComparatorForBalance());

    public Long return_balance_money(List<Expense> e){

        Long sums=e.stream().map(x->x.getCost()).toList().stream()
                .reduce(0L,(x,y)->x+y);
        return sums/e.size();}
    public void balanceing(ObjectForBalance p,ObjectForBalance m){
        if(p.getCost()+m.getCost()>0){
            p.setCost(p.getCost()+m.getCost());
            m.setCost(0L);
            System.out.printf("%s의 남은금액:%d %s의 남은 금액:%d %n",
                    p.getUser_name(),p.getCost(),m.getUser_name(),m.getCost());
        } else if (p.getCost()+m.getCost()==0){
            p.setCost(0L);
            m.setCost(0L);
            System.out.printf("%s님과 %s님 모두 정산완료%n",p.getUser_name(),m.getUser_name());
        }
        else{
            m.setCost(p.getCost()+m.getCost());
            p.setCost(0L);
            System.out.printf("%s의 남은금액:%d %s의 남은 금액:%d%n",
                    p.getUser_name(),p.getCost(),m.getUser_name(),m.getCost());

        }
    }


    @Test
    public void test1(){
        List<Expense> e = new ArrayList<>();
        e.add(new Expense("dong3058",10000L));
        e.add(new Expense("dong305",-4000L));
        e.add(new Expense("b",-1000L));
        e.add(new Expense("c",-5000L));
       // e.add(new Expense("a",-5000L));
        Long sum1 = 0L;
        for (Expense x : e) {
            Long remain = x.getCost() - sum1;
            if (remain >= 0) {
                plus_cost.offer(new ObjectForBalance(x.getUser_name(), remain));
            } else {
                minus_cost.offer(new ObjectForBalance(x.getUser_name(), remain));
            }


        }
        List<String> balancelog=new ArrayList<>();
        int count=0;
        ObjectForBalance p=null;
        ObjectForBalance m=null;

        while(!plus_cost.isEmpty()||!minus_cost.isEmpty()){
            if (count==0) {
                p = plus_cost.poll();
                m= minus_cost.poll();
                balanceing(p,m);
                count+=1;

            }
            else{
                if(p.getCost()==0L){
                p=plus_cost.poll();}
                if(m.getCost()==0L){
                    m=minus_cost.poll();
                }
                balanceing(p,m);
            }


        }

    };

}
