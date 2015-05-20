package io.codechobostudy.user.repository;


import io.codechobostudy.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNickname(String nickname);

    User findByProviderIdAndUserId(String providerId, String userId);

}
