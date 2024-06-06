package org.myworkspace.LibraryManagement.Entities;

import java.util.Date;

public interface Auditable {

    Date getCreationDate();
    void setCreationDate(Date createdOn);

    Date getUpdateDate();
    void setUpdateDate(Date updatedOn);
}
