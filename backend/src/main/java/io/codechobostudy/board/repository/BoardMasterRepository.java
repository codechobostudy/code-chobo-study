package io.codechobostudy.board.repository;

import io.codechobostudy.board.domain.BoardMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMasterRepository extends JpaRepository<BoardMaster, Long> {

}
