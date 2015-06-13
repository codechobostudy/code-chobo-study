package io.codechobostudy.notifications.repository;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.notifications.domain.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository <Watch, Integer>{
    Watch findByWatchModuleIdxAndWatchModuleNameAndWatchUser(int moduleIdx, String moduleName, MockUser user);

    List<Watch> findByWatchModuleIdxAndWatchModuleName(int watchModuleIdx, String watchModuleName);
}
