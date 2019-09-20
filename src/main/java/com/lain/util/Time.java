package com.lain.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	public static String getTimeymd() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String testDate=df.format(new Date());//格式化当前日期
        return testDate;
    }
	public static void main(String[] args) {
    Time time = new Time();
    System.out.println(time.getTimeymd());
	}
}
