package ru.job4j.chapter1.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class AlertRabbit {

    private final static String PATH = "rabbit.properties";
    private Properties properties;
    private int period;
    private final JobDataMap data = new JobDataMap();

    private void init() {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream(PATH)) {
            properties = new Properties();
            if (in != null) {
                properties.load(in);
            }
            Class.forName(properties.getProperty(Keys.DRIVER));
            period = Integer.parseInt(properties.getProperty(Keys.PERIOD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty(Keys.URL),
                properties.getProperty(Keys.USER),
                properties.getProperty(Keys.PASSWORD)
        )) {
            data.put(Keys.JOB_DATA_MAP_CONTENT, connection);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = getJobDetail();
            scheduler.scheduleJob(job, getTrigger(getSimpleScheduleBuilder()));
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    private Trigger getTrigger(SimpleScheduleBuilder times) {
        return newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
    }

    private SimpleScheduleBuilder getSimpleScheduleBuilder() {
        return simpleSchedule()
                .withIntervalInSeconds(period)
                .repeatForever();
    }

    private JobDetail getJobDetail() {
        return newJob(Rabbit.class)
                .usingJobData(data)
                .build();
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            try {
                Connection connection = (Connection) context
                        .getJobDetail()
                        .getJobDataMap()
                        .get(Keys.JOB_DATA_MAP_CONTENT);
                try (PreparedStatement statement = connection
                        .prepareStatement("insert into rabbit(created) values(?)")) {
                    statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                    statement.execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Keys {
        static final String DRIVER = "driver-class-name";
        static final String URL = "url";
        static final String USER = "username";
        static final String PASSWORD = "password";
        final static String PERIOD = "rabbit.interval";
        final static String JOB_DATA_MAP_CONTENT = "connection";
    }

    public static void main(String[] args) {
        new AlertRabbit().run();
    }
}
