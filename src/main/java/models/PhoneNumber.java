package models;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = {"phoneUser"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String code;
    int number;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    User phoneUser;

    public PhoneNumber(String code, int number) {
        this.code = code;
        this.number = number;
    }

    public PhoneNumber(String code, int number, User phoneUser) {
        this.code = code;
        this.number = number;
        this.phoneUser = phoneUser;
    }
}
