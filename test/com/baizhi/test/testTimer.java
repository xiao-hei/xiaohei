package com.baizhi.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class testTimer {

	public static void main(String[] args) {
		try {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				public void run() {
					System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				}
			},new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-07-28 14:14:15"),2000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
