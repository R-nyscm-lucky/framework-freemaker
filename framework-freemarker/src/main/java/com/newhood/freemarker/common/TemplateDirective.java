package com.newhood.freemarker.common;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * TemplateDirective.
 * </p>
 *
 * @author ZJW
 * @date 2020/10/23 9:47
 */
public abstract class TemplateDirective implements TemplateDirectiveModel {
    protected static String RESULT = "result";
    protected static String RESULTS = "results";

    @Override
    public void execute(Environment env, Map parameters,
                        TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        try {
            execute(new DirectiveHandler(env, parameters, loopVars, body));
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new TemplateException(e, env);
        }
    }

    public abstract void execute(DirectiveHandler handler) throws Exception;

    public abstract String getName();

}

