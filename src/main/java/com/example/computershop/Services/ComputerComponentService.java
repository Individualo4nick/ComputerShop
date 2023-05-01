package com.example.computershop.Services;

import com.example.computershop.Entities.Comment;
import com.example.computershop.Entities.ComputerComponent;
import com.example.computershop.ForFilter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ComputerComponentService {
    void addComment(Comment comment);

    boolean addComponent(ComputerComponent computerComponent, MultipartFile img) throws NoSuchAlgorithmException;

    void deleteComment(Long commentId);

    List<Comment> getAllComment(Long idComponent);

    List<String> getAllCategory();

    List<Integer>  getAllWarranty();

    List<String>  getAllProducer();

    Iterable<ComputerComponent>  getAllComponent();

    ComputerComponent getComponentById(Long id);

    Object filterComponent(ForFilter filter);

    File getComponentImage(String componentTitle);

    File getUserImage(String componentTitle);
}
