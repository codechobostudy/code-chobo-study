package io.codechobostudy.user.repository;

import io.codechobostudy.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UsersConnectionRepositoryImpl implements UsersConnectionRepository {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        User user = userRepository.findByProviderIdAndUserId(connection.getKey().getProviderId(),
            connection.getKey().getProviderUserId());
        if (user == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(Integer.toString(user.getIdx()));
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        return null;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        return null;
    }
}
