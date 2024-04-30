package com.dprsnn.UtilPlast.repositories;

import com.dprsnn.UtilPlast.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    public List<Request> findAllByStatus(String status);
    public List<Request> findAllByRequestCode(String code);
    public Request findRequestById(Long id);
}
