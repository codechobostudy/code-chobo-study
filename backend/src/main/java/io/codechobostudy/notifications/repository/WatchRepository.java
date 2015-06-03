package io.codechobostudy.notifications.repository;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.notifications.domain.Watch;
import io.codechobostudy.notifications.dto.WatchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends JpaRepository <Watch, Integer>{
    Watch findByWatchModuleIdxAndWatchModuleNameAndWatchUser(int moduleIdx, String moduleName, MockUser user);
}
