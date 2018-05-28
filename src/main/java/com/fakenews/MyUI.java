package com.fakenews;

import javax.servlet.annotation.WebServlet;

import com.logic.QueryApiHandler;
import com.model.News;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private QueryApiHandler query = new QueryApiHandler();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //maybe change this to
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type something: ");

        Button button = new Button("Search");
        button.addClickListener(e -> {
            /*layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));*/
            query.getValidatedNews();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            List<News> newsList = query.getFilteredNews(name.getValue());
            for(News n : newsList){
                HorizontalLayout newsLayout = new HorizontalLayout();
                Label l = new Label(n.getDescription());
                l.setCaption(n.getTitle());
                l.addStyleName("mystyle");
                newsLayout.addComponent(l);
                Label validation = null;
                if(n.isValidationResult()) {
                    validation = new Label("Verdadeira");
                    validation.setCaption("Resultado da Validação: ");
                    validation.addStyleName("myStyleTwo");
                } else {
                    validation = new Label("Falsa");
                    validation.setCaption("Resultado da Validação: ");
                    validation.addStyleName("myStyleTwo");
                }
                newsLayout.addComponent(validation);

                layout.addComponent(newsLayout);
            }


        });
        
        layout.addComponents(name, button);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
