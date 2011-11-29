package net.ftlines.blog.cdidemo.web.app;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.EmployeeCriteria;
import net.ftlines.blog.cdidemo.web.ConversationModel;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;

public class HomePage extends WebPage {

	@Inject
	Conversation conversation;

	public HomePage() {
		
		conversation.begin();
		
		IModel<EmployeeCriteria> criteria = new ConversationModel<EmployeeCriteria>(new EmployeeCriteria());

		add(new EmployeeCriteriaPanel("criteria", criteria));
		add(new EmployeeListPanel("list", criteria));
	}
}
