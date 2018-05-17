package com.george.jdbc.databaseDir;

import com.george.jdbc.JdbcDao;
import com.george.jdbc.databaseDir.entity.TableEntity;

import java.sql.Connection;
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

    public List<TableEntity> getMySqlDataDictionary(Connection connection, String databaseName, String tableName) {
        List<TableEntity> dictionary = new LinkedList<TableEntity>();
        List<Map<String, Object>> data = getData(connection, TypeOfSqlString.TYPE_OF_MYSQL, databaseName, tableName);

        for (Map<String, Object> map : data) {
            TableEntity tableEntity = new TableEntity();

        }

        return dictionary;
    }

    public List<TableEntity> getSqlServerDataDictionary(Connection connection, String tableName) {
        List<TableEntity> dictionary = new LinkedList<TableEntity>();
        List<Map<String, Object>> data = getData(connection, TypeOfSqlString.TYPE_OF_SQLSERVER, "", tableName);
        for (Map<String, Object> map : data) {
            TableEntity tableEntity = new TableEntity();
            tableEntity.setAllowNull(map.get("允许空") != null && !"".equals(map.get("允许空").toString()));
            tableEntity.setColumnName(map.get("列名").toString());
            tableEntity.setColumnRemark(map.get("列说明").toString());
            tableEntity.setDatabaseType(TypeOfSqlString.TYPE_OF_SQLSERVER);
            tableEntity.setDataLength((Short) map.get("长度"));
            tableEntity.setDataTyle(map.get("数据类型").toString());
            tableEntity.setDecimalLength((Integer) map.get("小数位数"));
            tableEntity.setDefaultValue(map.get("默认值").toString());
            tableEntity.setFlag(map.get("标识") != null && !"".equals(map.get("标识").toString()));
            tableEntity.setKeyFlag(map.get("主键") != null && !"".equals(map.get("主键").toString()));
            tableEntity.setSerialNum((Short) map.get("序号"));

            dictionary.add(tableEntity);
        }
        return dictionary;
    }

    public List<TableEntity> getOracleDataDictionary(Connection connection, String databaseName, String tableName) {
        List<TableEntity> dictionary = new LinkedList<TableEntity>();

        return dictionary;
    }

}
