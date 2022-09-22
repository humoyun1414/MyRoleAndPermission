package uz.pdp.myroleandpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "posts")
public class PostEntity extends BaseEntity {

    @Column(nullable = false,columnDefinition = "TEXT")
    private String title;

    @Column(nullable = true,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String url;

}
