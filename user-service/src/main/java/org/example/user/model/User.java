package org.example.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATE \"user\" SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class User {
    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    private boolean internal = false;

    private boolean deleted = false;
}
