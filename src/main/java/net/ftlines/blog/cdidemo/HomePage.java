package net.ftlines.blog.cdidemo;

import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import net.ftlines.blog.cdidemo.model.Employee;
import net.ftlines.blog.cdidemo.web.EntityProvider;
import net.ftlines.wicket.cdi.CdiContainer;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {

	@Inject
	EntityManager em;

	public HomePage() {

		add(new Label("count", new LoadableDetachableModel<Long>() {
			@Override
			protected Long load() {
				return (Long) em.createQuery("SELECT COUNT(*) FROM Employee").getSingleResult();
			}
		}));

		DataView<?> employees = new DataView<Employee>("employees", new EmployeeProvider()) {
			@Override
			protected void populateItem(Item<Employee> item) {
				IModel<Employee> user = item.getModel();
				item.add(new Label("first", new PropertyModel(user, "firstName")));
				item.add(new Label("last", new PropertyModel(user, "lastName")));
				item.add(new Label("email", new PropertyModel(user, "email")));
			}
		};
		employees.setItemsPerPage(10);
		add(employees);
		add(new PagingNavigator("navigator", employees));

	}

	private static class EmployeeProvider extends EntityProvider<Employee> {

		@Inject
		EntityManager em;

		public Iterator<Employee> iterator(int first, int count) {
			return em.createQuery("FROM Employee").setFirstResult(first).setMaxResults(count).getResultList()
					.iterator();
		}

		public int size() {
			return ((Number) em.createQuery("SELECT COUNT(*) FROM Employee").getSingleResult()).intValue();
		}

	}
}
