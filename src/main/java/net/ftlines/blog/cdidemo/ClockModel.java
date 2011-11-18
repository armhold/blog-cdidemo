package net.ftlines.blog.cdidemo;

import javax.inject.Inject;

import net.ftlines.wicket.cdi.CdiContainer;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class ClockModel extends AbstractReadOnlyModel<String> {
	@Inject
	Clock clock;

	public ClockModel() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}

	@Override
	public String getObject() {
		return clock.getTime();
	}
}
