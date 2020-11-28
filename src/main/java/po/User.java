package po;

import java.sql.Timestamp;

/**
 * <h3>URM</h3>
 * <p>用户信息表</p>
 * userName 登录用的用户名
 * password 登录密码
 * nickname 显示的用户昵称
 * @author : 李雷
 * @date : 2020-11-23 09:31
 **/
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String nickname;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
