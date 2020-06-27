package ntduong.movieappserver.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
