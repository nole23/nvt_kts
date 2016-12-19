package com.konstrukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konstrukcija.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByName(String name);
}
