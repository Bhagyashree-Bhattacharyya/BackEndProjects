package org.myworkspace.LibraryManagement.Entities;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements Auditable{
    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @Override
    public Date getCreationDate() {
        return createdOn;
    }

    @Override
    public void setCreationDate(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public Date getUpdateDate() {
        return updatedOn;
    }

    @Override
    public void setUpdateDate(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
