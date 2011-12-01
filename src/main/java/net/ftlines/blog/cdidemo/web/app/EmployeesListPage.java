package net.ftlines.blog.cdidemo.web.app;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.EmployeeCriteria;
import net.ftlines.blog.cdidemo.web.ConversationModel;

import org.apache.wicket.model.IModel;

public class EmployeesListPage extends BasePage {

  @Inject
  Conversation conversation;

  public EmployeesListPage() {

    conversation.begin();

    IModel<EmployeeCriteria> criteria = new ConversationModel<EmployeeCriteria>(new EmployeeCriteria());

    add(new EmployeeCriteriaPanel("criteria", criteria));
    add(new EmployeesListPanel("list", criteria));
  }
}
