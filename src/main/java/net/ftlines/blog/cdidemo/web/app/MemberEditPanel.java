package net.ftlines.blog.cdidemo.web.app;

import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.Member;
import net.ftlines.blog.cdidemo.model.PositionsRepository;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

public abstract class MemberEditPanel extends GenericPanel<Member> {

  @Inject
  PositionsRepository positions;

  public MemberEditPanel(String id, IModel<Member> member) {
    super(id, member);

    final FeedbackPanel feedback = new FeedbackPanel("feedback");
    feedback.setOutputMarkupId(true);
    add(feedback);

    Form form = new Form("form");
    add(form);

    form.add(new EmployeePicker("employee", new PropertyModel(member, "employee")));
    form.add(DateTextField.forDatePattern("effectiveDate", new PropertyModel(member, "effectiveDate"), "MM/dd/yyyy")
        .add(new DatePicker()));
    form.add(new DropDownChoice("position", new PropertyModel(member, "position"), new LoadableDetachableModel() {
      @Override
      protected Object load() {
        return positions.list();
      }

    }, new ChoiceRenderer("name", "id")));

    form.add(new AjaxButton("apply") {
      @Override
      protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        MemberEditPanel.this.onApply(target);
      }

      @Override
      protected void onError(AjaxRequestTarget target, Form<?> form) {
        target.add(feedback);
      }
    });

    form.add(new AjaxLink("cancel") {
      @Override
      public void onClick(AjaxRequestTarget target) {
        MemberEditPanel.this.onCancel(target);
      }
    });
  }

  protected abstract void onApply(AjaxRequestTarget target);

  protected abstract void onCancel(AjaxRequestTarget target);
}
