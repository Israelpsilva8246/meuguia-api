package com.ufpb.meuguiaapi.repository;

import com.ufpb.meuguiaapi.domain.TurismSegmentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurismSegmentationRepository extends JpaRepository<TurismSegmentation, Long> {

}
