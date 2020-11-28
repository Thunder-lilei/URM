package po;

import java.sql.Timestamp;

/**
 * <h3>UserPowerControl</h3>
 * <p>按钮资源类</p>
 * btnResourceName 按钮资源名称
 * menuResources 菜单名称 菜单是按钮上级
 * url 按钮的页面资源的访问路径
 * btnResourceType 按钮的类型
 * @author : 李雷
 * @date : 2020-11-24 14:31
 **/
public class BtnResource {
    private Integer id;
    private String btnResourceName;
    private String menuResourceName;
    private String url;
    private String btnResourceType;
    private String btnResourceNickname;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBtnResourceName() {
        return btnResourceName;
    }

    public void setBtnResourceName(String btnResourceName) {
        this.btnResourceName = btnResourceName;
    }

    public String getMenuResourceName() {
        return menuResourceName;
    }

    public void setMenuResourceName(String menuResourceName) {
        this.menuResourceName = menuResourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBtnResourceType() {
        return btnResourceType;
    }

    public void setBtnResourceType(String btnResourceType) {
        this.btnResourceType = btnResourceType;
    }

    public String getBtnResourceNickname() {
        return btnResourceNickname;
    }

    public void setBtnResourceNickname(String btnResourceNickname) {
        this.btnResourceNickname = btnResourceNickname;
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
