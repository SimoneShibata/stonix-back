package com.escoladeti.oldowl.stonix.forum.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by tdc on 01/05/16.
 */
@Entity
public class Question extends BasicForum{

    private String title;
    private Integer views;

    @OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL, mappedBy = "question")
    private List<Answer> answerList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    private List<CommentQuestion> commentList;

    public void transformAnswerInList(Answer answer){
        List<Answer> answers = getAnswerList();
        answers.add(answer);
        this.setAnswerList(answers);
    }

    public void transformCommentQuestionInList(CommentQuestion commentQuestion){
        List<CommentQuestion> commentQuestions = getCommentList();
        commentQuestions.add(commentQuestion);
        this.setCommentList(commentQuestions);
    }

    public List<CommentQuestion> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentQuestion> commentList) {
        this.commentList = commentList;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Question() {
        super();
        this.views = 0;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question that = (Question) o;
        return Objects.equal(this.title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, views);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("title", title)
                .add("views", views)
                .add("created", getCreated())
                .add("dead", getDead())
                .add("status", getStatus())
                .add("last_update", getLastUpdate())
                .add("description", getDescription())
                .toString();
    }
}
