package com.github.satwiksanand.quoraBackend.models;

//these are direct comments to a post, the replies table has answers of these comments.

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
@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Comments implements Likeable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;

    @ManyToOne
    @JoinColumn
    private Users commentedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Posts post;

    @Column(columnDefinition = "bigint default 0")
    private long upvote;

    private String commentContent;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;

    @ManyToMany
    @Builder.Default
    private Set<Users> likedBy = new HashSet<>();

    @Override
    public UUID getId() {
        return commentId;
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
