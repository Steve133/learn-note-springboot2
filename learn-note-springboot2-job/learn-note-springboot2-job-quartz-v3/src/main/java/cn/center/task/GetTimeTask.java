package cn.center.task;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component("getTimeTask")
public class GetTimeTask implements TaskService {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void run(String params) {
		log.info("Params === >> " + params);
		log.info("当前时间::::" + format.format(new Date()));
	}
}
