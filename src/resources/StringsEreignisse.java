package resources;

/**
 * Dieses Interface enthaelt Zeichenketten, welche fuer Ereignisse benoetigt
 * werden.
 */
public interface StringsEreignisse
{
    //  Namen der Ereignisse
    String START_RAUM_NAME = "Startpunkt.";
    String HAENDLER_NAME = "Der Spielplatz.";
    String TEMPEL_NAME = "Die Nachbarschaft.";
    String SCHMIED_NAME = "Die Schule.";
    String HEILER_NAME = "Mutti.";
    String GEGNER_NAME = "Gegner.";
    String TRUHE_NAME = "Truhe.";
    String ZUFALLS_EREIGNIS_NAME = "Zufaelliges Ereignis.";
    String LEERER_RAUM_NAME = "Leere Straße.";
    String TREPPE_NAME = "Die Bushaltestelle.";

    //  Beschreibungen der Ereignisse
    String START_RAUM_BESCHREIBUNG = "Hier bin ich ausgestiegen, " +
                                     "ich sollte mich in der Gegend mal " +
                                     "umsehen.";
    String HAENDLER_BESCHREIBUNG =
            "Der Spielplatz! Ein großartiger Ort um neue Freunde zu finden.";
    String TEMPEL_BESCHREIBUNG =
            "Wenn ich einen meiner Freunde loswerden will, " +
            "sollte ich bei seinen Eltern petzen gehen.";
    String SCHMIED_BESCHREIBUNG =
            "In der Schule kann man sicher viel Neues lernen.";
    String HEILER_BESCHREIBUNG =
            "Wenn ich Schmerzen hab sollte ich zu Mutti gehen. " +
            "Danach gehts es mir immer besser.";
    String GEGNER_BESCHREIBUNG =
            "Ein Gegner! Sollte ich ihn bekaempfen oder doch versuchen ihn " +
            "zu meiden?";
    String TRUHE_BESCHREIBUNG = "Eine Truhe, was da wohl drin ist?";
    String ZUFALLS_EREIGNIS_BESCHREIBUNG =
            "Ich kann nicht erkennen was vor mir ist. Ich muss naeher " +
            "rangehen...";
    String LEERER_RAUM_BESCHREIBUNG =
            "Hier ist absolut gar nichts... wie langweilig.";
    String TREPPE_BESCHREIBUNG =
            "Der naechste Bus kommt gleich. Wenn ich weiter will sollte ich" +
            " mitfahren.";

    //Strings fuer die Eingabe
    String EINGABE_BOOLEAN =
            "\"Auswahl: Entweder true oder false, um Ereignis auszufuehren" +
            ".\"";

    //Strings Truhe
    String TRUHE_1 = "Truhe 1";
    String TRUHE_2 = "Truhe 2";

    //Strings ZufallsEreignis
    String EREIGNIS_1 = "Ereignis 1";
    String EREIGNIS_2 = "Ereignis 2";
}
