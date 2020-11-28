package po;

import java.sql.Timestamp;

/**
 * <h3>UserPowerControl</h3>
 * <p>菜单资源类</p>
 * name 菜单名称
 * type 菜单所属的类型
 * @author : 李雷
 * @date : 2020-11-24 11:06
 **/

public class MenuResource {
    private Integer id;
    private String name;
    private String type;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
