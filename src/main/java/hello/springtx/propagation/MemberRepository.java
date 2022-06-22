package hello.springtx.propagation;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    @Transactional
    public void save(Member member){
        log.info("Member 저장");
        em.persist(member);
    }

    public Optional<Member> find(String userName){
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
            .setParameter("username", userName)
            .getResultList().stream().findAny();
    }

}
