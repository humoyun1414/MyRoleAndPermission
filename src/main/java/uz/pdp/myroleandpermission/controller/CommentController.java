package uz.pdp.myroleandpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.myroleandpermission.dto.CommentDto;
import uz.pdp.myroleandpermission.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/adm")
    public HttpEntity<?>addComment(@RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.addComment(commentDto));
    }
    @GetMapping("/adm")
    public HttpEntity<?>commentList(){
        return ResponseEntity.ok(commentService.list());
    }
    @GetMapping("/public/page")
    public HttpEntity<?>listByPage(@RequestParam(value = "page")int page,
                                   @RequestParam(value = "size")int size){
        return ResponseEntity.ok(commentService.listByPage(page,size));
    }
}
