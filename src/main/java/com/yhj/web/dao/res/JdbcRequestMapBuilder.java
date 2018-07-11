package com.yhj.web.dao.res;

import com.yhj.web.entity.res.ResRole;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author YHJ
 * @description 查询资源和角色，并构建RequestMap
 */
public class JdbcRequestMapBuilder extends JdbcDaoSupport {

    private String resourceQuerySQL;


    public List<ResRole> queryResourceData() {
        ResourceMapping resourceMapping = new ResourceMapping(getDataSource(), resourceQuerySQL);

        return resourceMapping.execute();
    }


    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

        List<ResRole> resourceList = queryResourceData();
        for (ResRole resRole : resourceList) {
            RequestMatcher requestMatcher = getRequestMatcher(resRole.getResUrl());
            List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
            list.add(new SecurityConfig(resRole.getResRole()));
            requestMap.put(requestMatcher, list);
        }
        return requestMap;
    }


    private RequestMatcher getRequestMatcher(String resUrl) {
        return new AntPathRequestMatcher(resUrl);
    }


    public String getResourceQuerySQL() {
        return resourceQuerySQL;
    }

    public void setResourceQuerySQL(String resourceQuerySQL) {
        this.resourceQuerySQL = resourceQuerySQL;
    }

    protected class ResourceMapping extends MappingSqlQuery {

        public ResourceMapping(DataSource dataSource, String resourceQuery) {
            super(dataSource, resourceQuery);
            compile();
        }

        /**
         * @param rs     查询到的资源信息
         * @param rowNum
         *
         * @return
         *
         * @throws SQLException
         */
        protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {

            String resUrl = rs.getString(1);

            String resRole = rs.getString(2);

            ResRole resRole1 = new ResRole(resUrl, resRole);

            return resRole1;
        }
    }

}

