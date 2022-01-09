package si.evinjete.uporabniki;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "uporabnik")
@NamedQueries({
        @NamedQuery(
                name = "Uporabnik.findUporabniki",
                query = "SELECT u " +
                        "FROM Uporabnik u"
        )
})
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "Uporabnik.findUporabnikFromNameSurnameEmail",
                        query = "SELECT * FROM uporabnik WHERE name = :name AND surname = :surname AND email = :email", resultClass = Uporabnik.class
                ),
                @NamedNativeQuery(
                        name = "Uporabnik.findUporabnikFromEmail",
                        query = "SELECT * FROM uporabnik WHERE email = :email", resultClass = Uporabnik.class
                )
        }
)
public class Uporabnik implements Serializable {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @Column(name = "surname", nullable = false, updatable = false)
    private String surname;
    @Column(name = "email", nullable = false, updatable = true)
    private String email;
    @Column(name = "password", nullable = false, updatable = true)
    private String password;
    @Column(name = "timestamp", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date timestamp;
    @Column(name = "type", nullable = false)
    private Integer type; //Type of user - 0 means normal user, 1 is police officer, 2 is admin


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
