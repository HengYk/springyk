package cn.edu.xidian.ictt.springyk.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare",
        args = {Connection.class, Integer.class}))
public class MyPageInterceptor implements Interceptor {

    private int currPage;

    private int pageSize;

    private String dbType;

    /**
     * 拦截过程
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaObjectHandler = SystemMetaObject.forObject(statementHandler);

        while (metaObjectHandler.hasGetter("h")) {
            Object obj = metaObjectHandler.getValue("h");
            metaObjectHandler = SystemMetaObject.forObject(obj);
        }

        while (metaObjectHandler.hasGetter("target")) {
            Object obj = metaObjectHandler.getValue("target");
            metaObjectHandler = SystemMetaObject.forObject(obj);
        }

        MappedStatement mappedStatement =
                (MappedStatement) metaObjectHandler.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();

        if (mapId.matches(".+ByPage$")) {
            ParameterHandler parameterHandler =
                    (ParameterHandler) metaObjectHandler.getValue("delegate.parameterHandler");

            Map<String, Object> paraObj =
                    (Map<String, Object>) parameterHandler.getParameterObject();

            currPage = (int) paraObj.get("currPage");
            pageSize = (int) paraObj.get("pageSize");

            String sql = (String) metaObjectHandler.getValue("delegate.boundSql.sql");

            String limitSql;
            sql = sql.trim();
            limitSql = sql + " limit " + (currPage - 1) * pageSize + ", " + pageSize;

            metaObjectHandler.setValue("delegate.boundSql.sql", limitSql);
        }

        return invocation.proceed();
    }

    /**
     * 获取代理对象
     *
     * @param o
     * @return
     */
    @Override
    public Object plugin(Object o) {

        return Plugin.wrap(o, this);
    }

    /**
     * 设置代理对象的参数
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

        String limitStr = properties.getProperty("limit", "10");
        this.pageSize = Integer.valueOf(limitStr);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
