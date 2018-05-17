package com.george.jdbc;

import com.george.jdbc.javaBeanCreator.ColumnModel;
import com.george.jdbc.javaBeanCreator.ColumnTypeEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2017/8/14.
 */
public class JdbcDao {

    // 定义sql语句的执行对象
    private static PreparedStatement pstmt;

    // 定义查询返回的结果集合
    private static ResultSet resultSet;

    /**
     * 执行更新操作
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @return 执行结果
     * @throws SQLException
     */
    public static boolean updateByPreparedStatement(Connection connection, String sql, List<?> params)
            throws SQLException {
        boolean flag = false;
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        // 填充sql语句中的占位符
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        releaseConn(connection);
        return flag;
    }

    /**
     * 执行查询操作
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> findResult(Connection connection, String sql, List<?> params)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        releaseConn(connection);
        return list;
    }

    /**
     * 释放资源
     */
    private static void releaseConn(Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取表结构
     *
     * @param tableName
     * @return
     */
    public static List<ColumnModel> getTableStructure(String tableName, Connection connection) {
        List<ColumnModel> columnModelList = new ArrayList<ColumnModel>();
        try {
            //TODO 表相关
            //ResultSet tableSet = metaData.getTables(null, "%",tableName,new String[]{"TABLE"});
            //TODO 字段相关
            ResultSet columnSet = connection.getMetaData().getColumns(null, "%", tableName, "%");
            ColumnModel columnModel = null;
            while (columnSet.next()) {
                columnModel = new ColumnModel();
                columnModel.setColumnName(columnSet.getString("COLUMN_NAME"));
                columnModel.setColumnSize(columnSet.getInt("COLUMN_SIZE"));
                columnModel.setDataType(columnSet.getString("DATA_TYPE"));
                columnModel.setRemarks(columnSet.getString("REMARKS"));
                String typeName = columnSet.getString("TYPE_NAME").toLowerCase();
                columnModel.setTypeName(typeName.contains("identity") ?
                        typeName.substring(0, typeName.indexOf(" identity")) : typeName);
                String columnClassName = ColumnTypeEnum.getColumnTypeEnumByDBType(columnModel.getTypeName());
//                String fieldName = getFieldName(columnModel.getColumnName());
                String fieldName = columnModel.getColumnName();
                String fieldType = Class.forName(columnClassName).getSimpleName();
                columnModel.setFieldName(fieldName);
                columnModel.setColumnClassName(columnClassName);
                columnModel.setFieldType(fieldType);
                columnModelList.add(columnModel);
                //System.out.println(columnModel.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnModelList;
    }

    /**
     * 将数据库字段转换成bean属性
     *
     * @param columnName
     * @return
     */
    private static String getFieldName(String columnName) {
        char[] columnCharArr = columnName.toLowerCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        int ad = -1;
        for (int i = 0; i < columnCharArr.length; i++) {
            char cur = columnCharArr[i];
            if (cur == '_') {
                ad = i;
            } else {
                if ((ad + 1) == i && ad != -1) {
                    sb.append(Character.toUpperCase(cur));
                } else {
                    sb.append(cur);
                }
                ad = -1;
            }
        }
        return sb.toString();
    }

    public static List<Map<String, Object>> executeSql(Connection connection, String sql) {
        List<Map<String, Object>> data = null;
        try {
            data = JdbcDao.findResult(connection, sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
