package hello.Tricount;


public class Expense {

    private String user_name;
    private Long cost;

    public Expense(String user_name, Long cost) {
        this.user_name = user_name;
        this.cost = cost;
    }

    public String getUser_name() {
        return user_name;
    }

    public Long getCost() {
        return cost;
    }
}
