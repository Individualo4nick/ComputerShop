package com.example.computershop.Services.Impl;

import com.example.computershop.Entities.Comment;
import com.example.computershop.Entities.ComputerComponent;
import com.example.computershop.ForFilter;
import org.springframework.beans.factory.annotation.Value;
import com.example.computershop.Repositories.CommentRepository;
import com.example.computershop.Repositories.ComputerComponentRepository;
import com.example.computershop.Services.ComputerComponentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.io.FilenameFilter;

@Service
public class ComputerComponentServiceImpl implements ComputerComponentService {
    @Value("${files.path}")
    private String filesPath;
    private final ComputerComponentRepository computerComponentRepository;
    private final CommentRepository commentRepository;

    public ComputerComponentServiceImpl(ComputerComponentRepository computerComponentRepository, CommentRepository commentRepository) {
        this.computerComponentRepository = computerComponentRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public boolean addComponent(ComputerComponent computerComponent, MultipartFile image) throws NoSuchAlgorithmException {
        if (computerComponentRepository.existsByTitle(computerComponent.getTitle())) {
            return false;
        }
        if (!image.isEmpty()) {
            try {
                image.transferTo(new File(filesPath + "/imageOfComponent/" + computerComponent.getTitle()
                        + "." + Objects.requireNonNull(image.getOriginalFilename()).split("\\.")[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        computerComponentRepository.save(computerComponent);
        return true;
    }


    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    @Override
    public List<Comment> getAllComment(Long idComponent) {
        return commentRepository.findAllByComponentid(idComponent);
    }

    @Override
    public List<String> getAllCategory() {
        return computerComponentRepository.getCategory();
    }

    @Override
    public List<Integer> getAllWarranty() {
        return computerComponentRepository.getWarranty();
    }

    @Override
    public List<String> getAllProducer() {
        return computerComponentRepository.getProducers();
    }

    @Override
    public Iterable<ComputerComponent> getAllComponent() {
        return computerComponentRepository.findAll();
    }

    @Override
    public ComputerComponent getComponentById(Long id) {
        return computerComponentRepository.getComputerComponentById(id);
    }

    @Override
    public Object filterComponent(ForFilter filter) {
        List<ComputerComponent> filterComponent = computerComponentRepository.findAll();
        if (filter.title != null && !filter.title.isEmpty()) {
            filterComponent = filterComponent.stream().filter(f -> f.getTitle().contains(filter.title)).toList();
        }
        if (filter.producers != null && filter.producers.size() != 0) {
            filterComponent = filterComponent.stream().filter(f -> filter.producers.contains(f.getProducer())).toList();
        }
        if (filter.categories!= null && filter.categories.size() != 0) {
            filterComponent = filterComponent.stream().filter(f -> filter.categories.contains(f.getCategory())).toList();
        }
        if (filter.warranties_in_month != null && filter.warranties_in_month.size() != 0) {
            filterComponent = filterComponent.stream().filter(f -> filter.warranties_in_month.contains(f.getWarranty_in_month())).toList();

        }
        if (filter.minPrice != -1) {
            filterComponent = filterComponent.stream().filter(f -> f.getPrice() >= filter.minPrice).toList();
        }
        if (filter.maxPrice != -1) {
            filterComponent = filterComponent.stream().filter(f -> f.getPrice() <= filter.maxPrice).toList();
        }
        return filterComponent;
    }


    @Override
    public File getComponentImage(String componentOrUserTitle) {
        File f = new File(filesPath + "/imageOfComponent/");
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(componentOrUserTitle);
            }
        });

        return matchingFiles[0];
    }

    @Override
    public File getUserImage(String userName) {
        File f = new File(filesPath + "/imageOfUser/");
        try {
            File[] matchingFiles = f.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith(userName);
                }
            });
            return matchingFiles[0];
        } catch (Exception e) {
            File[] matchingFiles = f.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith("anon");
                }
            });
            return matchingFiles[0];
        }
    }
}
