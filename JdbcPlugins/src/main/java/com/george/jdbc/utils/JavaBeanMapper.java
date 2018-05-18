package com.george.jdbc.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : George
 *         Description : 数据库数据映射到javabean上
 *         Date : Created in 14:25 2018/5/18
 *         Modified By :
 */
public class JavaBeanMapper {
    /**
     * 将结果集转换成实体对象集合
     *
     * @param rs 结果集
     * @param cc 实体对象映射类
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> mapEntity(ResultSet rs, Class cc) throws SQLException, InstantiationException, IllegalAccessException {
        //结果集 中列的名称和类型的信息
        ResultSetMetaData rsm = rs.getMetaData();
        int colNumber = rsm.getColumnCount();
        List<T> list = new ArrayList<T>();
        Field[] fields = cc.getDeclaredFields();

        //遍历每条记录
        while (rs.next()) {
            //实例化对象
            T obj = (T) (cc.newInstance());
            //取出每一个字段进行赋值
            for (int i = 1; i <= colNumber; i++) {
                Object value = rs.getObject(i);
                //匹配实体类中对应的属性
                for (Field f : fields) {
                    if (f.getName().equals(rsm.getColumnName(i))) {
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                    }
                }

            }
            list.add(obj);
        }
        return list;
    }

    /**
     * 将结果集转换成实体对象集合
     *
     * @param dataMap 结果集
     * @param cc      实体对象映射类
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> mapEntity(List<Map<String, Object>> dataMap, Class cc) throws SQLException, InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<T>();
        Field[] fields = cc.getDeclaredFields();

        //遍历每条记录
        for (Map<String, Object> row : dataMap) {
            //实例化对象
            T obj = (T) (cc.newInstance());
            //取出每一个字段进行赋值
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                Object value = entry.getValue();
                //匹配实体类中对应的属性
                for (Field f : fields) {
                    if (f.getName().equals(entry.getKey())) {
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                    }
                }

            }
            list.add(obj);
        }
        return list;
    }
}
