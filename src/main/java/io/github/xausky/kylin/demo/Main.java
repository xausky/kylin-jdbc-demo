package io.github.xausky.kylin.demo;

import java.sql.*;
import java.util.Properties;

/**
 * Created by xausky on 9/22/16.
 */
public class Main {
    public static void main(String[] args){
        try {
            Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();

            Properties info = new Properties();
            info.put("user", "ADMIN");
            info.put("password", "KYLIN");
            Connection conn = driver.connect("jdbc:kylin://172.20.0.202:7070/HRMS", info);
            //Statement state = conn.createStatement();
            //ResultSet resultSet = state.executeQuery("select * from  test");
            //ResultSetMetaData metaData = resultSet.getMetaData();
            ResultSet resultSet = conn.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
            for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
                System.out.printf("%25s",resultSet.getMetaData().getColumnName(i));
            }
            System.out.print("\n");
            while(resultSet.next()){
                for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
                    System.out.printf("%25s",resultSet.getObject(resultSet.getMetaData().getColumnName(i)));
                }
                System.out.print("\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
