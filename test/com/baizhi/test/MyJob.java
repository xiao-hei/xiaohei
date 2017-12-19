package com.baizhi.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.apache.struts2.components.Set;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		Integer id = (Integer) context.getJobDetail().getJobDataMap().get("id");
		String name = (String) context.getJobDetail().getJobDataMap().get("name");
		System.out.println("id:"+id +" | name:"+name);
		System.out.println("date: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
	}

	
}
