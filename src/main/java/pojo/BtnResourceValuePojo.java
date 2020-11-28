package pojo;

/**
 * <h3>UserPowerControl</h3>
 * <p>按钮的页面资源</p>
 * btnResourceId 按钮的id
 * btnResourceType 按钮的类型
 * value 按钮的页面资源
 * valueType 按钮的页面资源类型
 * @author : 李雷
 * @date : 2020-11-26 17:03
 **/
public class BtnResourceValuePojo {
    private Integer id;
    private Integer btnResourceId;
    private String btnResourceType;
    private String value;
    private String valueType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBtnResourceId() {
        return btnResourceId;
    }

    public void setBtnResourceId(Integer btnResourceId) {
        this.btnResourceId = btnResourceId;
    }

    public String getBtnResourceType() {
        return btnResourceType;
    }

    public void setBtnResourceType(String btnResourceType) {
        this.btnResourceType = btnResourceType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
