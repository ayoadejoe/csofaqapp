package com.iqjoy.csofaqapp.repository;

import com.iqjoy.csofaqapp.entities.NewFAQRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewFAQRequestRepository extends JpaRepository<NewFAQRequest, Long> {
}
