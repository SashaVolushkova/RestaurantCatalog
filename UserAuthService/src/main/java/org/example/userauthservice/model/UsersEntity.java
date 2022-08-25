package org.example.userauthservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 256)
    private String name;
    @Basic
    @Column(name = "email", nullable = false, length = 256)
    private String email;


    @Basic
    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_to_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Collection<RolesEntity> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(this.email, that.email);
    }

    @Override
    public int hashCode() {
        return 31 + (email != null ? email.hashCode() : 0);
    }
}
