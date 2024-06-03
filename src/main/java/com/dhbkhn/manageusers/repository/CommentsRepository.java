package com.dhbkhn.manageusers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhbkhn.manageusers.DTO.CommentsDTO;
import com.dhbkhn.manageusers.model.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    // create new comment

    // get all comments by product id

    @Query(value = "SELECT comments.*, User.name, User.avatar, COUNT(CASE WHEN react.type = 'like' THEN 1 END) AS likes, COUNT(CASE WHEN react.type = 'dislike' THEN 1 END) AS dislikes FROM comments "
            + "INNER JOIN User ON comments.user_id = User.id LEFT JOIN react ON comments.id = react.comment_id WHERE comments.product_id = :productId GROUP BY comments.id, User.name, User.avatar", nativeQuery = true)
    public List<Object[]> getAllCommentsProductId(@Param("productId") int productId);
}
