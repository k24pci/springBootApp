package com.ucx.training.sessions.app.entity;


import com.ucx.training.sessions.app.type.RecordStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity<T> {
    @Id //Indicates that this field is a Primary Key.
    //Id generation strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    @Column(length = 1000)
    private String description;
    @Version
    private Long version;
}