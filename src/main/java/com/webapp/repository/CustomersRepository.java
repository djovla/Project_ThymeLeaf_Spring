package com.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long>{

}
