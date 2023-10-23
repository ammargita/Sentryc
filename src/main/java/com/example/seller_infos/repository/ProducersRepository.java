package com.example.seller_infos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seller_infos.model.Producers;

@Repository
public interface ProducersRepository extends JpaRepository<Producers, Long>{

}
