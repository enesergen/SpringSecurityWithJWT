package com.enesergen.demoproject.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity implements Serializable {

    public BaseEntity() {
    }

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid.hex")
    @GeneratedValue(generator = "uuid")
    @Column(name = "OBJID", nullable = false, updatable = false)
    private String objId;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "OPERATION_TYPE")
    private String operationType;

    @PrePersist
    public void prePersist() {
        this.active = true;
        Date date = new Date();
        this.createDate = date;
        this.updateDate = date;
        this.operationType = "SAVE";
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = new Date();
        this.operationType = "UPDATE";
    }

    @PreRemove
    public void preRemove() {
        this.updateDate = new Date();
        this.operationType = "DELETE";
        this.active = false;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return isActive() == that.isActive()
                && Objects.equals(getObjId(), that.getObjId())
                && Objects.equals(getCreateDate(), that.getCreateDate())
                && Objects.equals(getUpdateDate(), that.getUpdateDate())
                && Objects.equals(getOperationType(), that.getOperationType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getObjId(),
                getCreateDate(),
                getUpdateDate(),
                isActive(),
                getOperationType());
    }
}
