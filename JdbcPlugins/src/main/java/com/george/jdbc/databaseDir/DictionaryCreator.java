package com.george.jdbc.databaseDir;

import com.george.jdbc.JdbcDao;
import com.george.jdbc.databaseDir.entity.MySqlTableEntity;
import com.george.jdbc.databaseDir.entity.SqlServerTableEntity;
import com.george.jdbc.utils.JavaBeanMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : George
 *         Description : 表实体
 *         Date : Created in 15:24 2018/3/8
 *         Modified By :
 */
public class DictionaryCreator {

    private List<Map<String, Object>> getData(Connection connection, int databaseType, String databaseName, String tableName) {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        switch (databaseType) {
            case TypeOfSqlString.TYPE_OF_MYSQL:
                res = JdbcDao.executeSql(connection, TypeOfSqlString.getMySqlString(databaseName, tableName));
                break;
            case TypeOfSqlString.TYPE_OF_SQLSERVER:
                res = JdbcDao.executeSql(connection, TypeOfSqlString.getSqlServerString(tableName));
                break;
            case TypeOfSqlString.TYPE_OF_ORACLE:
                res = JdbcDao.executeSql(connection, TypeOfSqlString.getOracleString(databaseName, tableName));
                break;
            default:
                break;
        }
        return res;
    }

    public List<MySqlTableEntity> getMySqlDataDictionary(Connection connection, String databaseName, String tableName) {
        List<Map<String, Object>> data = getData(connection, TypeOfSqlString.TYPE_OF_MYSQL, databaseName, tableName);
        List<MySqlTableEntity> dictionary = null;
        try {
            //实体映射
            dictionary = JavaBeanMapper.mapEntity(data, MySqlTableEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public List<SqlServerTableEntity> getSqlServerDataDictionary(Connection connection, String tableName) {
        List<Map<String, Object>> data = getData(connection, TypeOfSqlString.TYPE_OF_SQLSERVER, "", tableName);
        List<SqlServerTableEntity> dictionary = null;
        try {
            //实体映射
            dictionary = JavaBeanMapper.mapEntity(data,SqlServerTableEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public List<SqlServerTableEntity> getOracleDataDictionary(Connection connection, String databaseName, String tableName) {
        List<SqlServerTableEntity> dictionary = new LinkedList<SqlServerTableEntity>();

        return dictionary;
    }

}
