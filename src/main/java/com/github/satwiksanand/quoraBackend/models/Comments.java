package com.github.satwiksanand.quoraBackend.models;

//these are direct comments to a post, the replies table has answers of these comments.

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;

    @ManyToOne
    @JoinColumn
    private Users commentedBy;

    @ManyToOne
    @JoinColumn
    private Posts post;

    @Column(columnDefinition = "bigint default 0")
    private long upvote;

    private String commentContent;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date modifiedAt;
}
