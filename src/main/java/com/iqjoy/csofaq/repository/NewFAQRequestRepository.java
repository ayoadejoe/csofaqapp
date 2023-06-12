package com.iqjoy.csofaq.repository;

import com.iqjoy.csofaq.entities.NewFAQRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewFAQRequestRepository extends JpaRepository<NewFAQRequest, Long> {
}
