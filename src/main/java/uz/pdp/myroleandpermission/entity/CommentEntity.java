package uz.pdp.myroleandpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comments")
public class CommentEntity extends BaseEntity {

    @Column(nullable = false,columnDefinition = "TEXT")
    private String text;

    @ManyToOne(optional = false)
    private PostEntity postEntity;

}
