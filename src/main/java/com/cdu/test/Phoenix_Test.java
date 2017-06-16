package com.cdu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Phoenix_Test {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            connection =  DriverManager.getConnection("jdbc:phoenix:node114,node113:2181:/hbase-unsecure","","");
            statement = connection.createStatement();          
            //statement.execute("create sequence seq");
            //statement.execute("create table test3(rowkey varchar primary key , fence.id varchar, fence.name varchar , pageId bigint)");
            /*System.out.println(statement.executeUpdate("upsert into test3 values('1','1','张三',next value for seq)"));
            System.out.println(statement.executeUpdate("upsert into test3 values('2','2','李四',next value for seq)"));
            System.out.println(statement.executeUpdate("upsert into test3 values('3','3','王五',next value for seq)"));
            System.out.println(statement.executeUpdate("upsert into test3 values('4','4','麻子',next value for seq)"));
            System.out.println(statement.executeUpdate("upsert into test3 values('5','5','哈哈',next value for seq)"));*/
            connection.commit();
            ResultSet rs = statement.executeQuery("select pageId from test3 order by pageId desc");
            while (rs.next()) { 
                //System.out.println(rs.getString("rowkey") + ":" + rs.getString("id") + ":" + rs.getString("name") + ":" + rs.getString("pageId"));
                System.out.println(rs.getString("pageId"));
            }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
