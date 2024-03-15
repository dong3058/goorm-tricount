package hello.Tricount.domain.repository;

import hello.Tricount.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class JpaRepositroy implements Repositorys {

    private final EntityManager em;



    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findbyid(String id) {
        String query="select m from Member m where m.user_id=:id";

        try {
            Member m = em.createQuery(query, Member.class)
                    .setParameter("id", id)
                    .getSingleResult();

           return  Optional.ofNullable(m);
        }
        catch(NoResultException e){

            return Optional.ofNullable(null);
        }



    }

    @Override
    public boolean checkpassword(String password) {
        String query="select m from Member m where m.password=:password";
        List<Member> m= em.createQuery(query, Member.class)
                .setParameter("password", password)
                .getResultList();
        if(m.isEmpty()){
            return true;
        }
        else{
            return false;
        }

    }

}
