# Angriff auf die Vorstadt

Implementierung des Spiels DungeonTop als Gruppenarbeit

Entwicklungsarbeit Programmieren II<br/>
Hochschule Bremen, Fakultät 4<br/>
Gruppe: PinkeProgrammierPanther<br/>
Adrian Ruholt, Alexander Lorenz, Hendrik Böning, Henrik Janovsky und Keno Schumann

## Aufgabenstellung

Sie sollen die Mechanik des Spiels Dungeon Top in Java und JavaFX nach den unten
genannten Anforderungen implementieren und visualisieren, dabei sollen Sie kreativ sein
und Teile der Spielmechanik verbessern bzw. meinen Anforderungen anpassen. Beachten
Sie, dass Sie nur die unten genannten Anforderungen umsetzen müssen und im 2D
bleiben.

Jede Gruppe bereitet zu jedem Labortag einen DIN A4 Zettel mit Namen, Matrikelnummer
und erbrachter Wochenleistung der Gruppenmitglieder vor. Jedes Gruppenmitglied
bestätigt seine Wochenleistung mit der eigenhändigen Unterschrift. Besprechen Sie jede
Woche ihre Ergebnisse mit mir und stellen Sie Fragen, damit Ihnen meine Anforderungen
klarer werden. Es besteht für jeden Anwesenheitspflicht.

- Player spielt gegen Dungeon-Master am selben PC oder über Netzwerk. Player
  gewinnt das Spiel, wenn er sieben Bosse aus sieben Levels besiegt hat. DungeonMaster siegt, sobald die Lebenspunkte
  des Players kleiner oder gleich Null sind.
  Lassen Sie sich Mechaniken einfallen, die das Spiel ausgeglichen gestalten.
- Player hat einen Level, einen Wert, wie viele Karten pro Runde im Kampf gezogen
  werden können, einen Wert für Mana, einen Angriffswert und die Anzahl der
  Lebenspunkte. Player kann eine Waffe und bis zu zwei Artefakte besitzen. Waffen
  und Artefakte verändern Werte des Players. Außerdem kann Player Talente
  besitzen, die Werte des Players oder der Minions beeinflussen oder auch andere
  Effekte erzielen können. Gestalten Sie Dungeon-Master entsprechend nach Ihren
  Vorstellungen, achten Sie jedoch auf eine funktionierende Balance.
- Das Spiel ist rundenbasiert. Es soll keine KI implementiert werden.
- Es wird auf einer rechteckigen 2D Karte gespielt, die es in den Größen klein, mittel
  und groß gibt. Die Felder der Karte sind Quadrate.
- Player und Dungeon-Master spielen Karten aus, um Effekte zu erzielen oder
  Minions auf der Karte zu erzeugen.
- Minions besitzen einen Wert für Lebenspunkte und einen Angriffswert. Minions
  können nur benachbart zum Player/Dungeon-Master oder eigenen Minions erzeugt
  werden. Dabei gelten die Felder Nord, Süd, West und Ost als benachbart.
- Jede Karte hat einen Wert für die Anzahl des Manas, die Player oder DungeonMaster beim Ausspielen der Karte abgezogen
  werden.
- Player, Dungeon-Master und Minions können in einer Runde bewegt werden.
  Bewegungsrichtungen sind Nord, Süd, West und Ost. Die Anzahl der Felder, die
  eine Spielfigur pro Runde bewegt werden kann, wird durch einen Wert festgelegt.
  Beachten Sie, dass erzeugte Minions sich erst in der nächsten Runde bewegen
  können, wenn sie nicht die Fähigkeit haben, sofort nach dem Spawn zu agieren.
- Player, Dungeon-Master und Minions können gegnerischen Figuren angreifen,
  wenn sie zu dieser Figur benachbart sind. Dabei wird der angegriffenen Spielfigur
  (Angriffswert angreifende Spielfigur – Verteidigungswert angegriffenen Spielfigur)
  Lebenspunkte abgezogen. Sinken die Lebenspunkte der angegriffenen Spielfigur
  auf Null, verschwindet sie vom Spielfeld. Und auch die von dieser Spielfigur
  bewirkten Effekte erlöschen. Beachten Sie, dass manche Waffen wie Bögen einen
  Angriff über mehrere Felder hinaus ermöglichen. Es soll Minions geben, die Angriffe
  über mehrere Felder wirken können und/oder Gegner auf mehreren Feldern treffen.
