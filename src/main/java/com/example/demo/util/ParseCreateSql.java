package com.example.demo.util;

import com.example.demo.ColumnModel;
import com.example.demo.EntityModel;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCreateSql {

    public static void main(String[] args) throws IOException {
        ParseCreateSql parseCreateSql = new ParseCreateSql();
//        String sqlPath = "/Users/gaojjx/code/springBootDemo/src/main/resources/data.sql";
//        parseCreateSql.readSql(sqlPath);
        String sql = "CREATE TABLE `ejiaren_order` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',\n" +
                "  `weahan_code` varchar(128) NOT NULL COMMENT '系统编码',\n" +
                "  `patient_code` varchar(128) DEFAULT NULL COMMENT '患者编码',\n" +
                "  `patient_name` varchar(64) NOT NULL COMMENT '患者姓名',\n" +
                "  `telphone` varchar(64) NOT NULL COMMENT '电话',\n" +
                "  `card_id` varchar(18) NOT NULL COMMENT '身份证号',\n" +
                "  `sex` tinyint(2) DEFAULT NULL COMMENT '性别(1.男2.女)',\n" +
                "  `birth_day` bigint(20) DEFAULT NULL COMMENT '生日',\n" +
                "  `address` varchar(128) DEFAULT NULL COMMENT '家庭地址',\n" +
                "  `triage_officer_name` varchar(64) DEFAULT NULL COMMENT '分诊员姓名',\n" +
                "  `guider` varchar(64) DEFAULT NULL COMMENT '导诊员姓名',\n" +
                "  `controller` varchar(64) DEFAULT NULL COMMENT '指派人',\n" +
                "  `designate_time` varchar(64) DEFAULT NULL COMMENT '指派时间',\n" +
                "  `guide_time` bigint(20) DEFAULT NULL COMMENT '导诊时间/就诊时间',\n" +
                "  `triage_guide_status` tinyint(4) DEFAULT NULL COMMENT '分诊导诊状态(0.待分诊1.已分诊/待指派2已指派/待导诊3.导诊中/就诊中4.已完成)',\n" +
                "  `main_symptom` varchar(64) NOT NULL COMMENT '主要症状',\n" +
                "  `main_symptom_time` varchar(64) DEFAULT NULL COMMENT '主要症状持续时间',\n" +
                "  `minor_symptom` varchar(64) DEFAULT NULL COMMENT '次要症状',\n" +
                "  `minor_symptom_time` varchar(64) DEFAULT NULL COMMENT '次要症状时间',\n" +
                "  `expect_treat_time` varchar(64) NOT NULL COMMENT '期望就诊时间',\n" +
                "  `free_status` tinyint(2) NOT NULL DEFAULT 2 COMMENT '是否免费(1.是2.否)',\n" +
                "  `order_status` tinyint(2) DEFAULT NULL COMMENT '订单状态(1.未完成 2.已完成)',\n" +
                "  `driver` varchar(64) DEFAULT NULL COMMENT '司机姓名',\n" +
                "  `driver_tel` varchar(18) DEFAULT NULL COMMENT '司机联系电话',\n" +
                "  `aboard_address` varchar(128) DEFAULT NULL COMMENT '上车地址',\n" +
                "  `summary` varchar(512) DEFAULT NULL COMMENT '说明',\n" +
                "  `display_order` bigint(20) NOT NULL COMMENT '排序号',\n" +
                "  `data_status` tinyint(4) NOT NULL,\n" +
                "  `createtime` bigint(20) NOT NULL COMMENT '创建时间',\n" +
                "  `createby` varchar(64) DEFAULT NULL COMMENT '创建人',\n" +
                "  `updatetime` bigint(20) NOT NULL COMMENT '更新时间',\n" +
                "  `updateby` varchar(64) DEFAULT NULL COMMENT '更新人',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';";
        EntityModel model = parseCreateSql.getColumn(sql);
        String json = parseCreateSql.createTableSql2Json(model);
        System.out.println(json);
        String markdown = parseCreateSql.parse2MarkdownTable(model);
        System.out.println(markdown);
    }
    public String readSql(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String str = new String(bytes, "UTF-8");
        String[] tables = str.split("CREATE TABLE ");
        for (String s : tables) {
            System.out.println(s);
        }
        return str;
    }

    public EntityModel getColumn(String string) {
        String[] columns = string.split("\n");
        String ColumnPattern = "^\\s+`(\\w+)`\\s+(\\w+).*COMMENT\\s+'(.*)'";
        EntityModel entityModel = new EntityModel();
        entityModel.setTableName(columns[0]);
        List<ColumnModel> columnList = new ArrayList<>();
        ColumnModel columnModel;
        for (String s : columns) {
            Pattern r = Pattern.compile(ColumnPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = r.matcher(s.trim());
            if (matcher.find()) {
                columnModel = new ColumnModel();
                columnModel.setColumnName(matcher.group(1));
                columnModel.setColumnType(matcher.group(2));
                columnModel.setComment(matcher.group(4));
                columnList.add(columnModel);
            }
        }
        entityModel.setColumnList(columnList);
        return entityModel;
    }

    public String createTableSql2Json(EntityModel model) {
        if (model != null && model.getColumnList() != null && model.getColumnList().size() > 0) {
            StringBuilder sb = new StringBuilder("{\n");
            for (ColumnModel column : model.getColumnList()) {
                sb.append("\t\"" + StringUtil.underlineToCamel(column.getColumnName()) + "\" : \"" + column.getComment() + "\",\n");
            }
            sb.append("}");
            return sb.toString();
        }
        return null;
    }

    public String parse2MarkdownTable(EntityModel model) {
        if (model == null || model.getColumnList() == null || model.getColumnList().size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder("| 字段名 | 类型 | 含义 |\n" +
                "|---|---|---|");
        for (ColumnModel column : model.getColumnList()) {
            sb.append("\n| " + StringUtil.underlineToCamel(column.getColumnName()) + " | " + columnType(column.getColumnType()) + " | " + column.getComment() + " |");
        }
        return sb.toString();
    }

    private String columnType(String columnStringType) {
        if (StringUtils.isEmpty(columnStringType)) {
            return "";
        }
        String type = columnStringType.toLowerCase();
        String columnType = "";
        switch (type) {
            case "varchar":
                columnType = "String";
                break;
            case "int":
                columnType = "Number";
                break;
            case "bigint":
                columnType = "Number";
                break;
            case "tinyint":
                columnType = "Byte";
                break;
            default:
                columnType = "";
                break;
        }
        return columnType;
    }
}
