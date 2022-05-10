package control;

import io.CharakterIO;
import model.Charakter;

import java.io.IOException;
import java.util.Stack;

public class CharakterController
{
    public static Charakter leseCharakter (int position) throws IOException
    {
        Stack meineCharaktere = CharakterIO.leseDatei();
        return (Charakter) meineCharaktere.get(position);
    }
}
