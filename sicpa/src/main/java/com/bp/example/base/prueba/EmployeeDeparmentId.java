package com.bp.example.base.prueba;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeDeparmentId
    implements Serializable {
 
    @Column(name = "post_id")
    private Long postId;
 
    @Column(name = "tag_id")
    private Long tagId;
 
    private EmployeeDeparmentId() {}
 
    public EmployeeDeparmentId(
        Long postId,
        Long tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }
 
    //Getters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EmployeeDeparmentId that = (EmployeeDeparmentId) o;
        return Objects.equals(postId, that.postId) &&
               Objects.equals(tagId, that.tagId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(postId, tagId);
    }
}