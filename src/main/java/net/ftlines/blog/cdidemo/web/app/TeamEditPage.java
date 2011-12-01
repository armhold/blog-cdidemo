package net.ftlines.blog.cdidemo.web.app;

import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.Member;
import net.ftlines.blog.cdidemo.model.PositionsRepository;
import net.ftlines.blog.cdidemo.model.Team;
import net.ftlines.blog.cdidemo.web.EntityModel;
import net.ftlines.blog.cdidemo.web.UserAction;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TeamEditPage extends BasePage {

  @Inject
  UserAction action;

  public TeamEditPage(PageParameters params) {
    action.begin();

    Long teamId = params.get("id").toLong();
    IModel<Team> team = new EntityModel<Team>(Team.class, teamId);
    setDefaultModel(team);

    Form form = new Form("form");
    add(form);

    form.add(new TextField("name", new PropertyModel(team, "name")));

    final ModalWindow modal = new ModalWindow("modal");
    form.add(modal);

    form.add(new ListView<Member>("members", new PropertyModel(team, "members")) {

      @Override
      protected void populateItem(final ListItem<Member> item) {
        item.setOutputMarkupId(true);
        IModel<Member> member = item.getModel();
        item.add(new Label("employee", new PropertyModel(member, "employee.fullName")));
        item.add(new Label("effectiveDate", new PropertyModel(member, "effectiveDate")));
        item.add(new Label("position", new PropertyModel(member, "position.name")));
        item.add(new AjaxLink<Member>("edit", member) {
          @Override
          public void onClick(AjaxRequestTarget target) {
            modal.setContent(new MemberEditPanel(ModalWindow.CONTENT_ID, getModel()) {
              @Override
              protected void onApply(AjaxRequestTarget target) {
                modal.close(target);
                target.add(item);
              }

              @Override
              protected void onCancel(AjaxRequestTarget target) {
                modal.close(target);
              }
            });
            modal.show(target);
          }
        });
        item.add(moveUpLink("up", item));
        item.add(moveDownLink("down", item));
      }
    }.setReuseItems(true));

    form.add(new Button("save") {
      @Override
      public void onSubmit() {
        action.apply();
        setResponsePage(TeamsListPage.class);
      }
    });
    form.add(new Link("cancel") {
      @Override
      public void onClick() {
        action.undo();
        setResponsePage(TeamsListPage.class);
      }
    });
  }

  public static PageParameters forTeam(Team team) {
    PageParameters params = new PageParameters();
    params.set("id", team.getId());
    return params;
  }

}
