package io.codechobostudy.user.domain;


import javax.persistence.*;

@Entity
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userid", nullable = false, length = 20)
    private String userId;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
