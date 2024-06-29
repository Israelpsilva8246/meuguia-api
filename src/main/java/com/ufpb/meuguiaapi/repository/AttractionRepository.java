package com.ufpb.meuguiaapi.repository;

import com.ufpb.meuguiaapi.domain.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    @Query("SELECT obj FROM Attraction obj WHERE obj.name = :name")
    List<Attraction> findAllByName(@Param(value = "name") String name);

    @Query("SELECT obj FROM Attraction obj WHERE obj.city = :city")
    List<Attraction> findAllByCity(@Param(value = "city") String city);

    @Query("SELECT a FROM Attraction a JOIN a.attractionTypes s WHERE s.name = :attractionTypesName")
    List<Attraction> findAllByType(@Param(value = "attractionTypesName") String attractionTypes);

    @Query("SELECT a FROM Attraction a JOIN a.segmentations s WHERE s.name = :segmentationName")
    List<Attraction> findAllBySegmentationName(@Param("segmentationName") String segmentationName);
}
