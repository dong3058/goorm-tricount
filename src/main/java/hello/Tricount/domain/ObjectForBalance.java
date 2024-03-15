package hello.Tricount.domain;

import lombok.Data;

@Data
public class ObjectForBalance {

    public String user_name;
    public Long cost;

    public ObjectForBalance(String user_name, Long cost) {
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
