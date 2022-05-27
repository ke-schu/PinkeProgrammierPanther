module PinkeProgrammierPanther {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.fxml;
    requires com.google.gson;
    opens gui;
    opens control;
    opens exceptions;
    opens io;
    opens model;
    opens resources;
    opens model.ereignisse;
    opens gui.xml;
}