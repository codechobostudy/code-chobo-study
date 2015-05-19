package io.codechobostudy.notifications.repository;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.notifications.domain.NotiCnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiCntRepository extends JpaRepository <NotiCnt, Integer>{
    NotiCnt findByUser(MockUser user);
}
