package com.dhbkhn.manageusers.service.Comments;

import java.sql.Timestamp;
import java.util.List;

import com.dhbkhn.manageusers.DTO.CommentsDTO;
import com.dhbkhn.manageusers.model.Comments;

public interface CommentsService {
    // create new comment
    public void createComment(int productId, int userId, String content, Timestamp createdAt, int likes, int dislikes);

    // get all comments by product id
    public List<Object[]> findAllByProductId(int productId);
}
