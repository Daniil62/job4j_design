package ru.job4j.chapter1.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class AlertRabbit {

    private final static String PATH = "rabbit.properties";
    private final static String PERIOD_KEY = "rabbit.interval";
    private int period;

    private void init() {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream(PATH)) {
            Properties properties = new Properties();
            if (in != null) {
                properties.load(in);
            }
            period = Integer.parseInt(properties.getProperty(PERIOD_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(period)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
        }
    }

    public static void main(String[] args) {
        new AlertRabbit().run();
    }
}
