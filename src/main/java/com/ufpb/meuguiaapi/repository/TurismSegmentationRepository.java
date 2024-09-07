package com.ufpb.meuguiaapi.repository;

import com.ufpb.meuguiaapi.domain.TouristSegmentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurismSegmentationRepository extends JpaRepository<TouristSegmentation, Long> {

}
