package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.vo;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.Comment;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@EqualsAndHashCode
public class CommentChildren {

    @OneToMany(mappedBy = "PARENT", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

}
