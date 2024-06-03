package com.dhbkhn.manageusers.service.Comments;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhbkhn.manageusers.DTO.CommentsDTO;
import com.dhbkhn.manageusers.model.Comments;
import com.dhbkhn.manageusers.repository.CommentsRepository;

@Service
public class CommentsServiceImpl implements CommentsService {

    public final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    // create new comment
    @Override
    public void createComment(int productId, int userId, String content, Timestamp createdAt, int likes, int dislikes) {
        Comments comment = new Comments(productId, userId, content, createdAt, likes, dislikes);
        commentsRepository.save(comment);
    }

    // get all comments by product id
    @Override
    public List<Object[]> findAllByProductId(int productId) {
        return commentsRepository.getAllCommentsProductId(productId);
    }
}
