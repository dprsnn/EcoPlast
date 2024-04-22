package com.dprsnn.UtilPlast.repositories;

import com.dprsnn.UtilPlast.models.PlasticCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<PlasticCategory, Long> {
    public PlasticCategory findPlasticCategoryById(Long id);
}
