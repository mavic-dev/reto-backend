package com.bancom.retobackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class Monitoring {
    @Column(name = "created_date")
    @CreatedDate()
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate()
    private LocalDateTime modifiedData;

}
