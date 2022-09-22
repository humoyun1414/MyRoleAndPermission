package uz.pdp.myroleandpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.myroleandpermission.dto.PostDto;
import uz.pdp.myroleandpermission.entity.PostEntity;
import uz.pdp.myroleandpermission.payload.ApiResponses;
import uz.pdp.myroleandpermission.repository.PostRepository;
import uz.pdp.myroleandpermission.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/adm")
    public HttpEntity<?> addPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.addPost(postDto));
    }

    @GetMapping("/adm/list")
    public HttpEntity<?> getList() {
        return ResponseEntity.ok(postService.getList());
    }

    @GetMapping("/page")
    public HttpEntity<?> entityPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(postService.getPostsByPage(page, size));
    }

    @PutMapping("/adm/{id}")
    public HttpEntity<?> editPost(@PathVariable Integer id, PostDto postDto) {
        return ResponseEntity.ok(postService.editPost(id, postDto));
    }

    @DeleteMapping("/adm/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.delete(id));
    }

}
