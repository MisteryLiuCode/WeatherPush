package com.liu.weathermail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataMigrationToolTest {

    private static final String SOURCE_DB_URL = "jdbc:mysql://source_host:3306/source_db";
    private static final String TARGET_DB_URL = "jdbc:mysql://target_host:3306/target_db";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    private static final int BATCH_SIZE = 1000;  // 每次迁移的批次大小

    public static void main(String[] args) {
        migrateData();
    }

    public static void migrateData() {
        Connection sourceConnection = null;
        Connection targetConnection = null;
        PreparedStatement sourceStmt = null;
        PreparedStatement targetStmt = null;
        ResultSet resultSet = null;

        try {
            // 建立源数据库连接
            sourceConnection = DriverManager.getConnection(SOURCE_DB_URL, USER, PASSWORD);
            // 建立目标数据库连接
            targetConnection = DriverManager.getConnection(TARGET_DB_URL, USER, PASSWORD);

            // 查询源数据库中的数据
            String sourceQuery = "SELECT * FROM source_table";  // 根据实际表名修改
            sourceStmt = sourceConnection.prepareStatement(sourceQuery, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sourceStmt.setFetchSize(BATCH_SIZE);  // 设置每次批量读取的大小
            resultSet = sourceStmt.executeQuery();

            // 插入数据到目标数据库
            String targetInsert = "INSERT INTO target_table (column1, column2, ...) VALUES (?, ?, ...)";  // 根据实际表和列修改
            targetStmt = targetConnection.prepareStatement(targetInsert);

            int count = 0;
            while (resultSet.next()) {
                // 根据源表的列数，将数据逐列获取并设置到目标插入语句
                targetStmt.setString(1, resultSet.getString("column1"));  // 例如，列 "column1" 需要根据实际修改
                targetStmt.setInt(2, resultSet.getInt("column2"));
                // ...设置其他列

                targetStmt.addBatch();  // 将每一条数据添加到批处理中

                // 每批次插入 BATCH_SIZE 条数据
                if (++count % BATCH_SIZE == 0) {
                    targetStmt.executeBatch();
                }
            }

            // 执行剩余的批次
            if (count % BATCH_SIZE != 0) {
                targetStmt.executeBatch();
            }

            System.out.println("数据迁移完成");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            closeResources(resultSet, sourceStmt, targetStmt, sourceConnection, targetConnection);
        }
    }

    // 关闭数据库资源
    private static void closeResources(ResultSet resultSet, Statement sourceStmt, Statement targetStmt,
                                       Connection sourceConnection, Connection targetConnection) {
        try {
            if (resultSet != null) resultSet.close();
            if (sourceStmt != null) sourceStmt.close();
            if (targetStmt != null) targetStmt.close();
            if (sourceConnection != null) sourceConnection.close();
            if (targetConnection != null) targetConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
