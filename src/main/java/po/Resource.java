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
    Integer id;
    Integer menuResourceId;
    String resourceType;
    String resourceName;
    Timestamp createTime;
    Timestamp updateTime;

    public Resource() {}

    public Resource(Integer id, Integer menuResourceId, String resourceType, String resourceName,
                    Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.menuResourceId = menuResourceId;
        this.resourceType = resourceType;
        this.resourceName = resourceName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuResourceId() {
        return menuResourceId;
    }

    public void setMenuResourceId(Integer menuResourceId) {
        this.menuResourceId = menuResourceId;
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
