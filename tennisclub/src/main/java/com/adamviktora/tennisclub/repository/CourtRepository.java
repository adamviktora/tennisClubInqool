package com.adamviktora.tennisclub.repository;

import com.adamviktora.tennisclub.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Integer> {

    @Query(
            value = "SELECT surface_id FROM court WHERE id = ?1",
            nativeQuery = true
    )
    Integer getSurfaceId(int courtId);
}
