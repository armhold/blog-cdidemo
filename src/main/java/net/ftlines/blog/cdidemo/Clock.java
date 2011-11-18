package net.ftlines.blog.cdidemo;

import javax.enterprise.context.ApplicationScoped;

import org.apache.wicket.util.time.Time;

@ApplicationScoped
public class Clock {
	public String getTime() {
		return Time.now().toString("hh:MM:ss");
	}
}
