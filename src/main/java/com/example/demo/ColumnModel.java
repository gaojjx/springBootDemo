package com.example.demo;

public class ColumnModel {
    private String columnName;
    private String columnType;
    private String comment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ColumnModel{" +
                "columnName='" + columnName + '\'' +
                ", columnType=" + columnType +
                ", comment='" + comment + '\'' +
                '}';
    }
}
