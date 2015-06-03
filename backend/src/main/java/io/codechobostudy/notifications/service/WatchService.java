package io.codechobostudy.notifications.service;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.Watch;
import io.codechobostudy.notifications.dto.WatchDTO;
import io.codechobostudy.notifications.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchService {
    @Autowired
    private WatchRepository watchRepository;

    public void setupWatch(WatchDTO watchDTO, MockUserDTO userDTO) {
        if (null == watchDTO || null == userDTO){
            throw new IllegalArgumentException();
        }
        watchDTO.setWatchUserDTO(userDTO);
        watchRepository.save(watchDTO.toDomain(watchDTO));
    }

    public WatchDTO getWatchUser(WatchDTO watchDTO, MockUserDTO userDTO) {
        Watch watch = watchDTO.toDomain(watchDTO);
        int moduleIdx = watch.getWatchModuleIdx();
        String moduleName = watch.getWatchModuleName();
        MockUser user = userDTO.toDomain(userDTO);

        Watch dbWatch = watchRepository.findByWatchModuleIdxAndWatchModuleNameAndWatchUser(moduleIdx, moduleName, user);
        if (dbWatch != null){
            return new WatchDTO().toDTO(dbWatch);
        } else {
            return null;
        }
    }

    public String watchStatus(WatchDTO watchDTO, MockUserDTO userDTO) {
        WatchDTO resultWatchDTO = getWatchUser(watchDTO, userDTO);
        if (resultWatchDTO != null){
            return "on";
        } else {
            return "off";
        }
    }
}
