package com.lain.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	public static String getTimeymd() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        String testDate=df.format(new Date());//��ʽ����ǰ����
        return testDate;
    }
	public static void main(String[] args) {
    Time time = new Time();
    System.out.println(time.getTimeymd());
	}
}
