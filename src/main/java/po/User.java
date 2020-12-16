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
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Timestamp createTime;
    private Timestamp updateTime;

    public User() {}

    public User(Long id,String username,String password,String nickname,Timestamp createTime,Timestamp updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
