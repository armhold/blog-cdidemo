package net.ftlines.blog.cdidemo.web.app;

import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.EmployeeCriteria;
import net.ftlines.blog.cdidemo.model.TeamRepository;

import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

public class EmployeeCriteriaPanel extends GenericPanel<EmployeeCriteria> {

    @Inject
    private TeamRepository teams;

    public EmployeeCriteriaPanel(String id, IModel<EmployeeCriteria> model) {
        super(id, model);

        Form<?> form = new Form<Void>("form");
        add(form);

        form.add(DateTextField.forDatePattern("hireDateMin", new PropertyModel(model, "hireDateMin"), "MM/dd/yyyy")
                .add(new DatePicker()));
        form.add(DateTextField.forDatePattern("hireDateMax", new PropertyModel(model, "hireDateMax"), "MM/dd/yyyy")
                .add(new DatePicker()));
        form.add(new DropDownChoice("team", new PropertyModel(model, "team"), new LoadableDetachableModel() {
            @Override
            protected Object load() {
                return teams.list();
            }
        }, new ChoiceRenderer("name", "id")).setNullValid(true));

    }

}
