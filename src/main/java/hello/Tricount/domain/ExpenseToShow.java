package hello.Tricount.domain;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class ExpenseToShow {



    private String settle_title;
    private Long expense_id;
    private String username;
    private Long cost;

    private LocalDateTime expense_date;


    public ExpenseToShow(String settle_title,String username ,Long expense_id, Long cost, LocalDateTime expense_date) {
        this.settle_title = settle_title;
        this.username=username;
        this.expense_id = expense_id;
        this.cost = cost;
        this.expense_date = expense_date;
    }

    public ExpenseToShow(Long expense_id, String username, Long cost, LocalDateTime expense_date) {
        this.expense_id = expense_id;
        this.username = username;
        this.cost = cost;
        this.expense_date = expense_date;
    }
}
