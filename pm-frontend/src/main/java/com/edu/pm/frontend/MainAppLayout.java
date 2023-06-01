package com.edu.pm.frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route("")
public class MainAppLayout extends AppLayout {

    public MainAppLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Project Manager");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0 10px 0 10px");
        Icon icon = new Icon("lumo", "eye");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, icon, title);
    }

    private Tabs getTabs() {
        Tab projects = new Tab();
        projects.setLabel("Projects");

        Tab myIteration = new Tab();
        myIteration.setLabel("My Team Iteration");

        Tab projectFeatures = new Tab();
        projectFeatures.setLabel("My Project Features");

        Tab planning = new Tab();
        planning.setLabel("Planning");

        Tab forum = new Tab();
        forum.setLabel("Forum");

        Tab settings = new Tab();
        settings.setLabel("Settings");

        Tabs tabs = new Tabs();
        tabs.addTabAtIndex(0, projects);
        tabs.addTabAtIndex(1, myIteration);
        tabs.addTabAtIndex(2, projectFeatures);
        tabs.addTabAtIndex(3, planning);
        tabs.addTabAtIndex(4, forum);
        tabs.addTabAtIndex(5, settings);

        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        return tabs;
    }
}

