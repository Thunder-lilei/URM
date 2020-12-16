package po;

import java.sql.Timestamp;

/**
 * <h3>URM</h3>
 * <p>角色类</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 00:46
 **/
public class Role {
    Long id;
    String roleType;
    String roleName;
    Timestamp createTime;
    Timestamp updateTime;

    public Role() {}

    public Role(Long id, String roleName, String roleType, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.roleType = roleType;
        this.roleName = roleName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType='" + roleType + '\'' +
                ", roleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
