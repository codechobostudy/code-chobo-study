package io.codechobostudy.common.repository;


import io.codechobostudy.common.domain.BoardAttachment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardAttachmentRepository extends JpaRepository<BoardAttachment, Long>{
	
}
