package io.codechobostudy.mock.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class MockUser {
    @Id
    private String userId;

    @Column
    private String userName;

    public MockUser(){}

    public MockUser(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
