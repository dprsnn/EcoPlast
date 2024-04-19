package com.dprsnn.UtilPlast.repositories;

import com.dprsnn.UtilPlast.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Vadym Hnatiuk
 */
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    BlogPost findBlogPostById(Long id);
    List<BlogPost> findAllByOrderByIdDesc();
    List<BlogPost> findFirst3ByOrderByIdDesc();


}
