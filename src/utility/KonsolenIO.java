package utility;

/**
 Diese Klasse ist fuer Eingabe- und Ausgabemethoden durch die Konsole. */
public class KonsolenIO
{
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private KonsolenIO ()
    {
    }
    
    /**
     Diese Methode gibt, das uebergebene Objekt in der Konsole aus.
     @param o auszugebendes Objekt
     */
    public static void ausgeben (Object o)
    {
        System.out.println(o);
    }
}
