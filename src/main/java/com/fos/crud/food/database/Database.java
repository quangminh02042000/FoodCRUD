package com.fos.crud.food.database;

import com.fos.crud.food.model.Food;
import com.fos.crud.food.repo.FoodRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/*
khởi tạo db, biến môi trường
initialize data for h2 database
 */
@Configuration
public class Database {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(FoodRepositories foodRepositories){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Food foodA = new Food("cơm rang hải sản", BigDecimal.valueOf(100000), BigDecimal.valueOf(30000));
                Food foodB = new Food("cơm gà Hải Nam", BigDecimal.valueOf(65000), BigDecimal.valueOf(25000));
                logger.info("insert data: "+foodRepositories.save(foodA));
                logger.info("insert data: "+foodRepositories.save(foodB));
            }
        };
    }
}
