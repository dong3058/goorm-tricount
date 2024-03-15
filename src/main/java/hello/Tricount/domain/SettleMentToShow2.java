package hello.Tricount.domain;

import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SettleMentToShow2 {

    private Settlement s;
    private String user_id;

    public SettleMentToShow2(Settlement s, String user_id) {
        this.s = s;
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettleMentToShow2 that = (SettleMentToShow2) o;
        return Objects.equals(this.user_id,that.getUser_id());
    }


    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
