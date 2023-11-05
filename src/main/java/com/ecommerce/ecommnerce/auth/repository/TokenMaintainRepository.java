package com.ecommerce.ecommnerce.auth.repository;

import com.ecommerce.ecommnerce.auth.entity.TokenMaintainTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenMaintainRepository extends JpaRepository<TokenMaintainTable,Long> {

  //  TokenMaintainTable findByEmailAndStatus(String email,int status);


    Optional<TokenMaintainTable> findByEmailAndStatus(String email,int status);

    Optional<TokenMaintainTable> findByTokenAndStatus(String token,int status);

}
