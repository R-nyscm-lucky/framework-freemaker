package com.newhood.freemarker.common;

import freemarker.template.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Freemarker 模型工具类.
 * </p>
 *
 * @author ZJW
 * @date 2020/10/23 9:48
 */
public class TemplateModelUtils {

    public static final DateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final int FULL_DATE_LENGTH = 19;

    public static final DateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final int SHORT_DATE_LENGTH = 10;

    /**
     * <p>
     * TODO.
     * </p>
     *
     * @param model TemplateModel
     * @return java.lang.String
     * @author ZJW
     * @date 2020-10-23 9:50
     **/
    public static String converString(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateScalarModel) {
                return ((TemplateScalarModel) model).getAsString();
            } else if ((model instanceof TemplateNumberModel)) {
                return ((TemplateNumberModel) model).getAsNumber().toString();
            }
        }
        return null;
    }

    /**
     * <p>
     * TODO.
     * </p>
     *
     * @param model TemplateModel
     * @return freemarker.template.TemplateHashModel
     * @author ZJW
     * @date 2020-10-23 9:50
     **/
    public static TemplateHashModel converMap(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateHashModelEx) {
                return (TemplateHashModelEx) model;
            } else if (model instanceof TemplateHashModel) {
                return (TemplateHashModel) model;
            }
        }
        return null;
    }

    /**
     * <p>
     * TODO.
     * </p>
     *
     * @param model TemplateModel
     * @return java.lang.Integer
     * @author ZJW
     * @date 2020-10-23 9:50
     **/
    public static Integer converInteger(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateNumberModel) {
                return ((TemplateNumberModel) model).getAsNumber().intValue();
            } else if (model instanceof TemplateScalarModel) {
                String s = ((TemplateScalarModel) model).getAsString();
                if (StringUtils.isNotBlank(s)) {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * <p>
     *    TODO.
     * </p>
     * @author ZJW
     * @date 2020-10-23 9:54
     * @param model TemplateModel
     * @return java.lang.Short
     **/
    public static Short converShort(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateNumberModel) {
                return ((TemplateNumberModel) model).getAsNumber().shortValue();
            } else if (model instanceof TemplateScalarModel) {
                String s = ((TemplateScalarModel) model).getAsString();
                if (StringUtils.isNotBlank(s)) {
                    try {
                        return Short.parseShort(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * <p>
     *    TODO.
     * </p>
     * @author ZJW
     * @date 2020-10-23 9:55
     * @param model TemplateModel
     * @return java.lang.Long
     **/
    public static Long converLong(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateNumberModel) {
                return ((TemplateNumberModel) model).getAsNumber().longValue();
            } else if (model instanceof TemplateScalarModel) {
                String s = ((TemplateScalarModel) model).getAsString();
                if (StringUtils.isNotBlank(s)) {
                    try {
                        return Long.parseLong(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * <p>
     *    TODO.
     * </p>
     * @author ZJW
     * @date 2020-10-23 9:55
     * @param model TemplateModel
     * @return java.lang.Double
     **/
    public static Double converDouble(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateNumberModel) {
                return ((TemplateNumberModel) model).getAsNumber().doubleValue();
            } else if (model instanceof TemplateScalarModel) {
                String s = ((TemplateScalarModel) model).getAsString();
                if (StringUtils.isNotBlank(s)) {
                    try {
                        return Double.parseDouble(s);
                    } catch (NumberFormatException ignored) {
                        ignored.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * <p>
     *    TODO.
     * </p>
     * @author ZJW
     * @date 2020-10-23 9:55
     * @param model TemplateModel
     * @return java.lang.String[]
     **/
    public static String[] converStringArray(TemplateModel model) throws TemplateModelException {
        if (model instanceof TemplateSequenceModel) {
            TemplateSequenceModel smodel = (TemplateSequenceModel) model;
            String[] values = new String[smodel.size()];
            for (int i = 0; i < smodel.size(); i++) {
                values[i] = converString(smodel.get(i));
            }
            return values;
        } else {
            String str = converString(model);
            if (StringUtils.isNotBlank(str)) {
                return StringUtils.split(str, ',');
            }
        }
        return null;
    }

    /**
     * <p>
     *    TODO.
     * </p>
     * @author ZJW
     * @date 2020-10-23 9:56
     * @param model TemplateModel
     * @return java.lang.Boolean
     **/
    public static Boolean converBoolean(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateBooleanModel) {
                return ((TemplateBooleanModel) model).getAsBoolean();
            } else if (model instanceof TemplateNumberModel) {
                return !(0 == ((TemplateNumberModel) model).getAsNumber().intValue());
            } else if (model instanceof TemplateScalarModel) {
                String temp = ((TemplateScalarModel) model).getAsString();
                if (StringUtils.isNotBlank(temp)) {
                    return Boolean.valueOf(temp);
                }
            }
        }
        return null;
    }

    /**
     * <p>
     *    TODO.
     * </p>
     * @author ZJW
     * @date 2020-10-23 9:56
     * @param model TemplateModel
     * @return java.util.Date
     **/
    public static Date converDate(TemplateModel model) throws TemplateModelException {
        if (null != model) {
            if (model instanceof TemplateDateModel) {
                return ((TemplateDateModel) model).getAsDate();
            } else if (model instanceof TemplateScalarModel) {
                String temp = StringUtils.trimToEmpty(((TemplateScalarModel) model).getAsString());
                return parseDate(temp);
            }
        }
        return null;
    }

    /**
     * <p>
     *    TODO.
     * </p>
     * @author ZJW
     * @param date String
     * @date 2020-10-23 9:56
     * @return java.util.Date
     **/
    public static Date parseDate(String date) {

        Date ret = null;
        try {
            if (FULL_DATE_LENGTH == date.length()) {
                ret = FULL_DATE_FORMAT.parse(date);
            } else if (SHORT_DATE_LENGTH == date.length()) {
                ret = SHORT_DATE_FORMAT.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }
}