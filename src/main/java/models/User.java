package models;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"userPhone", "emails"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String surname;
@OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "phoneUser"
)
    List<PhoneNumber> userPhone = new ArrayList<>();
@OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "userEmail"
)
    List<Email> emails = new ArrayList<>();

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname, List<PhoneNumber> userPhone, List<Email> emails) {
        this.name = name;
        this.surname = surname;
        this.userPhone = userPhone;
        this.emails = emails;
    }
}
