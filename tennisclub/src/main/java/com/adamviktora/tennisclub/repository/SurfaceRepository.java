package com.adamviktora.tennisclub.repository;

import com.adamviktora.tennisclub.entity.Surface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurfaceRepository extends JpaRepository<Surface, Integer> {

}
