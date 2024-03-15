package hello.Tricount.domain;

import jakarta.persistence.*;
import org.hibernate.mapping.Join;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Settlement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="settle_id",nullable = false)
    private Long settle_id;


    @Column(name="title",nullable = false,unique = true)
    private String title;



    /*@OneToMany(mappedBy = "settlement",fetch = FetchType.LAZY)
    private List<MemberSettle> membersettlelist=new ArrayList<>();*/

    @OneToMany(mappedBy = "settlement_expense",fetch = FetchType.LAZY)
    private List<Expense> expenselist=new ArrayList<>();



    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    public List<MemberSettle> getMembersettlelist() {
        return membersettlelist;
    }*/

    //private Member member_settle;

    public Long getSettle_id() {
        return settle_id;
    }

    public String getTitle() {
        return title;
    }

    public List<Expense> getExpenselist() {
        return expenselist;
    }

    public void setSettle_id(Long settle_id) {
        this.settle_id = settle_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /*public void setMember_settle(Member member_settle) {
        this.member_settle = member_settle;
    }*/




}
