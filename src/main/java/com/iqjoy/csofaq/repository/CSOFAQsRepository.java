package com.iqjoy.csofaq.repository;

import com.iqjoy.csofaq.entities.CSOFAQs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CSOFAQsRepository extends JpaRepository<CSOFAQs, Integer> {
    @Query("SELECT DISTINCT c.regulator FROM CSOFAQs c")
    List<String> findAllRegulators();

    List<CSOFAQs> findByRegulator(String regulator);
}
