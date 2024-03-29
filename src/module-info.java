module PinkeProgrammierPanther {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.google.common;
    opens control;
    opens exceptions;
    opens utility;
    opens model;
    opens resources;
    opens model.ereignisse;
    opens view.fxml;
    opens view.components;
    opens view.fxmlControl;
    opens view.mp3;
}