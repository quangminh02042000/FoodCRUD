package com.fos.crud.food.controller;

import com.fos.crud.food.model.Food;
import com.fos.crud.food.model.ResponseObject;
import com.fos.crud.food.repo.FoodRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Foods")
/*
điều hướng các request đến business và view tương ứng
 */
public class FoodController {
    @Autowired
    private FoodRepositories foodRepositories;

    @GetMapping("")
    List<Food> getAllFoods(){
        return foodRepositories.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Food> foundFood = foodRepositories.findById(id);
        // check found product null or not
        return foundFood.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(new
                        ResponseObject("ok", "Query food successfully", foundFood)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                        ResponseObject("failed", "Cannot find food with id = " + id, "")
                );
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> insertFood(@RequestBody Food newFood){
        // 2 products must not have the same name
        List<Food> foundFood = foodRepositories.findByName(newFood.getName().trim());
        if(foundFood.size() > 0 ){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Food name already existed", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert food successfully",
                        foodRepositories.save(newFood))
        );
    }

    @PatchMapping("/{id}")
    ResponseEntity<ResponseObject> updateFood(@RequestBody Food newFood, @PathVariable Long id){
        Food updatedFood = foodRepositories.findById(id)
                .map(food -> {
                    food.setName(newFood.getName());
                    food.setSalePrice(newFood.getSalePrice());
                    food.setCostPrice(newFood.getCostPrice());
                    return foodRepositories.save(food);
                }).orElseGet(() -> {
                    newFood.setId(id);
                    return foodRepositories.save(newFood);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "updated food successfully", updatedFood)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteFood(@PathVariable Long id){
        boolean exists = foodRepositories.existsById(id);
        if(exists){
            foodRepositories.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "delete food successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "can not find food to delete", "")
        );
    }


}
