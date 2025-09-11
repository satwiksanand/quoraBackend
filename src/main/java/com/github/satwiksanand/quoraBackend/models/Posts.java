package com.github.satwiksanand.quoraBackend.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    @Column(columnDefinition = "TEXT")
    private String postContent;

    @ManyToOne
    @JoinColumn
    private Users createdBy;

    private Long postUpvoteCount;
    private Long postDownVote;
    private Long postViews;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
