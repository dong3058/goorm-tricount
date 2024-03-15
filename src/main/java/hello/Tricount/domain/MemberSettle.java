package hello.Tricount.domain;

import jakarta.persistence.*;

@Entity
public class MemberSettle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="membersettle_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="settle_id")
    private Settlement settlement;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }


    public Member getMember() {
        return member;
    }

    public Settlement getSettlement() {
        return settlement;
    }
}
