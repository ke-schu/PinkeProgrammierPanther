package control;

import exceptions.KartenDeckFehlerhaftException;
import io.CharakterIO;
import model.Charakter;

import java.io.IOException;
import java.util.Stack;

public class CharakterController
{
    public static Charakter leseCharakter (int position) throws IOException, KartenDeckFehlerhaftException
    {
        Stack meineCharaktere = CharakterIO.leseDatei();
        Charakter meinCharakter = new Charakter((Charakter) meineCharaktere.get(position));
        return meinCharakter;
    }
}
