package pojo;

/**
 * <h3>UserPowerControl</h3>
 * <p>UserPOJO</p>
 * userName 登录用的用户名
 * password 登录密码
 * nickname 显示的用户昵称
 * @author : 李雷
 * @date : 2020-11-23 09:49
 **/
public class UserPojo {
    private Integer id;
    private String userName;
    private String password;
    private String nickname;

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
}
