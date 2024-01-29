package club.book.asmr.user.entity;

import club.book.asmr.common.entity.BaseTime;
import club.book.asmr.user.data.Age;
import club.book.asmr.user.data.AuthProvider;
import club.book.asmr.user.data.Gender;
import club.book.asmr.user.data.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'empty'")
    private Age age;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'empty'")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider authProvider;

    @Builder
    public User(String nickname, String email, String profileImageUrl, Role role,
                AuthProvider authProvider) {
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
        this.authProvider = authProvider;
    }

    public User update(String name, String picture) {
        this.nickname = name;
        this.profileImageUrl = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}