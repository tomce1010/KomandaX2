package Personel;

import com.sun.source.doctree.SerialDataTree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
    private String phoneNum;
    private String login;

    public User(String password, String name, String lastname, LocalDate dateOfBirth, String phoneNum, String login) {
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNum = phoneNum;
        this.login = login;
    }


    @Override
    public String toString() {
        return  "ID = " + id +
                "\nName = '" + name + '\'' +
                "\nLast name = '" + lastname + '\'' +
                "\nLogin = '" + login + '\'';
    }
}
