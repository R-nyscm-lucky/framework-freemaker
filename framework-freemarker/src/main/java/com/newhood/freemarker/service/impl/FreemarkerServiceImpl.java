package com.newhood.freemarker.service.impl;

import com.newhood.freemarker.constants.FreemarkerContants;
import com.newhood.freemarker.service.IFreemarkerService;
import com.newhood.freemarker.utils.TimeAgoMethod;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * FreemarkerServiceImpl.
 * </p>
 *
 * @author ZJW
 * @date 2020/10/22 10:31
 */
@Component
public class FreemarkerServiceImpl implements IFreemarkerService {

    @Resource
    private TimeAgoMethod timeAgoMethod;

    @Override
    public String parse(String templateContent, Map<String, Object> model) {
        Configuration configuration = configuration();
        try {
            return processTemplate(configuration, FreemarkerContants.defaultTemplateName,
                    templateContent, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String processTemplate(
            Configuration configuration, String templateName,
            String templateValue, Map<String, Object> model
    ) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Template template = new Template(templateName, templateValue, configuration);
        template.process(model, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 配置 freemarker configuration.
     */
    @Override
    public Configuration configuration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
        // 设置空值处理方式
        configuration.setClassicCompatible(true);
        // 设置时间格式问题
        configuration.setSharedVariable("DateFormat", timeAgoMethod);
        return configuration;
    }

}
