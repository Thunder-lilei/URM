package service.resource.value;

import service.resource.BtnResourceService;
import service.resource.MenuResourceService;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>页面标签表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-25 10:02
 **/
public class BtnResourceValueService {

    public static List<String> selectBtnResourceValueInputByBtnResourceId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_btn_resource_value where " +
                    "btn_resource_id = ? and value_type = ?");
            pstat.setInt(1,id);
            pstat.setString(2,"input");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("value"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    public static String selectBtnResourceValueActionByBtnResourceId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_btn_resource_value where " +
                    "btn_resource_id = ? and value_type = ?");
            pstat.setInt(1,id);
            pstat.setString(2,"action");
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                return rs.getString("value");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Integer addBtnResourceValue(String btnResourceName,String btnResourceNickName) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into btn_resource_value(btn_resource_id," +
                    "btn_resource_type,value,value_type) values(?,?,?,?)");
            pstat.setInt(1, BtnResourceService.selectBtnReourceIdByName("addRolePower"));
            pstat.setString(2,"add");
            pstat.setString(3,"<input name=\"checkbox\" type=\"checkbox\" value=\""+btnResourceName+"\" />"+
                    btnResourceNickName+"<br/>");
            pstat.setString(4,"input");
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public static Integer insertBtnResourceValueDelete(String btnResourceName,String valueType) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into btn_resource_value(btn_resource_id," +
                    "btn_resource_type,value,value_type) values(?,?,?,?)");
            pstat.setInt(1,BtnResourceService.selectBtnReourceIdByName(btnResourceName));
            pstat.setString(2,"delete");

            switch (valueType) {
                case "input":
                pstat.setString(3,"<input name=\"name\" type=\"text\" placeholder=\"name\" required><br />");
                pstat.setString(4,"input");
                    break;
                case "action":
                pstat.setString(3,"/Delete"+
                        MenuResourceService.selectTypeById(BtnResourceService.selectMenuResourceIdById
                                (BtnResourceService.selectBtnReourceIdByName(btnResourceName))) +"servlet");
                pstat.setString(4,"action");
                    break;
                default:
                    break;
            }

            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

}
