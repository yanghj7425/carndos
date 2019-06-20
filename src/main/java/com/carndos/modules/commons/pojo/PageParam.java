package com.carndos.modules.commons.pojo;

import com.carndos.modules.commons.util.HttpServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class PageParam {


    private Map<String, Object> params;

    private int pageSize;
    private int pageNum;

    private PageParam() {
    }

    public static Builder builder(HttpServletRequest request) {
        if (request == null) {
            return new PageParam().new Builder();
        }
        return new PageParam().new Builder(request);
    }

    public Map<String, Object> getParams() {
        return params;
    }


    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageParam{");
        sb.append("params=").append(params);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", pageNum=").append(pageNum);
        sb.append('}');
        return sb.toString();
    }

    public class Builder {


        private Builder() {
            pageNum = 1;
            pageSize = 7;
        }

        private Builder(HttpServletRequest request) {
            pageSize = HttpServletUtils.getParameter(request, "pageSize", 7);
            pageNum = HttpServletUtils.getParameter(request, "pageNum", 1);
        }

        public Builder addParam(String key, Object value) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put(key, value);
            return this;
        }

        public PageParam build() {
            return PageParam.this;
        }
    }


}
