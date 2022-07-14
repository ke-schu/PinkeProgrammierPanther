package exceptions;

import java.io.IOException;

import static resources.Strings.JSON_NICHT_LESBAR_INFO;

/**
 Die JsonNichtLesbarException kann beim Einlesen von Kartendecks geworfen
 werden. */
public class JsonNichtLesbarException extends IOException
{
    /**
     Konstruiert eine JsonNichtLesbarException.
     */
    public JsonNichtLesbarException ()
    {
        super(JSON_NICHT_LESBAR_INFO);
    }
    
    /**
     Konstruiert eine JsonNichtLesbarException mit uebergebenen Werten.
     @param nachricht die Nachricht der Exception.
     @param grund der Grund der Exception.
     */
    public JsonNichtLesbarException (String nachricht, Throwable grund)
    {
        super(nachricht, grund);
    }
}