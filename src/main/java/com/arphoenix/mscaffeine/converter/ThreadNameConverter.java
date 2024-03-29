package com.arphoenix.mscaffeine.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class ThreadNameConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		return "[" + event.getThreadName() + "]";
	}
}