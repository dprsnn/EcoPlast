package com.dprsnn.UtilPlast.repositories;


import com.dprsnn.UtilPlast.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vadym Hnatiuk
 */
public interface BlogPostImageRepository extends JpaRepository<Image, Long> {
    public void deleteImageById(Long id);
    public Image findImageById(Long id);
}
