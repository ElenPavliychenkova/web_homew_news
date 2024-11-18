package app.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Role role;
    private String password;

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

    // get setter password
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(name, other.name) && Objects.equals(role, other.role);
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    public enum Role {
        ADMIN,
        JOURNALIST,
        READER
    }

}
