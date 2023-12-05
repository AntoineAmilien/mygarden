package com.mygarden.mygarden.repository;

import com.mygarden.mygarden.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
