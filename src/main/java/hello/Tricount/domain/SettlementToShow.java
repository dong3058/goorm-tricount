package hello.Tricount.domain;

import lombok.Data;

@Data
public class SettlementToShow {

    private Long settle_id;
    private String title;

    public SettlementToShow(Long settle_id, String title) {
        this.settle_id = settle_id;
        this.title = title;
    }
}
