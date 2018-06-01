package com.huiju.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;

import com.huiju.module.util.StringUtils;

/**
 * 工具类
 * 
 * @author：yuhb
 * @date：2017年2月9日 下午10:35:21
 */
public class NeuUtils {

    public static Calendar parseCalendar(String dateStr) {
        return parseCalendar(dateStr, "yyyy-MM-dd");
    }

    /**
     * 转换为日期格式
     * 
     * @param dateStr
     *            日期值
     * @param format
     *            日期格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     * 
     * @author：yuhb
     * @date：2017年1月5日 上午11:16:01
     */
    public static Calendar parseCalendar(String dateStr, String format) {
        Calendar cl = Calendar.getInstance();
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            cl.setTime(df.parse(dateStr));
        } catch (Exception e) {
            cl = null;
        }
        return cl;
    }

    public static String parseStringFromCalendar(Calendar cl) {
        return parseStringFromCalendar(cl, "yyyy-MM-dd");
    }

    /**
     * 把Calendar类型的日期值转换为字符串
     * 
     * @param cl
     *            Calendar类型的日期值
     * @param format
     *            日期格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     * 
     * @author：yuhb
     * @date：2017年1月5日 上午11:16:01
     */
    public static String parseStringFromCalendar(Calendar cl, String format) {
        String retStr = "";
        if (cl != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat(format);
                retStr = df.format(cl.getTime());
            } catch (Exception e) {
                // TODO
            }
        }
        return retStr;
    }

    public static String parseString(Object obj) {
        return parseString(obj, "");
    }

    /**
     * 把对象转换为字符串
     * 
     * @param obj
     * @return
     * 
     * @author：yuhb
     * @date：2017年1月4日 上午9:57:58
     */
    public static String parseString(Object obj, String defStr) {
        return obj == null ? defStr : obj.toString();
    }

    // ///////////////////////////////////////////读取application.properties到内存///////////////////////////////////////////////
    public static Properties props = new Properties();

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * action中转json避免相互依赖需要使用的属性
     * 
     * @param includes
     *            需要使用的json属性（节点全路径）
     * @param attr
     *            实体类关联的类的外键属性数组
     * @return
     * 
     * @author：yuhb
     * @date：2017年1月10日 上午11:49:39
     */
    public static String[] getActionJsonExcludes(String[] includes, String[]... attrs) {
        List<String> excludes = new ArrayList<String>();
        for (String[] strArr : attrs) {
            for (String str : strArr) {
                boolean flag = false;
                for (String str2 : includes) {
                    if (str.equals(str2)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    excludes.add(str);
                }
            }
        }
        String[] rsArr = new String[excludes.size()];
        return excludes.toArray(rsArr);
    }

    // ///////////////////////////////////////////action中转换实体类的排序///////////////////////////////////////////////

    /**
     * action中转换实体类的排序：字典表汉字属性需要以“Name”结尾
     * 
     * @param sort
     * @return
     * 
     * @author：yuhb
     * @date：2017年2月9日 下午11:30:35
     */
    public static String chgQrySort(String sort) {
        return chgQrySort(sort, null);
    }

    /**
     * action中转换实体类的排序：字典表汉字属性需要以“Name”结尾，表字段中“非字典表、以Name结尾”的属性不需要转换
     * 
     * @param sort
     * @param excludeStr
     * @return
     * 
     * @author：yuhb
     * @date：2017年2月9日 下午11:15:29
     */
    public static String chgQrySort(String sort, String excludeStr) {
        String retSort = sort;
        if (StringUtils.isNotBlank(sort)) {
            boolean needChgFlag = true;
            if (StringUtils.isNotBlank(excludeStr)) {
                String[] excludes = excludeStr.replaceAll(" ", "").split(",");
                for (String str : excludes) {
                    if (sort.equals(str)) {
                        needChgFlag = false;
                        break;
                    }
                }
            }
            if (needChgFlag) {
                retSort = sort.replaceAll("Name", "");
            }
        }
        return retSort;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static DecimalFormat df = new DecimalFormat("#.##");

    /**
     * 格式化数字为字符串：excel导出避免科学计数法
     */
    public static String formatMath(Object d) {
        if (d != null) {
            return df.format(d);
        } else {
            return null;
        }
    }

    /**
     * 格式化对象为double
     */
    public static Double formatDouble(Object d) {
        if (d != null) {
            return Double.parseDouble(df.format(d));
        } else {
            return null;
        }
    }

    /////////////////////////////////////////////// 读取excel转换单元格数据 //////////////////////////////////////////////

    /**
     * cell格式化：Integer
     */
    public static Integer cellFormatInteger(Cell cell) {
        if (cell == null) {
            return null;
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return (int) Math.round(Double.parseDouble(cell.getStringCellValue()));
        } else {
            return (int) Math.round(cell.getNumericCellValue());
        }
    }

    /**
     * cell格式化：Long
     */
    public static Long cellFormatLong(Cell cell) {
        if (cell == null) {
            return null;
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return (long) Math.round(Double.parseDouble(cell.getStringCellValue()));
        } else {
            return Math.round(cell.getNumericCellValue());
        }
    }

    /**
     * cell格式化：Double
     */
    public static Double cellFormatDouble(Cell cell) {
        if (cell == null) {
            return null;
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return Double.parseDouble(df.format(Double.parseDouble(cell.getStringCellValue())));
        } else {
            double cellValue = cell.getNumericCellValue();
            if (cellValue == 0) {
                return null;
            } else {
                return Double.parseDouble(df.format(cellValue));
            }
        }
    }

    /**
     * cell格式化：String
     */
    public static String cellFormatString(Cell cell) {
        if (cell == null) {
            return null;
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(Math.round(cell.getNumericCellValue()));
        } else {
            return cell.getStringCellValue();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取e.printStack详细信息
     * 
     * @author：yuhb
     * @date：2017年2月8日 上午11:15:10
     */
    public static String getStackTraceStr(Exception e) {
        String retStr = null;
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    retStr = sw.toString().replaceAll("\r\n", "<br/>").replaceAll("\t", "    ");
                    sw.close();
                    sw = null;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return retStr;
    }

}