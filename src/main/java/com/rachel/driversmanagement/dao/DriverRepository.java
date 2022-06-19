package com.rachel.driversmanagement.dao;

import com.rachel.driversmanagement.entities.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "apidrivers", path = "apidrivers")
public interface DriverRepository extends PagingAndSortingRepository<Driver, Long> {

    public Driver findById(long id);

    @Query(nativeQuery = true, value = "select * from driver d where d.status = 'ACTIVE' and  d.start_time >= :start AND d.end_time <= :end")
    public List<Driver> activeDriversSameMapBoundsSameTimeWindow(LocalDateTime start, LocalDateTime end);

}
