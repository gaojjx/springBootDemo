package com.example.demo.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParseCreateSqlTest {

    private ParseCreateSql parseCreateSql = new ParseCreateSql();
    @Test
    public void readSql() throws Exception {
    }

    @Test
    public void getColumn() throws Exception {
        parseCreateSql.getColumn("CREATE TABLE `exam_life_style` (\n" +
                "  `id` varchar(32) NOT NULL COMMENT '自增主键',\n" +
                "  `phis_id` varchar(32) DEFAULT '' COMMENT 'phis接口返回id',\n" +
                "  `exam_id` varchar(32) NOT NULL COMMENT '外键：exam_master表ID',\n" +
                "  `exercise_frequency` tinyint(3) unsigned DEFAULT NULL COMMENT '锻炼频率\\r\\n1、每天 2、每周一次以上 3、偶尔 4、不锻炼',\n" +
                "  `each_exercise_time` smallint(5) unsigned DEFAULT NULL COMMENT '每次锻炼时间\\r\\n单位 ：分钟',\n" +
                "  `exercise_time` decimal(3,1) unsigned DEFAULT NULL COMMENT '坚持锻炼时间\\r\\n单位：年',\n" +
                "  `exercise_method` varchar(64) DEFAULT '' COMMENT '锻炼方式',\n" +
                "  `exercise_week_times` tinyint(3) unsigned DEFAULT NULL COMMENT '每周锻炼次数',\n" +
                "  `diet` tinyint(3) unsigned DEFAULT NULL COMMENT '饮食习惯\\r\\n1、荤素均衡 2、荤食为主 3、素食为主 4、嗜盐 5、嗜油 6、嗜糖',\n" +
                "  `smoking_status` tinyint(3) unsigned DEFAULT NULL COMMENT '吸烟状况\\r\\n1、从不吸烟 2、已戒烟 3、吸烟',\n" +
                "  `smoking` tinyint(3) unsigned DEFAULT NULL COMMENT '日吸烟量 单位：根',\n" +
                "  `smoking_age` tinyint(3) unsigned DEFAULT NULL COMMENT '开始吸烟年龄',\n" +
                "  `age_quit` tinyint(3) unsigned DEFAULT NULL COMMENT '戒烟年龄',\n" +
                "  `drinking_frequency` tinyint(3) unsigned DEFAULT NULL COMMENT '饮酒频率\\r\\n1 、从不  2、偶尔 3、经常 4、 每天',\n" +
                "  `daily_alcoholake` decimal(10,2) unsigned DEFAULT NULL COMMENT '日饮酒量 两',\n" +
                "  `has_alcohol` tinyint(3) unsigned DEFAULT NULL COMMENT '是否戒酒\\r\\n1、未戒 2、已戒',\n" +
                "  `alcohol_age` tinyint(3) unsigned DEFAULT NULL COMMENT '戒酒年龄',\n" +
                "  `age_started_drinking` tinyint(3) unsigned DEFAULT NULL COMMENT '开始饮酒年龄',\n" +
                "  `has_drunk_last_year` tinyint(3) unsigned DEFAULT NULL COMMENT '近一年内是否曾醉酒\\r\\n1、是  2、否',\n" +
                "  `alcohol_type` varchar(32) DEFAULT '' COMMENT '饮酒种类\\r\\n1 、白酒 2、 啤酒 3 、红酒 4、 黄酒 5、 其他',\n" +
                "  `has_oe` tinyint(3) unsigned DEFAULT NULL COMMENT '是否职业暴露 1 无 2 有',\n" +
                "  `occupation` varchar(32) DEFAULT '' COMMENT '工种',\n" +
                "  `working_time` tinyint(3) unsigned DEFAULT NULL COMMENT '从业时间(1-100)年',\n" +
                "  `createtime` bigint(20) unsigned NOT NULL COMMENT '创建时间',\n" +
                "  `updatetime` bigint(20) unsigned NOT NULL COMMENT '更新时间',\n" +
                "  `data_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '数据状态 0 未公开，1 正常，2 停用，3已删',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生活方式';\n" +
                "\n");

    }

    @Test
    public void tableSql2Json() throws Exception {
    }

    @Test
    public void parse2MarkdownTable() throws Exception {
    }

}