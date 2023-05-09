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

    File getComponentImage(String componentTitle);

    File getUserImage(String componentTitle);

    void deletePage(Long idComponent);

    List<Comment> getAllComment(Long idComponent);

    List<String> getAllCategory();

    ComputerComponent getComponentById(Long id);

    Object filterComponent(ForFilter filter);

    List<Integer>  getAllWarranty();

    List<String>  getAllProducer();

    Iterable<ComputerComponent>  getAllComponent();

}
