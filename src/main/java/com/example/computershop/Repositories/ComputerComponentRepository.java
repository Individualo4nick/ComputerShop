package com.example.computershop.Repositories;

import com.example.computershop.Entities.ComputerComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComputerComponentRepository extends JpaRepository<ComputerComponent, Long> {
    boolean existsByTitle(String title);

    ComputerComponent getComputerComponentById(Long id);

    @Query(value= "SELECT DISTINCT c.producer FROM ComputerComponent c")
    List<String> getProducers();
    @Query(value= "SELECT DISTINCT c.category FROM ComputerComponent c")
    List<String> getCategory();

    @Query(value= "SELECT DISTINCT c.warranty_in_month FROM ComputerComponent c")
    List<Integer> getWarranty();
}