- Zu Beginn eines Spiels wählen die spielenden Personen eine Charakterklasse aus.
  Es sollen mindestens 3 verschiedene Klassen implementiert werden. Die Wahl der
  Klasse bestimmt die Anfangswerte und das Deck von Player und Dungeon-Master.
- Ein Deck besteht aus Karten, die Effekte erzielen oder Minions erzeugen.
- Player startet im ersten von sieben Levels eines Dungeons. Stellen Sie Level
  geeignet dar. Player soll in einem Level von Raum zu Raum gehen können. In
  einem Raum erwarten Player Kämpfe gegen Monster, die von Dungeon-Master
  kontrolliert werden, Ereignisse (Händler, Aufwertungen, Heilung, Schatzfunde, usw.)
  oder die Treppe hinab in den nächsten Level. Diese Treppe wird vom Level-Boss
  bewacht. Sie haben bei der Darstellung der Level freie Hand und müssen sich nicht
  an der Vorlage Dungeon Top halten.
- Denken Sie daran, dass Player im Laufe des Spiels Werte und das Deck
  verbessern kann. Sie können dies über Erfahrungspunkte lösen. Es soll auch nach
  jedem erfolgreichen Kampf Gold, neue Karten, Waffen oder Talente geben. Gold
  soll bei einem erfolglosen Run erhalten bleiben. Mit diesem Gold kann Player vor
  Beginn eines neuen Runs seine Werte aufbessern, Karten, Talente und Waffen
  kaufen.
- Die Monster in den höheren Levels sollen immer bessere Werte haben. Achten Sie
  darauf, dass die Spielmechanik spannend ist. Sie können auch neue Mechaniken,
  die es in Dungeon Top nicht gibt, einfügen. In Dungeon Top gibt es eine KI. Sie
  lassen zwei Menschen gegeneinander spielen, also achten Sie auf viele
  Möglichkeiten, die Balance zu verändern.
- Setzen Sie Sound geeignet ein und visualisieren Sie die von der spielenden Person
  auszuführenden Aktionen (Selektion, Angriff, Bewegung, usw.) geeignet.

## Teilaufgabe 1 (17.05.2022)

Im ersten Teil des Labors entwerfen und implementieren Sie die Klassen mit ihren
Attributen und Methoden. Denken Sie an Kapselung, Vererbung, Polymorphismus,
Assoziationen, Aggregation, Komposition, abstrakte Klassen, Interfaces. Implementieren
Sie die notwendigen Collections für die Verwaltung der Objekte. Testen Sie Ihre Klassen
mit einem Konsolenprogramm. In diesem Teil will ich keine Grafik, keine Threads und kein
Netzwerk! Denken Sie schon jetzt daran, die zukünftige GUI von der Funktionalität sauber
zu trennen. Nehmen Sie sich Zeit über Objektorientierung nachzudenken.

## Teilaufgabe 2 (14.06.2022)

Jetzt sollen Sie die GUI entwerfen und implementieren. Sie brauchen ein Menü, in dem
man die einzelnen Funktionen auswählen oder Einstellungen vornehmen kann. Mit Menü
meine ich ein Menü, wie es in diesem Tutorial vorgestellt wird. Wie Sie Ihre GUI gestalten,
überlasse ich Ihnen. Jedoch sollten Sie moderne GUI-Elemente verwenden. Es gibt eine
GUI mit Menü, mindestens 2 Dialogen und mindestens 3 Steuerelementen davon 2
verschiedene Grundtypen (Buttons, Slider usw.). Sie visualisieren das Spielprinzip
geeignet, es muss noch nicht vollständig umgesetzt sein. Achten Sie darauf, dass Sie nur
freie Grafiken und Sounds verwenden oder selbst welche anfertigen.

## Teilaufgabe 3 (15.07.2022)

Im letzten Teil machen Sie Ihr Spiel mit den oben genanten Anforderungen spielfähig. Es
soll möglich sein über Netzwerk gegeneinander zu spielen. Die Spielmechanik soll nach
den Anforderungen vollständig umgesetzt sein.
