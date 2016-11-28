package com.javacodegeeks.camel;


import org.apache.camel.Converter;
import org.apache.camel.Exchange;

import java.io.UnsupportedEncodingException;

import static org.apache.hadoop.hdfs.web.resources.ExceptionHandler.LOG;

public class TestBean {
	public String hello(String msg) {
		return msg + ":" + Thread.currentThread();
	}
	@Converter
	public static String toString(byte[] data, Exchange exchange) {
		if (exchange != null) {
			String charsetName = exchange.getProperty(Exchange.CHARSET_NAME, String.class);
			if (charsetName != null) {
				try {
					return new String(data, charsetName);
				} catch (UnsupportedEncodingException e) {
					LOG.warn("Cannot convert the byte to String with the charset " + charsetName, e);
				}
			}
		}
		return new String(data);
	}
}
