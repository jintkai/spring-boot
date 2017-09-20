package com.jon.job;

import com.jon.collect.host.CollLinuxHost;
import com.jon.collect.host.CollLinuxHostMBean;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import static org.quartz.TriggerBuilder.newTrigger;

public class HostJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yy-mm-dd HH:MM:SS");
        String now = f.format(date);
        System.out.println("Current Time:" + now);
        CollLinuxHostMBean bean = new CollLinuxHost();
        bean.getBaseInfo();
    }

    public static void main(String... arg) throws SchedulerException {
        System.out.println("Begin Time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        JobDetail jobDetail = JobBuilder.newJob(HostJob.class).withIdentity("HostJob", "Coll").build();
        SimpleTrigger jobTrigger = (SimpleTrigger) newTrigger().withIdentity("HostTrigger", "Coll").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        scheduler = schedulerfactory.getScheduler();
        scheduler.scheduleJob(jobDetail, jobTrigger);
        scheduler.start();

        System.out.println("End Time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));


    }
}
