package com.hyf.entryexit.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 获得可预约日期列表, 可提前5个工作日预约，周末不能预约，当天不能预约
 * 如：｛"2015-02-05 星期四","2015-02-06 星期五",..｝
 * @author HuangYongFeng
 */
public class AvailableDateUtil {
	/**
	 * 获得可预约日期列表, 可提前5个工作日预约，周末不能预约，当天不能预约
	 * 如：｛"2015-02-05 星期四","2015-02-06 星期五",..｝
	 * @return String[] 5个预约工作时间
	 */
	public static String[] getAvailableDate(){
		String[] dates = new String[5];
		Calendar cal = new GregorianCalendar();
		//可以预约未来5天，不包括周末,不包括今天
//		cal.add(Calendar.DAY_OF_MONTH, 1);//（DAY_OF_MONTH:一个月中的某天）,就是nowDate+1
		for (int i = 0; i < 5; i++,cal.add(Calendar.DAY_OF_MONTH, 1)) {//每执行for一次，天的加多一天
			StringBuffer str = new StringBuffer();
			//1、获得年
			str.append(cal.get(Calendar.YEAR) + "-");
			//2、获得月  这里0代表现实生活中的一月，11代表十二月，要注意加
			//Calendar.MONTH 当前月
			//设置下月是两位数
			if((Calendar.MONTH + 1) < 10){
				str.append("0" + (cal.get(Calendar.MONTH) + 1) + "-");
			}else{
				str.append(cal.get(Calendar.MONTH) + 1 + "-");
			}
			/* 3、获得日 :星期日是一周开始的第一天 0 星期一是1 .. 星期六是 6 ,不包括周末
			 * Calendar.Day_OF_WEEK   一星期的第几天
			 * Calendar.Day_OF_MONTH  一月的第几天
			 */
			 //如果是星期日，那就设置下日期加一天，变成星期一
			if(cal.get(Calendar.DAY_OF_WEEK) == 0){
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			//如果是星期六，那就设置下日期加两天，变成星期一
			if(cal.get(Calendar.DAY_OF_WEEK)  == 6){
				cal.add(Calendar.DAY_OF_MONTH, 2);
			}
//			System.out.println(cal.get(Calendar.DAY_OF_MONTH));
			//设置下日是两位数,不包括今天，所以+1
			if((cal.get(Calendar.DAY_OF_MONTH) + 1) < 10){
				str.append("0" + (cal.get(Calendar.DAY_OF_MONTH) + 1));
			}else{
				str.append(cal.get(Calendar.DAY_OF_MONTH) + 1);
			}
			//设置星期  :星期日是一周开始的第一天 0 星期一是1 .. 星期六是 6
			String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};  
			str.append(" " + week[cal.get(Calendar.DAY_OF_WEEK) - 1]);
			//设置好年月是日(2015-02-05 星期四)后，放到数组里
//			System.out.println(str);
			dates[i] = str.toString();
		}
		return dates;
	}
}
