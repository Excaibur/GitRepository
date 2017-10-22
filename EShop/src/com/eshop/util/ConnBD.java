package com.eshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnBD {
    private static String className;
    private static String url;
    private static String username;
    private static String password;
    private static Connection connection;

    static {
        InputStream is=ConnBD.class.getResourceAsStream("/OraclePro");
        Properties p=new Properties();
        try {
            p.load(is);
            className=p.getProperty("classname");
            url=p.getProperty("url");
            username=p.getProperty("username");
            password=p.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            Class.forName(className);
            connection=DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConn(ResultSet rs, PreparedStatement ps,Connection con){
            try {
                if (rs!=null){
                    rs.close();
                }
                if (ps!=null){
                    ps.close();
                }
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
