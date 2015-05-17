package io.codechobostudy.notifications.repository;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.notifications.domain.Noti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotiRepository extends JpaRepository <Noti, Integer>{
    List<Noti> findByUsers(MockUser users);

    int countByUsersAndModule(MockUser user, String module);

    int countByUsers(MockUser user);
}
