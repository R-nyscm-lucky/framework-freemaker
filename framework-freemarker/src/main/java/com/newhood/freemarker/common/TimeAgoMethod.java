package com.newhood.freemarker.common;

import freemarker.ext.beans.StringModel;
import freemarker.template.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自定义时间处理标签.
 * </p>
 *
 * @author ZJW
 * @date 2020/10/23 10:01
 */
@Component
public class TimeAgoMethod implements TemplateMethodModelEx {

    // 默认解析方式
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * <p>
     * 将时间戳解析成时间格式.
     * </p>
     *
     * @param arguments 单个时间解析格式
     * @return java.lang.Object
     * @author ZJW
     * @date 2020-10-23 10:32
     **/
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments == null || arguments.size() == 0) {
            return null;
        }
        if (arguments.size() > 1) {
            throw new TemplateModelException("DateFormat 只能解析单个时间戳");
        }
        try {
            long timestamp;
            if (arguments.get(0) instanceof StringModel) {
                StringModel stringModel = (StringModel) arguments.get(0);
                return stringModel.getAsString();
            } else if (arguments.get(0) instanceof SimpleNumber) {
                SimpleNumber simpleNumber = (SimpleNumber) arguments.get(0);
                timestamp = simpleNumber.getAsNumber().longValue();
                // localDateTime 存在“T”
                return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
            } else if (arguments.get(0) instanceof SimpleDate) {
                SimpleDate simpleDate = (SimpleDate) arguments.get(0);
                Date parse = simpleDate.getAsDate();
                return simpleDateFormat.format(parse);
            } else {
                throw new TemplateModelException("DateFormat 只能解析时间戳格式");
            }
        } catch (Exception e) {
            throw new TemplateModelException("DateFormat 解析失败");
        }
    }

}
