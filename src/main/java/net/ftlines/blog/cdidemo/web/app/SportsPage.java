package net.ftlines.blog.cdidemo.web.app;

import net.ftlines.blog.cdidemo.model.Sport;
import net.ftlines.blog.cdidemo.model.SportsRepository;
import net.ftlines.blog.cdidemo.web.ConversationModel;
import net.ftlines.blog.cdidemo.web.UserAction;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import javax.inject.Inject;

public class SportsPage extends BasePage
{
    @Inject
    UserAction action;

    @Inject
    SportsRepository sportsRepository;
    
    public SportsPage()
    {

        action.begin();

        // create new, unsaved Sport entity
        final IModel<Sport> model = new ConversationModel<Sport>(new Sport());
        
        Form form = new Form("form")
        {
            @Override
            protected void onSubmit()
            {
                // persist and commit the Sport
                sportsRepository.saveSport(model.getObject());
                action.apply();
            }
        };
        

        RequiredTextField<String> nameField = new RequiredTextField<String>("name", new PropertyModel<String>(model, "name"));
        form.add(nameField);

        Button cancelButton = new Button("cancelButton")
        {
            @Override
            public void onSubmit()
            {
                action.undo();
                setResponsePage(HomePage.class);
            }
        }.setDefaultFormProcessing(false);
        
        form.add(cancelButton);

        add(form);
    }
}
