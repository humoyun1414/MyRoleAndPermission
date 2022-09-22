package uz.pdp.myroleandpermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.myroleandpermission.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

}
