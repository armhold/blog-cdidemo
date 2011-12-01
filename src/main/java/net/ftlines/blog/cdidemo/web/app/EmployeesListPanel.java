package net.ftlines.blog.cdidemo.web.app;

import java.util.Iterator;

import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.Employee;
import net.ftlines.blog.cdidemo.model.EmployeeCriteria;
import net.ftlines.blog.cdidemo.model.EmployeesRepository;
import net.ftlines.blog.cdidemo.web.EntityProvider;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class EmployeesListPanel extends GenericPanel<EmployeeCriteria> {

  public EmployeesListPanel(String id, IModel<EmployeeCriteria> model) {
    super(id, model);

    DataView<?> employees = new DataView<Employee>("employees", new EmployeeProvider()) {
      @Override
      protected void populateItem(Item<Employee> item) {
        IModel<Employee> user = item.getModel();
        item.add(new Label("first", new PropertyModel(user, "firstName")));
        item.add(new Label("last", new PropertyModel(user, "lastName")));
        item.add(new Label("email", new PropertyModel(user, "email")));
        item.add(new Label("hireDate", new PropertyModel(user, "hireDate")));
      }
    };
    employees.setItemsPerPage(10);
    add(employees);
    add(new PagingNavigator("navigator", employees));
  }

  private class EmployeeProvider extends EntityProvider<Employee> {

    @Inject
    EmployeesRepository employees;

    public Iterator<Employee> iterator(int first, int count) {
      return employees.list(getModelObject(), first, count).iterator();
    }

    public int size() {
      return employees.count(getModelObject());
    }

  }

}
