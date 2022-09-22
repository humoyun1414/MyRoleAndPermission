package uz.pdp.myroleandpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EnableJpaAuditing
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "created_by",updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity createdBy;

    @LastModifiedBy
    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity updatedBy;

    @Column(name = "created_at",updatable = false,nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    @JoinColumn(name = "updated_at")
    private Timestamp updatedAt;

}
