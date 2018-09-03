package com.junhao.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class LogBackTest {
	public static final Logger logger = LoggerFactory.getLogger(LogBackTest.class);
	
	@Test
	public void loggerContextTest() {
		logger.debug("Hello world");
		
		// 打印Logback内部状态
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
	    StatusPrinter.print(loggerContext);
	}
	
	@Test
	public void logLevelTest() {
	    ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
	    logger.setLevel(Level.INFO);
	   
	    logger.debug("can not be printed bacause DEBUG < INFO");
	    logger.warn("can be printed bacause WARN > INFO");
	}
	
	@Test
	public void xmlLogBackTest() {
		logger.trace("logback的--trace日志--输出了");
		logger.debug("logback的--debug日志--输出了");
		logger.info("logback的--info日志--输出了");
		logger.warn("logback的--warn日志--输出了");
		logger.error("logback的--error日志--输出了");
	}
}
