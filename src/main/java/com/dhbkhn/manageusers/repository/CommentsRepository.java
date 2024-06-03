package com.dhbkhn.manageusers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhbkhn.manageusers.DTO.CommentsDTO;
import com.dhbkhn.manageusers.model.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    // create new comment

    // get all comments by product id
    @Query("SELECT c, u.name AS user_name, u.avatar AS user_avatar FROM Comments c INNER JOIN User u ON c.user_id = u.id WHERE c.product_id = :productId")
    public List<Object[]> getAllCommentsProductId(@Param("productId") int productId);
}
