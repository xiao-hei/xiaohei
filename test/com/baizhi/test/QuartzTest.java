package com.baizhi.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	
	public static void main(String[] args) {
		try {
			StdSchedulerFactory factory = new StdSchedulerFactory();
			//获取调度器
			Scheduler scheduler = factory.getScheduler();
			//创建任务详情 指定身份信息 构建方法
			JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
					.withIdentity("myJob", "group1")
					.usingJobData("id", 1)
					.usingJobData("name","xxx").build();
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2011-3-12 16:2:4");
			// 基于cron表达式的触发器   每天每小时10-35分钟25秒开始 10秒
			/*Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(CronScheduleBuilder.cronSchedule("25/10 1-35 * ? * * 2020"))
					.withIdentity("myTrigger").startNow().build();*/
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
									.withIntervalInSeconds(3).repeatForever())  // 每隔三秒调用(重复执行)
					.withIdentity("myTrigger").startNow().build();
			// 注册到调度器中
			scheduler.scheduleJob(jobDetail, trigger);

			// 开启调度
			scheduler.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
}
