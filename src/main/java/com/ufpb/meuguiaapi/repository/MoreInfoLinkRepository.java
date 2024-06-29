package com.ufpb.meuguiaapi.repository;

import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoreInfoLinkRepository extends JpaRepository<MoreInfoLink, Long> {
}
