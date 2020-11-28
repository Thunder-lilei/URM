package service.resource.value;

import constant.ConstantClassField;
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
 * <h3>UserPowerControl</h3>
 * <p>PowerValueService</p>
 *
 * @author : 李雷
 * @date : 2020-11-25 10:02
 **/
public class BtnResourceValueService {
    private static final String VALUE_TYPE_INPUT = "input";
    private static final String VALUE_TYPE_ACTION = "action";
    private static final String VALUE = "value";
    private static final String BTN_RESOURCE_TYPE_DELETE = "delete";
    private static final String ROLE_BTN_RESOURCE = "addRolePower";
    private static final String ROLE_BTN_RESOURCE_ADD = "add";
    private static final String ROLE_BTN_RESOURCE_VALUE_TYPE = "input";
    private static final String ROLE_BTN_RESOURCE_VALUE_FIRST = "<input name=\"checkbox\" type=\"checkbox\" value=\"";
    private static final String ROLE_BTN_RESOURCE_VALUE_SECOND = "\" />";
    private static final String ROLE_BTN_RESOURCE_VALUE_THIRD = "<br/>";
    private static final String ROLE_BTN_RESOURCE_VALUE_DELETE = "<input name=\"name\" type=\"text\" placeholder=\"name\" required><br />";
    private static final String ROLE_BTN_RESOURCE_VALUE_DELETE_FIRST = "/Delete";
    private static final String ROLE_BTN_RESOURCE_VALUE_DELETE_SECOND = "servlet";

    public static List<String> selectBtnResourceValueInputByBtnResourceId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_btn_resource_value where " +
                    "btn_resource_id = ? and value_type = ?");
            pstat.setInt(1,id);
            pstat.setString(2,VALUE_TYPE_INPUT);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(VALUE));
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
            pstat.setString(2,VALUE_TYPE_ACTION);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                return rs.getString(VALUE);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Integer addBtnResourceValue(String btnResourceName,String btnResourceNickName) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = ConstantClassField.RESULT_ZERO;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into btn_resource_value(btn_resource_id," +
                    "btn_resource_type,value,value_type) values(?,?,?,?)");
            pstat.setInt(1, BtnResourceService.selectBtnReourceIdByName(ROLE_BTN_RESOURCE));
            pstat.setString(2,ROLE_BTN_RESOURCE_ADD);
            pstat.setString(3,ROLE_BTN_RESOURCE_VALUE_FIRST+btnResourceName+ROLE_BTN_RESOURCE_VALUE_SECOND+
                    btnResourceNickName+ROLE_BTN_RESOURCE_VALUE_THIRD);
            pstat.setString(4,ROLE_BTN_RESOURCE_VALUE_TYPE);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public static Integer insertBtnResourceValueDelete(String btnResourceName,String valueType) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = ConstantClassField.RESULT_ZERO;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into btn_resource_value(btn_resource_id," +
                    "btn_resource_type,value,value_type) values(?,?,?,?)");
            pstat.setInt(1,BtnResourceService.selectBtnReourceIdByName(btnResourceName));
            pstat.setString(2,BTN_RESOURCE_TYPE_DELETE);

            switch (valueType) {
                case VALUE_TYPE_INPUT:
                pstat.setString(3,ROLE_BTN_RESOURCE_VALUE_DELETE);
                pstat.setString(4,VALUE_TYPE_INPUT);
                    break;
                case VALUE_TYPE_ACTION:
                pstat.setString(3,ROLE_BTN_RESOURCE_VALUE_DELETE_FIRST+
                        MenuResourceService.selectTypeById(BtnResourceService.selectMenuResourceIdById
                                (BtnResourceService.selectBtnReourceIdByName(btnResourceName))) +ROLE_BTN_RESOURCE_VALUE_DELETE_SECOND);
                pstat.setString(4,VALUE_TYPE_ACTION);
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
