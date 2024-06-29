package com.petwellnes.petwellnes_backend.service;

import com.petwellnes.petwellnes_backend.model.entity.Comment;
import com.petwellnes.petwellnes_backend.model.entity.Post;
import com.petwellnes.petwellnes_backend.model.dto.postDto.CommentDTO;

import java.util.List;

public interface CommentService {
    void comment(Comment comment);
    List<CommentDTO> findAllByPost(Post post);
}
