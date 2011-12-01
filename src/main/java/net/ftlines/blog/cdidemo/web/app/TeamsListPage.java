package net.ftlines.blog.cdidemo.web.app;

import java.util.Iterator;

import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.Team;
import net.ftlines.blog.cdidemo.model.TeamsRepository;
import net.ftlines.blog.cdidemo.web.EntityProvider;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class TeamsListPage extends BasePage {
  public TeamsListPage() {

    DataView<?> employees = new DataView<Team>("teams", new TeamsProvider()) {
      @Override
      protected void populateItem(Item<Team> item) {
        IModel<Team> team = item.getModel();
        item.add(new Label("name", new PropertyModel(team, "name")));
        item.add(new BookmarkablePageLink("edit", TeamEditPage.class, TeamEditPage.forTeam(team.getObject())));
      }
    };
    employees.setItemsPerPage(10);
    add(employees);
    add(new PagingNavigator("navigator", employees));
  }

  private class TeamsProvider extends EntityProvider<Team> {

    @Inject
    TeamsRepository teams;

    public Iterator<Team> iterator(int first, int count) {
      return teams.list(first, count).iterator();
    }

    public int size() {
      return teams.count();
    }

  }
}
