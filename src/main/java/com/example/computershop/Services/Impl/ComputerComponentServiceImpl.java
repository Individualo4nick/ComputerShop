package com.example.computershop.Services.Impl;

import com.example.computershop.Entities.Comment;
import com.example.computershop.Entities.ComputerComponent;
import com.example.computershop.Services.ComputerComponentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ComputerComponentServiceImpl implements ComputerComponentService {
    @Override
    public boolean addComponent(ComputerComponent computerComponent, MultipartFile img) throws NoSuchAlgorithmException {
        return false;
    }

    @Override
    public boolean saveComment(Comment comment) {
        return false;
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public Iterable<ComputerComponent> getAll() {
        return null;
    }

    @Override
    public List<Comment> getAllComment(Long idComponent) {
        return null;
    }
}
