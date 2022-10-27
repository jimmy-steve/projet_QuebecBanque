package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    //    Integer countById(Integer id);
//@Query(
//        value = "select * from car where is_rented = true",
//        nativeQuery = true)
    @Query(value = "SELECT u from User u WHERE u.username = :username")
    User findUserByUserName(String username);
}
