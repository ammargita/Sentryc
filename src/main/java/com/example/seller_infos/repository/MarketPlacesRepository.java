package com.example.seller_infos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seller_infos.model.MarketPlaces;

@Repository
public interface MarketPlacesRepository extends JpaRepository<MarketPlaces, Long>{

}
