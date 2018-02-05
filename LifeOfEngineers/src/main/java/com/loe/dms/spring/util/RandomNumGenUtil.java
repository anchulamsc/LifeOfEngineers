package com.loe.dms.spring.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomNumGenUtil {
	private static final Logger logger = LoggerFactory.getLogger(RandomNumGenUtil.class);

	private static Random rand = new Random();
	
	public static String generate2DigitRandomNum() {
		long drand = (long) (rand.nextDouble() * 100L);
		logger.info("Generated 2DigitRandomNum Value : " + drand);
		return String.valueOf(drand);
	}
	
	public static String generate4DigitRandomNum() {
		long drand = (long) (rand.nextDouble() * 10000L);
		logger.info("Generated 4DigitRandomNum Value : " + drand);
		return String.valueOf(drand);
	}

	public static String generate6DigitRandomNum() {
		long drand = (long) (rand.nextDouble() * 1000000L);
		logger.info("Generated 6DigitRandomNum Value : " + drand);
		return String.valueOf(drand);
	}

	public static String generate8DigitRandomNum() {
		long drand = (long) (rand.nextDouble() * 100000000L);
		logger.info("Generated 8DigitRandomNum Value : " + drand);
		return String.valueOf(drand);
	}

	public static String generate10DigitRandomNum() {
		long drand = (long) (rand.nextDouble() * 10000000000L);
		logger.info("Generated 10DigitRandomNum Value : " + drand);
		return String.valueOf(drand);
	}

}
