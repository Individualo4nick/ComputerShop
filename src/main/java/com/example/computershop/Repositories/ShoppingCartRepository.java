package com.example.computershop.Repositories;

import com.example.computershop.Entities.ComputerComponent;
import com.example.computershop.Entities.ShoppingCart;
import com.example.computershop.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByUser(User user);
    @Query(value= "SELECT s FROM ShoppingCart s WHERE s.computerComponent.id = :componentId")
    ShoppingCart findByComputerComponentId(@Param("componentId") Long id);

    @Query(value= "SELECT s FROM ShoppingCart s WHERE s.computerComponent.id in :componentsId and s.user = :user")
    List<ShoppingCart> findAllByListIdsAndUser(@Param("componentsId") List<Long> ids, @Param("user") User user);
}
