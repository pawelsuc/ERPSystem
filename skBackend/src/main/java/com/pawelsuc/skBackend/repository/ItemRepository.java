package com.pawelsuc.skBackend.repository;

import com.pawelsuc.skBackend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
