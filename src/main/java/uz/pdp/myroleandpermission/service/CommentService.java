package uz.pdp.myroleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.myroleandpermission.dto.CommentDto;
import uz.pdp.myroleandpermission.entity.CommentEntity;
import uz.pdp.myroleandpermission.entity.PostEntity;
import uz.pdp.myroleandpermission.exception.CommentNotFoundException;
import uz.pdp.myroleandpermission.exception.PostNotFoundException;
import uz.pdp.myroleandpermission.repository.CommentRepository;
import uz.pdp.myroleandpermission.repository.PostRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public CommentDto addComment(CommentDto commentDto) {
        Optional<PostEntity> optionalPost = postRepository.findById((commentDto.getPostId()));
        if (optionalPost.isPresent()) {
            CommentEntity comment = new CommentEntity();
            comment.setText(comment.getText());
            comment.setPostEntity(optionalPost.get());
            CommentEntity commentEntity = commentRepository.save(comment);

            commentDto.setId(commentEntity.getId());
            return commentDto;
        }
        throw new PostNotFoundException("Post not found");
    }

    public List<CommentDto> list() {
        List<CommentDto> dtoList = new LinkedList<>();
        List<CommentEntity> entityList = commentRepository.findAll();
        for (CommentEntity comment : entityList) {
            dtoList.add(toDto(comment));
        }
        if (dtoList.isEmpty()){
            throw new CommentNotFoundException("comment not found ");
        }
        return dtoList;
    }

    public List<CommentDto> listByPage(int page, int size) {
        List<CommentDto>dtoList = new LinkedList<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        commentRepository.findAll(pageable).forEach(commentEntity -> {
            dtoList.add(toDto(commentEntity));
        });
        return dtoList;
    }
    public CommentDto toDto(CommentEntity comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setPostId(comment.getPostEntity().getId());
        return dto;
    }
}
