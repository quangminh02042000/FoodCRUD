package com.fos.crud.food.repo;

import com.fos.crud.food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
interface giúp để thao tác với cơ sở dữ liệu
 */
@Repository
public interface FoodRepositories extends JpaRepository<Food, Long> {
    List<Food> findByName(String name);
}
