package com.dprsnn.UtilPlast.repositories;

import com.dprsnn.UtilPlast.models.PointAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<PointAddress, Long> {
    public PointAddress getPointAddressById(Long id);
}
