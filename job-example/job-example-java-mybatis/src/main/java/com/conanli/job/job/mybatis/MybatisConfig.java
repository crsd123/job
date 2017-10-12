package com.conanli.job.job.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class MybatisConfig {

    private SqlSessionFactory sqlSessionFactory;

    public MybatisConfig() {
        // 数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setPoolPingEnabled(true);
        dataSource.setPoolPingConnectionsNotUsedFor(60000);
        dataSource.setPoolPingQuery("select 1");

        // 类加载器
        Resources.setDefaultClassLoader(this.getClass().getClassLoader());

        // 事务管理器
        TransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();

        // SessionFactory
        Configuration configuration = new Configuration(new Environment("development", jdbcTransactionFactory, dataSource));
        configuration.addMapper(SqlMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    public SqlMapper getSqlMapper() {
        return sqlSessionFactory.openSession(true).getMapper(SqlMapper.class);
    }
}
