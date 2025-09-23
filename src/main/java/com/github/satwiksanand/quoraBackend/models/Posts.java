package com.github.satwiksanand.quoraBackend.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Posts  implements Likeable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    @Column(nullable = false)
    private String postTitle;

    @Column(columnDefinition = "TEXT")
    private String postContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Users createdBy;

    private Long postUpvoteCount;
    private Long postDownVote;
    private Long postViews;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ManyToMany
    @Builder.Default
    private Set<Users> likedBy = new HashSet<>();

    @Override
    public UUID getId() {
        return postId;
    }

    @Override
    public void addLike(Users user) {
        likedBy.add(user);
    }

    @Override
    public int getLikeCount() {
        return likedBy.size();
    }
}
