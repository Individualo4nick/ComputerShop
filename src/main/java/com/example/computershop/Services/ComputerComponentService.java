package com.example.computershop.Services;

import com.example.computershop.Entities.Comment;
import com.example.computershop.Entities.ComputerComponent;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ComputerComponentService {
    boolean saveComment(Comment comment);
    void deleteComment(Long commentId);

    Iterable<ComputerComponent> getAll();

    List<Comment> getAllComment(Long idComponent);
}
