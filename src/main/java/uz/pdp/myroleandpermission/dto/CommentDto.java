package uz.pdp.myroleandpermission.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private long id;

    @NotNull
    private String text;

    @NotNull
    private long postId;

}
