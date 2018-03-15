package com.healthcloud.utils.jdbc.databaseDir;

/**
 * @author : George
 *         Description : 查询各数据库数据字典sql语句
 *         Date : Created in 15:14 2018/3/8
 *         Modified By :
 */
public class TypeOfSqlString {
    public static final int TYPE_OF_MYSQL = 1;
    public static final int TYPE_OF_SQLSERVER = 2;
    public static final int TYPE_OF_ORACLE = 3;

    public static String getMySqlString(String databaseName, String tableName) {
        return "SELECT\n" +
                "\tCOLUMN_NAME 列名,\n" +
                "\tCOLUMN_TYPE 数据类型,\n" +
                "\tDATA_TYPE 字段类型,\n" +
                "\tCHARACTER_MAXIMUM_LENGTH 长度,\n" +
                "\tIS_NULLABLE 是否为空,\n" +
                "\tCOLUMN_DEFAULT 默认值,\n" +
                "\tCOLUMN_COMMENT 备注\n" +
                "FROM\n" +
                "\tINFORMATION_SCHEMA. COLUMNS\n" +
                "WHERE\n" +
                "\ttable_schema = '" + databaseName + "'\n" +
                "AND table_name = '" + tableName + "'";
    }

    public static String getSqlServerString(String tableName) {
        return "SELECT\n" +
                "\tCASE\n" +
                "WHEN col.colorder = 1 THEN\n" +
                "\tobj.name\n" +
                "ELSE\n" +
                "\t''\n" +
                "END AS 表名,\n" +
                " col.colorder AS 序号,\n" +
                " col.name AS 列名,\n" +
                " CAST (\n" +
                "\tISNULL(ep.[value], '') AS VARCHAR (500)\n" +
                ") AS 列说明,\n" +
                " t.name AS 数据类型,\n" +
                " col.length AS 长度,\n" +
                " ISNULL(\n" +
                "\tCOLUMNPROPERTY(col.id, col.name, 'Scale'),\n" +
                "\t0\n" +
                ") AS 小数位数,\n" +
                " CASE\n" +
                "WHEN COLUMNPROPERTY(\n" +
                "\tcol.id,\n" +
                "\tcol.name,\n" +
                "\t'IsIdentity'\n" +
                ") = 1 THEN\n" +
                "\t'√'\n" +
                "ELSE\n" +
                "\t''\n" +
                "END AS 标识,\n" +
                " CASE\n" +
                "WHEN EXISTS (\n" +
                "\tSELECT\n" +
                "\t\t1\n" +
                "\tFROM\n" +
                "\t\tdbo.sysindexes si\n" +
                "\tINNER JOIN dbo.sysindexkeys sik ON si.id = sik.id\n" +
                "\tAND si.indid = sik.indid\n" +
                "\tINNER JOIN dbo.syscolumns sc ON sc.id = sik.id\n" +
                "\tAND sc.colid = sik.colid\n" +
                "\tINNER JOIN dbo.sysobjects so ON so.name = si.name\n" +
                "\tAND so.xtype = 'PK'\n" +
                "\tWHERE\n" +
                "\t\tsc.id = col.id\n" +
                "\tAND sc.colid = col.colid\n" +
                ") THEN\n" +
                "\t'√'\n" +
                "ELSE\n" +
                "\t''\n" +
                "END AS 主键,\n" +
                " CASE\n" +
                "WHEN col.isnullable = 1 THEN\n" +
                "\t'√'\n" +
                "ELSE\n" +
                "\t''\n" +
                "END AS 允许空,\n" +
                " ISNULL(comm. TEXT, '') AS 默认值\n" +
                "FROM\n" +
                "\tdbo.syscolumns col\n" +
                "LEFT JOIN dbo.systypes t ON col.xtype = t.xusertype\n" +
                "INNER JOIN dbo.sysobjects obj ON col.id = obj.id\n" +
                "AND obj.xtype = 'U'\n" +
                "AND obj.status >= 0\n" +
                "LEFT JOIN dbo.syscomments comm ON col.cdefault = comm.id\n" +
                "LEFT JOIN sys.extended_properties ep ON col.id = ep.major_id\n" +
                "AND col.colid = ep.minor_id\n" +
                "AND ep.name = 'MS_Description'\n" +
                "LEFT JOIN sys.extended_properties epTwo ON obj.id = epTwo.major_id\n" +
                "AND epTwo.minor_id = 0\n" +
                "AND epTwo.name = 'MS_Description'\n" +
                "WHERE\n" +
                "\tobj.name = '" + tableName + "' --表名\n" +
                "ORDER BY\n" +
                "\tcol.colorder;";
    }

    public static String getOracleString(String databaseName, String tableName) {
        return "";
    }
}
