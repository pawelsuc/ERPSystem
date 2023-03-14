package com.pawelsuc.skBackend.repository;

import com.pawelsuc.skBackend.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository <Warehouse, Long> {
}
