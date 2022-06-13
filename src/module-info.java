module PinkeProgrammierPanther {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.fxml;
    requires com.google.gson;
    opens view;
    opens control;
    opens exceptions;
    opens utility;
    opens model;
    opens resources;
    opens model.ereignisse;
    opens view.xml;
    opens control.test;
    opens view.components;
    opens view.xmlControl;
}