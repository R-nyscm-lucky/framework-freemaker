package com.newhood.freemarker.service.impl;

import com.newhood.freemarker.constants.FreemarkerContants;
import com.newhood.freemarker.service.IFreemarkerService;
import com.newhood.freemarker.common.TimeAgoMethod;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * freemarker 解析工具类.
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
        return parse(configuration() ,templateContent,model );
    }

    @Override
    public String parse(Configuration configuration, String templateContent, Map<String, Object> model) {
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
     * <p>
     *     配置 freemarker configuration.
     *     默认不调用数字分割，采取数字分割样式替换.
     * </p>
     */
    @Override
    public Configuration configuration() {
        return configuration(false);
    }

    /**
     * <p>
     *     配置 freemarker configuration：数字格式是否为：1222 -> 1,222.
     * </p>
     */
    @Override
    public Configuration configuration(Boolean isDigitalSegmentation) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
        // 设置空值处理方式
        configuration.setClassicCompatible(true);
        // 设置时间格式问题
        configuration.setSharedVariable("DateFormat", timeAgoMethod);
        // 设置数字格式
        if(isDigitalSegmentation){
            configuration.setNumberFormat("#");
        }
        return configuration;
    }

}
