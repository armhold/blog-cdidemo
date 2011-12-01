package net.ftlines.blog.cdidemo.web.app;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import net.ftlines.blog.cdidemo.model.Employee;
import net.ftlines.blog.cdidemo.model.EmployeesRepository;
import net.ftlines.blog.cdidemo.util.IteratorAdapter;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class EmployeePicker extends AutoCompleteTextField<String> {
  @Inject
  EmployeesRepository employees;

  @Inject
  EntityManager em;

  private final IModel<Employee> employee;

  public EmployeePicker(String id, IModel<Employee> model) {
    super(id, new Model<String>(toString(model.getObject())));
    this.employee = model;
  }

  @Override
  protected Iterator<String> getChoices(String input) {
    final Iterator<Employee> matches = employees.search(input).iterator();
    return new IteratorAdapter<Employee, String>(matches) {
      @Override
      public String next(Employee employee) {
        return EmployeePicker.toString(employee);
      }
    };
  }

  @Override
  public void updateModel() {
    super.updateModel();
    Employee employee = null;
    String value = getModelObject();
    Pattern pattern = Pattern.compile("\\[([0-9]+)\\]");
    Matcher matcher = pattern.matcher(value);
    if (matcher.find()) {
      String id = matcher.group(1);
      try {
        employee = em.find(Employee.class, Long.parseLong(id));
      } catch (NumberFormatException e) {
        // ignoring
      }
    }

    this.employee.setObject(employee);
    if (employee != null) {
      clear();
    }
  }

  @Override
  protected void onDetach() {
    super.onDetach();
    employee.detach();
  }

  private void clear() {
    clearInput();
    setModelObject(null);
  }

  private static String toString(Employee employee) {
    if (employee != null) {
      return employee.getFirstName() + " " + employee.getLastName() + " [" + employee.getId() + "]";
    } else {
      return "";
    }
  }
}