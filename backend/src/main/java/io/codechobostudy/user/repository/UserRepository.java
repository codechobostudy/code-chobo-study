package io.codechobostudy.user.repository;


import io.codechobostudy.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserDomain, Long> {

}
