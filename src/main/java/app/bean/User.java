package app.bean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String email = "email";
    private String password;
    private String name;
    private String surname = "sur";
    private Role role;
    private LocalDate birthDate = LocalDate.now();
    private LocalDateTime registrationDate;

    public User(long id, String email, String password, String name, String surname, Role role, LocalDate birthDate, LocalDateTime registrationDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
    }

    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, Role role) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public User(String email, String password, String name, String surname, Role role, LocalDateTime registrationDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder().append(email, user.email).append(password, user.password).append(name, user.name).append(surname, user.surname).append(role, user.role).append(registrationDate, user.registrationDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(email).append(password).append(name).append(surname).append(role).append(registrationDate).toHashCode();
    }

    public enum Role {
        ADMIN,
        JOURNALIST,
        READER
    }

}
