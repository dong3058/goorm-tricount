package hello.Tricount.domain;

import lombok.Data;

@Data
public class BalancingLog {

    private String sending_user;
    private Long sending_money;

    private String receive_user;


    public BalancingLog(String sending_user, Long sending_money, String receive_user) {
        this.sending_user = sending_user;
        this.sending_money = sending_money;
        this.receive_user = receive_user;

    }
}
