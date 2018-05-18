package com.george.test;

import com.george.jdbc.Constants;
import com.george.jdbc.JdbcUtil;
import com.george.jdbc.databaseDir.DictionaryCreator;
import com.george.jdbc.databaseDir.entity.SqlServerTableEntity;
import com.george.jdbc.javaBeanCreator.BeanProcess;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 2018/5/17.
 */
public class CommonTest {

    @Test
    public void common() {
        DictionaryCreator creator = new DictionaryCreator();
        //MySql数据库字典获取
//        JdbcUtil jdbcUtil = new JdbcUtil("root", "123456",
//                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/multi_db");
//        List<MySqlTableEntity> mySqlDataDictionary = creator.getMySqlDataDictionary(jdbcUtil.getConnection(),
//                "multi_db", "db_src_info");

        //SqlServer数据库字典获取
        JdbcUtil jdbcUtil = new JdbcUtil("jic", "jic!@#",
                "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://192.168.20.73:1433;databaseName=srm");
        List<SqlServerTableEntity> tableEntities = creator.getSqlServerDataDictionary(jdbcUtil.getConnection(), "Goods");

        System.out.println();
    }

    /**
     * 生成javabean
     */
    @Test
    public void generateJavaBean() {
        String basePath = Constants.ROOT_PATH + "/src/main/java";//生成文件保存的路径
        String packagePath = "com.george.jdbc.databaseDir.entity";//包名
        String tableName = "db_details";
        String beanClassName = "MySqlTableEntity";

        //获得数据库连接
        JdbcUtil jdbcUtil = new JdbcUtil("root", "123456",
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/multi_db");
        Connection connection = jdbcUtil.getConnection();

        try {
            BeanProcess.generateJavaBeanFromDB(basePath, packagePath,
                    connection, tableName, beanClassName);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
