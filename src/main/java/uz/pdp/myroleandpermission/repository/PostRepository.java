package uz.pdp.myroleandpermission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import uz.pdp.myroleandpermission.entity.PostEntity;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Integer> {

    boolean findByTitle(String title);

    Optional<PostEntity> findById(long postId);


//    Page<PostEntity> findAll(Pageable pageable, Sort sort);
}
