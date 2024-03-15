package hello.Tricount.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="user_id",nullable = false,unique = true)
    private String user_id;
    @Column(name="username",nullable = false)
    private String username;
    @Column(name="password",nullable = false)
    private String password;


   @OneToMany(mappedBy = "member",fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MemberSettle> membersettlelist=new ArrayList<>();



    @OneToMany(mappedBy = "member_expense",fetch=FetchType.LAZY)
    private List<Expense> expenses=new ArrayList<>();

    public List<MemberSettle> getMembersettlelist() {
        return membersettlelist;
    }

    public Long getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public List<Expense> getExpenselist() {
        return expenses;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
