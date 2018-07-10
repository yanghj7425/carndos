package com.yhj.config.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }


    /**
     * @return
     * @Description: 将DispatcherServlet映射到 “/”
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
