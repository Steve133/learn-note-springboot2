package cn.center.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cn.center.service.QuartzService;

@Component
public class JobSchedule implements CommandLineRunner {

    @Autowired
    private QuartzService taskService;

    /**
     * @author song 任务调度开始
     * 
     */
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("任务调度开始==============任务调度开始");
        taskService.timingTask();
        System.out.println("任务调度结束==============任务调度结束");
    }
}
