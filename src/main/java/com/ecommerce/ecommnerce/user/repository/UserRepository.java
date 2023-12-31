package com.ecommerce.ecommnerce.user.repository;

import com.ecommerce.ecommnerce.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailAndStatus(String email,Byte status);


}
