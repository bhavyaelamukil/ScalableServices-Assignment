package com.assignment.repo;

import com.assignment.dto.DispatchDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends JpaRepository<DispatchDetails, Long> {
}
