package com.george.jdbc.databaseDir.entity;

/**
 * @author : George
 *         Description : 表实体
 *         Date : Created in 15:24 2018/3/8
 *         Modified By :
 */
public class TableEntity {
    private int databaseType; //数据库类型
    private Short serialNum; //序号
    private String columnName;
    private String columnRemark;
    private String dataTyle;
    private Short dataLength;
    private Integer decimalLength;//小数位数
    private boolean flag;//标识
    private boolean keyFlag;//主键
    private boolean allowNull;//允许空
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

    public String getDataTyle() {
        return dataTyle;
    }

    public void setDataTyle(String dataTyle) {
        this.dataTyle = dataTyle;
    }

    public Integer getDecimalLength() {
        return decimalLength;
    }

    public void setDecimalLength(Integer decimalLength) {
        this.decimalLength = decimalLength;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
