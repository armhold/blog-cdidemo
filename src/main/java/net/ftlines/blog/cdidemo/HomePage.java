package net.ftlines.blog.cdidemo;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {
	@Inject
	Clock clock;

	public HomePage() {
		add(new Label("clock", new PropertyModel<String>(this, "clock.time")));
	}
}
