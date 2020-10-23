package com.newhood.freemarker.service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * IFreemarkerService.
 * </p>
 *
 * @author ZJW
 * @date 2020/10/22 10:28
 */
public interface IFreemarkerService {

    /**
     * <p>
     * string解析.
     * </p>
     *
     * @param templateContent model
     * @return java.lang.String
     * @author ZJW
     * @date 2020-10-22 10:30
     **/
    String parse(String templateContent, Map<String, Object> model);

    /**
     * <p>
     * 自定义解析String工具.
     * </p>
     *
     * @param configuration templateName, templateValue, model
     * @return java.lang.String
     * @author ZJW
     * @date 2020-10-22 10:46
     **/
    String processTemplate(Configuration configuration, String templateName,
                           String templateValue, Map<String, Object> model
    ) throws IOException, TemplateException;

    /**
     * <p>
     * 配置 freemarker configuration.
     * </p>
     *
     * @return freemarker.template.Configuration
     * @author ZJW
     * @date 2020-10-22 10:48
     **/
    Configuration configuration();
}
