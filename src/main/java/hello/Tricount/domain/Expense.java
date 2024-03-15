package hello.Tricount.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Expense {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="expense_id",nullable = false)
    private Long expense_id;

    @Column(name="cost")
    private Long cost;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="settle_id")
    private Settlement settlement_expense;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Member member_expense;

    @Column(name="cost_date")
    private LocalDateTime cost_date;


    public Long getExpense_id() {
        return expense_id;
    }

    public Long getCost() {
        return cost;
    }

    public Member getMember_expense() {
        return member_expense;
    }

    public LocalDateTime getCost_date() {
        return cost_date;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement_expense = settlement;
    }

    public void setMember(Member member) {
        this.member_expense = member;
    }

    public void setExpense_id(Long expense_id) {
        this.expense_id = expense_id;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public void setCost_date(LocalDateTime cost_date) {
        this.cost_date = cost_date;
    }
}
