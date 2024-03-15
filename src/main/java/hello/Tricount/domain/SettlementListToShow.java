package hello.Tricount.domain;

import lombok.Data;

import java.util.List;
@Data
public class SettlementListToShow {


    private String title;

    private List<ExpenseToShow> expense;

    public SettlementListToShow( String title, List<ExpenseToShow> expense) {

        this.title = title;
        this.expense = expense;
    }
}
