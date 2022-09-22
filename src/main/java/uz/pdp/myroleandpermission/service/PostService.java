package uz.pdp.myroleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.pdp.myroleandpermission.dto.PostDto;
import uz.pdp.myroleandpermission.entity.PostEntity;
import uz.pdp.myroleandpermission.exception.PostNotFoundException;
import uz.pdp.myroleandpermission.payload.ApiResponses;
import uz.pdp.myroleandpermission.repository.PostRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDto addPost(PostDto postDto) {
        boolean title = postRepository.findByTitle(postDto.getTitle());
        if (title) {
            throw new PostNotFoundException("Such an article is available in the system");
        }
        PostEntity post = new PostEntity();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setUrl("kunUz");
        PostEntity postEntity = postRepository.save(post);

        long id = postEntity.getId();
        postDto.setId(id);
        return postDto;
    }

    public List<PostDto> getList() {
        List<PostDto> postDtoList = new LinkedList<>();
        postRepository.findAll().forEach(postEntity -> {
            postDtoList.add(toDto(postEntity));
        });
        if (postDtoList.isEmpty()) {
            throw new PostNotFoundException("Post not found");
        }
        return postDtoList;
    }

    public List<PostDto> getPostsByPage(int page, int size) {
        List<PostDto>dtoList = new LinkedList<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        postRepository.findAll(pageable).forEach(postEntity -> {
            dtoList.add(toDto(postEntity));
        });
        if (dtoList.isEmpty()){
            throw new PostNotFoundException("Post not found");
        }
        else {
            return dtoList;
        }

    }

    public PostDto editPost(Integer id, PostDto postDto) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postRepository.save(postEntity);
        return toDto(postEntity);
    }

    public ApiResponses delete(Integer id) {
        try {
            postRepository.deleteById(id);
            return new ApiResponses("Deleted", true);

        } catch (Exception e) {
            return new ApiResponses("Post not found", false);
        }
    }
    public PostDto toDto(PostEntity postEntity) {
        PostDto postDto = new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        return postDto;
    }
}
