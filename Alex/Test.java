package Alex;

import static Alex.KartenEinheitType.EricKarte;

public class Test
{
    public static void main(String[] args)
    {
        Karte karteeins = new KarteEinheit(EricKarte);
        Karte kartezwei = new Karte();
        Karte kartedrei = new Karte();
        Karte kartevier = new Karte();
        Karte kartefuenf = new Karte();

        Deck meindeck = new Deck();
        Kartenhand meinehand = new Kartenhand();


        meindeck.push(karteeins);
        meindeck.push(kartezwei);
        meindeck.push(kartedrei);
        meindeck.push(kartevier);
        meindeck.push(kartefuenf);

        System.out.println("Der Stack ist "+meindeck.size()+" Elemente groß.");

        meinehand.handziehen(meindeck);

        System.out.println("habe eine hand gezogen, jetzt ist mein deck "+meindeck.size()+" Elemente groß.");
        meinehand.karteablegen(1,meindeck);
        System.out.println("habe eine karte abgelegt jetzt ist mein deck wieder  "+meindeck.size()+" Elemente groß.");

        meinehand.handablegen(meindeck);
        System.out.println("habe meine hand abgelegt jetzt ist mein deck wieder  "+meindeck.size()+" Elemente groß.");








    }
}
