package io.codechobostudy.mock.user.repository;

import io.codechobostudy.mock.user.domain.MockUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockUserRepository extends JpaRepository <MockUser, Integer> {

}
