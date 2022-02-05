package com.example.farm.repository;

import com.example.farm.model.FarmData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmDataRepository extends JpaRepository<FarmData, Long> {
}
