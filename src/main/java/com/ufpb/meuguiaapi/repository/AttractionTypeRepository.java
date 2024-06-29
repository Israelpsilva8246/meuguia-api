package com.ufpb.meuguiaapi.repository;

import com.ufpb.meuguiaapi.domain.AttractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionTypeRepository extends JpaRepository<AttractionType, Long> {
}
