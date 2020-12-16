package po;

import java.sql.Timestamp;

/**
 * <h3>URM</h3>
 * <p>资源类</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 00:43
 **/
public class Resource {
    Long id;
    Long menuResourceId;
    String controlType;
    String resourceType;
    String resourceName;
    Timestamp createTime;
    Timestamp updateTime;

    public Resource() {}

    public Resource(Long id, Long menuResourceId, String controlType, String resourceType,
                    String resourceName, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.menuResourceId = menuResourceId;
        this.controlType = controlType;
        this.resourceType = resourceType;
        this.resourceName = resourceName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuResourceId() {
        return menuResourceId;
    }

    public void setMenuResourceId(Long menuResourceId) {
        this.menuResourceId = menuResourceId;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
