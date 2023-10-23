package com.example.seller_infos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seller_infos.model.SellerInfos;
import com.example.seller_infos.model.Sellers;

@Repository
public interface SellersRepository extends JpaRepository<Sellers, Long>{

	List<Sellers> findBySellerInfos(SellerInfos infos);

}
