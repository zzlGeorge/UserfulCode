package com.george.jdbc.databaseDir.entity;

/**
 * @author : George
 *         Description : 表实体
 *         Date : Created in 15:24 2018/3/8
 *         Modified By :
 */
public class SqlServerTableEntity {
    private int databaseType; //数据库类型
    private Short serialNum; //序号
    private String columnName;
    private String columnRemark;
    private String dataType;
    private Short dataLength;
    private Integer decimalLength;//小数位数
    private String flag;//标识
    private String keyFlag;//主键
    private String allowNull;//允许空
    private String defaultValue;//默认值

    public int getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(int databaseType) {
        this.databaseType = databaseType;
    }

    public Short getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Short serialNum) {
        this.serialNum = serialNum;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnRemark() {
        return columnRemark;
    }

    public Short getDataLength() {
        return dataLength;
    }

    public void setDataLength(Short dataLength) {
        this.dataLength = dataLength;
    }

    public void setColumnRemark(String columnRemark) {
        this.columnRemark = columnRemark;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getDecimalLength() {
        return decimalLength;
    }

    public void setDecimalLength(Integer decimalLength) {
        this.decimalLength = decimalLength;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(String keyFlag) {
        this.keyFlag = keyFlag;
    }

    public String getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(String allowNull) {
        this.allowNull = allowNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
