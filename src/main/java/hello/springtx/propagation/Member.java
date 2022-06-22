package hello.springtx.propagation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name", length = 30)
    private String username;

    public Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
