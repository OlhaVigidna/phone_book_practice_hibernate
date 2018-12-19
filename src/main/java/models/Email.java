package models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"userEmail"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String domen;
    String emailName;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    User userEmail;

    public Email(String domen, String emailName) {
        this.domen = domen;
        this.emailName = emailName;
    }

    public Email(String domen, String emailName, User userEmail) {
        this.domen = domen;
        this.emailName = emailName;
        this.userEmail = userEmail;
    }
}
