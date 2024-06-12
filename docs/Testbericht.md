<img src="./resources/img/logo.png" height="100" align="right">

## CrInGE - Testbericht

## Inhaltsverzeichnis

1. [Einleitung](#einleitung)
2. [Teststrategie](#teststrategie)
3. [Testplan](#testplan)
4. [Testfälle](#testfälle)
5. [Testergebnisse](#testergebnisse)
6. [Metriken](#metriken)
7. [Empfehlungen](#empfehlungen)
8. [Schlussfolgerungen](#schlussfolgerung)

## Einleitung

Um eine möglichst hohe Softwarequalität zu gewährleisten und um sicherzustellen, dass die komplexen Mechanismen der Videospielengine korrekt funktionieren, soll CrInGE durchgängig Test-Driven entwickelt werden. Dazu werden Unit-Tests vor der Implementierung testbarer Komponenten entwickelt, die Sonderfälle und Randbedingungen sinnvoll abdecken. Dies wird konsequent während dem gesamtem Entwicklungsprozess durchgeführt.

Einige Komponenten sind nicht einfach über Unit-Tests testbar. Solche Komponenten sind von der Test-Driven-Entwicklung ausgeschlossen.

<!--Dieser Abschnitt gibt einen Überblick über den Softwaretestprozess und den Umfang der Testaktivitäten.-->

## Teststrategie

Die entwickelte Software wird mittels Unit-Tests getestet. Da die Software in _Java_ mittels _Maven_ entwickelt wird, wird das Test-Framework _JUnit 5_ verwendet. Die entwickelten Unit-Tests werden über eine GitHub-Actions-Pipeline bei jedem Push durchgeführt. In der [README.md](../README.md)-Datei im root-Verzeichnis des Repositories wird das Ergebnis der Tests über eine Badge angezeigt.

Unit-Tests werden von jedem Entwickler geschrieben, bevor eine testbare Komponente implementiert wird. So wird sichergestellt, dass das Verfahren der Test-Driven-Entwicklung konsequent während der Entwicklung durchgeführt wird.

Die entwickelten Unit-Tests sollen die zu testenden Komponenten sinnvoll beanspruchen. Zu testende Eigenschaften sind unter Anderem Sonderfälle, Randwerte oder falsche Eingaben.

Aufgrund der hohen Komplexität der Software können einige Komponenten nicht testbar umgesetzt werden - dazu zählen beispielsweise Singleton-Instanzen. Derartige Komponenten können nur mit erheblichem Mehraufwand mit Unit-Tests getestet werden. Da die verfügbaren menschlichen Ressourcen in diesem Projekt jedoch knapp bemessen sind, werden solche Komponenten nicht getestet. Diese Komponenten werden im weiteren Verlauf dieses Dokumentes nicht weiter berücksichtigt.

Des weiteren ist das Testen der Korrektheit der grafischen Benutzeroberfläche ausgeschlossen, da dies nicht realistisch mit Unit-Tests durchgeführt werden kann.

<!--In diesem Abschnitt wird der Gesamtansatz für das Testen beschrieben, einschließlich der Testmethodik, der Testarten und der verwendeten Testtechniken. Geben Sie an, welche automatischen Testwerkzeuge/Frameworks für Ihr Projekt verwendet werden.-->

## Testplan

Die Software wird Test-Driven entwickelt.

Dabei werden vor der eigentlichen Entwicklung von Softwarekomponenten Unit-Tests entworfen, die beispielsweise Randbedingungen oder Sondereingaben prüfen. Zu Testende Eingaben sind beispielsweise (nicht aber beschränkt auf):

* Nullwerte
* Negative Indizes
* Randwerte

Demnach werden die Unit-Tests als **Black-Box** Tests entworfen. Das bedeutet, dass Fehler in der Software durch falsche Einfaben identifiziert werden sollen, ohne dass die internen Strukturen und Funktionsweisen der zu Testenden Komponenten bekannt sind.

Nachdem Unit-Tests entworfen wurden, wird die tatsächliche Softwarekomponente entwickelt. Neben der Implementierung der gewünschten Funktionalitäten, ist ein Ziel der Entwicklung sicherzustellen, dass die zuvor entworfenen Unit-Tests erfüllt werden.

Idealerweise werden die Unit-Tests nicht von dem Teammitglied entworfen, dass die Softwarekomponente anschließend entwickelt. In Ausnahmefällen können die Unit-Tests aber vom selben Entwickler entworfen werden, der auch die Softwarekomponente implementiert.

Ausgehend hiervon gibt es keine allgemeine Testphase gegen Ende der Projektlaufzeit. Allgemeinere Tests (bspw. manuelles Testen der Benutzeroberfläche) werden unmittelbar nach der Entwicklung der jeweiligen Komponente durchgeführt.

Die Entwickler der jeweiligen Softwarekomponenten sind selbstständig dafür verantwortlich, dass die entsprechenden Tests gemäß des hier entworfenen Testplans durchgeführt werden.

<!--In diesem Abschnitt werden die spezifischen Testaufgaben, Zeitpläne und Ressourcen beschrieben, die zum Erreichen der Testziele erforderlich sind.-->

## Testfälle

Die vorhandenen Testfälle werden für den [Editor](#testfälle-für-den-videospieleditor) und die [Engine](#testfälle-für-die-videospielengine) unterschieden.

### Testfälle für den Videospieleditor

Für den Videospieleditor ergeben sich folgende Testfälle:

Nr | Test | Beschreibung | Status | Gefundene Fehler
--- | --- | --- | --- | ---
01 | EinleseTest/ressourceTest | Testen ob das Einelesen einer Datei aus den Programmressourcen funktioniert | Bestanden | Keine

### Testfälle für die Videospielengine

Für die Videospielengine ergeben sich folgende Testfälle:

Nr | Test | Beschreibung | Status | Gefundene Fehler
--- | --- | --- | --- | ---
01 | EPointTest/createEPoint | Testen des Standardkonstruktors mit Übergabeparametern | Bestanden | Keine
02 | EPoint/castEPoint | Testen des Kopierkonstruktors der Klasse `EPoint` | Bestanden | Keine
03 | EPoint/castEPointNull | Testen des Kopierkonstruktors, falls `null` übergeben wird | Bestanden | Keine
04 | EPoint/getX | Testen der Rückgabe des X-Wertes | Bestanden | Keine
05 | EPoint/getY | Testen der Rückgabe des Y-Wertes | Bestanden | Keine
06 | EPoint/setX | Testen des Ändern des X-Wertes | Bestanden | Keine
07 | EPoint/setY | Testen des Ändern des Y-Wertes | Bestanden | Keine
08 | EPoint/equals | Testen der Gleichheit zweier `EPoint`-Instanzen | Bestanden | Keine
09 | EPoint/equalsNull | Testen der Gleichheit einer `EPoint`-Instanz mit `null` | Bestanden | Keine
10 | EPoint/sameHash | Testen, ob die `hashCode`-Methode zweier identischer `EPoint`-Instanzen denselben Hashwert generiert | Bestanden | Keine
11 | EPoint/differentHash | Testen, ob die `hashCode`-Methode zweier unterschiedlicher `EPoint`-Instanzen zwei unterschiedliche Hashwerte generiert | Bestanden | Keine
12 | EPoint/String | Testen, ob die String-Representation einer `EPoint`-Instanz korrekt ist | Bestanden | Keine
13 | EVector/addNull | Testen, ob das Addieren einer `EVector`-Instanz und `null` eine Exception wirft | Bestanden | Keine
14 | EVector/add | Testen, ob die Addition zweier `EVecor`-Instanzen das korrekte Ergebnies generiert | Bestanden | Keine
15 | EVector/subNull | Testen, ob das Subtrahieren von `null` von einer `EVector`-Instanz eine Exception wirft | Bestanden | Keine
16 | EVector/sub | Testen, ob das subtrahieren zweier `EVector`-Instanzen das korrekte Ergebnis liefert | Bestanden | Keine
17 | EVector/scalarMultiplication | Testen, ob die skalare Multiplikation einer `EVector`-Instanz mit einem Skalar ein korrektes Ergebnis liefert | Bestanden | Keine
18 | EVector/dotProduct | Testen, ob das Kreuzprodukt zweier `EVector`-Instanzen ein korrektes Ergebnis generiert | Bestanden | Keine
19 | EVector/dotProductNull | Testen, ob das Kreuzprodukt einer `EVector`-Instanz mit `null` eine Exception wirft | Bestanden | Keine
20 | EVector/length | Testen, ob die Länge einer `EVector`-Instanz korrekt berechnet wird | Bestanden | Keine
21 | EVector/angleNull | Testen, ob die Berechnung des Winkels zwischen einer `EVector`-Instanz und `null` eine Exception wirft | Bestanden | Keine
22 | EVectzor/angle | Testen, ob die Berechnung des Winkels zwischen zwei `EVector`-Instanzen den korrekten Wert generiert | Bestanden | Keine
23 | EVector/crossProductNull | Testen, ob die Berechnung des Kreuzproduktes einer `EVector`-Instanz mit `null` eine Exception wirft | Bestanden | Keine
24 | EVector/crossProduct | Testen, ob die Berechnung des Kreuzproduktes zweier `EVector`-Instanzen den korrekten Wert generiert | Bestanden | Keine
25 | EVector/dependenNull | Testen, ob die Bestimmung linearer Abhängigkeit einer `EVector`-Instanz mit `null` eine Exception wirft | Bestanden | Keine
26 | EVector/depended | Testen, ob die Bestimmung linearere Abhängigkeit zweier `EVector`-Instanzen korrekt ist | Bestanden | Keine
27 | GameChunk/accessMapObjectWithNull | Testen, ob das Zugreifen auf nicht vorhandene `MapObject`-Instanzen eine Exception wirft | Bestanden | Keine
28 | GameChunk/accessExternakMapObjectWithNull | Testen, ob das Zugreifen auf nicht vorhandene externe `MapObject`-Instanzen eine Exception wirft | Bestanden | Keine
29 | GameChunk/accessMapObject | Testen, ob das Zugreifen auf `MapObject`-Instanzen die korrekten Objekte zurückgibt | Bestanden | Keine
30 | GameChunk/accessExternalMapObject | Testen, ob das Zugreifen auf externe `MapObject`-Instanzen die korrekten Objekte zurückgibt | Bestanden | Keine
31 | GameChunk/accessNonExistentMapObject | Testen, ob das Zugreifen auf nicht vorhandene `MapObject`-Instanzen eine Exception wirft | Bestanden | Keine
32 | GameChunk/accessNonExistentExternalMapObject | Testen, ob das Zugreifen auf nicht vorhandene externe `MapObject`-Instanzen eine Exception wirft | Bestanden | Keine
33 | GameChunk/resetMapObjectIterator | Testen, ob das Zurücksetzen des Iterators für `MapObject`-Instanzen funktioniert | Bestanden | Der Iterator für `MapObject`-Instanzen wird nicht korrekt zurückgesetzt
34 | GameChunk/resetExternalMapObjectIterator | Testen, ob das Zurücksetzen des Iterators für externe `MapObject`-Instanzen funktioniert | Bestanden | Der Iterator für externe `MapObject`-Instanzen wird nicht korrekt zurückgesetzt
35 | GameMap/createWithChunk | Testen, ob das Erstellen einer `GameMap` mit einem `GameChunk` funktioniert | Bestanden | Keine
36 | GameMap/createWithNoHeight | Testen, ob das Erstellen einer `GameMap` mit einer Höhe von 0 Chunks eine Exception wirft | Bestanden | Keine
37 | GameMap/createWithNoWidth | Testen, ob das Erstellen einer `GameMap` mit einer Breite von 0 Chunks eine Exception wirft | Bestanden | Keine
38 | GameMap/createWithIncorrectChunkNumber | Testen, ob das Erstellen einer `GameMap` mit definierte Breite und Höhe, aber falsche Anzahl an Chunks eine Exception wirft | Bestanden | Keine
39 | GameMap/createWithCorrectNumberOfChunks | Testen, ob das Erstellen einer `GameMap` mit definierter Breite und Höhe und korrekter Anzahl an Chunks funktioniert | Bestanden | Keine
40 | GameMap/createWithNullChunk | Testen, ob das Erstellen einer `GameMap` mit einer Liste an `GameChunk`s, von denen jedoch einer `null` ist, eine Exception wirft | Bestanden | Keine
41 | GameMap/accessChunkOutOfRange | Testen, ob das Zugreifen auf einen Chunk mit falschen Array-Indes eine Exception wirft | Bestanden | Keine
42 | GameMap/accessChunkWithCoordinates | Testen, ob das Zugreifen auf einen Chunk mittels Chunk-Koordinaten funktioniert | Bestanden | Keine
43 | GameMap/accessChunkOutOfRangeWithCoordinates | Testen, ob das Zugreifen auf einen Chunk außerhalb definierter Koordinaten eine Exception wirft | Bestanden | Keine
44 | EventDetection/instantiateCollisionDetector | Testen, ob das Instanziieren einer `CollisionEventDetector`-Instanz funktioniert | Bestanden | Keine
45 | EventDetection/instantiateMoveDetector | Testen, ob das Instanziieren einer `MoveEventDetector`-Instanz funktioniert | Bestanden | Keine
46 | EventDetection/detectCollision | Testen, ob das Erkennen einer Kollision zwischen Entitäten funktioniert | Bestanden | Keine
47 | EventDetection/detectMove | Testen, ob das Erkennen der Bewegung einer Entität funktioniert | Bestanden | Keine
48 | EventDetection/emptyEventList | Testen, ob standardmäßig keine Events bei einer Entität registriert sind | Bestanden | Keine
49 | EventDetection/addCollisionListener | Testen, ob das Hinzufügen einer `CollisionEvent`-Instanz zu einer `Entity` funktioniert | Bestanden | Keine
50 | EventDetection/addMoveListener | Testen, ob das Hinzufügen einer `MoveEvent`-Instanz zu einer `Entity` funktioniert | Bestanden | Keine
51 | EventDetection/getCollisionListener | Testen, ob eine registrierte `CollisionEvent`-Instanz korrekt abgefragt werden kann | Bestanden | Keine
52 | EventDetection/getMoveListener | Testen, ob eine registrierte `MoveEvent`-Instanz korrekt abgefragt werden kann | Bestanden | Keine
53 | EventDetection/allEventsRegistered | Testen, ob alle Events korrekt hinzugefügt werden können | Bestanden | Keine
54 | EventDetection/moveEventTrigger | Testen, ob eine Exception geworfen wird, falls ein `MoveEvent` ausgelöst wird, aber kein Event registriert ist | Bestanden | Keine
55 | EventDetection/moveEventTrigger | Testen, ob ein `MoveEvent` korrekt ausgelöst wird | Bestanden | Keine
56 | EventDetection/collisionEventTrigger | Testen, ob eine Exception geworfen wird, falls ein `CollisionEvent` ausgelöst wird, aber kein Event registriert ist | Bestanden | Keine
57 | EventDetection/collisionEventTrigger | Testen, ob ein `CollisionEvent` korrekt ausgelöst wird | Bestanden | Keine
58 | ImportEntitysTest/init | Testen, ob Entitäten korrekt aus einer Datei geladen werden | Bestanden | Keine
59 | ImportEntitysTest/loadEntityTest1 | Testen ob die Dimension der geladenen Texturen für Entitäten korrekt ist | Bestanden | Keine
60 | ImportEntitysTest/checkEntity | Testen, ob die Attributwerte von Entitäten korrekt aus einer Datei geladen werden | Bestanden | Keine
61 | ImportEntitysTest/decode_and_encode | Testen, ob das enkodieren und darauffolgendes dekodieren einer Entität dieselbe Entität erzeugt | Bestanden | Keine

<!--In diesem Abschnitt werden die spezifischen Testfälle aufgeführt, die ausgeführt wurden, einschließlich des Status "bestanden/nicht bestanden" und der während der Tests gefundenen Fehler. (Sie können auf das Repository mit den Anwendungsfällen verweisen.)-->

## Testergebnisse

Insgesamt wurden **62** Unit-Tests im Rahmen der Test-Driven entwicklung entworfen.

Folgende Fehler wurden gefunden:

Fehler | Test | Schweregrad<br/>(0 - 10) | Maßnahmen zur Behebung
--- | :-: | :-: | :-:
Der Iterator für `MapObject`-Instanzen wird nicht korrekt zurückgesetzt | 33 | 2 | Änderung der Implementierung
Der Iterator für externe `MapObject`-Instanzen wird nicht korrekt zurückgesetzt | 34 | 2 | Änderung der Implementierung

<!--In diesem Abschnitt werden die Testergebnisse zusammengefasst, einschließlich der wichtigsten gefundenen Fehler, ihres Schweregrads und der zu ihrer Behebung unternommenen Schritte. (Sie können einen Link zu den von Ihrem Testwerkzeug erstellten Testberichten einfügen oder diese kopieren.)-->

## Metriken

Folgende Metriken sind während des Testprozesses angefallen:

Anzahl durchgeführter Tests: **62**

Anzahl gefundener Fehler: **2**

Durchschnittlischer Schweregrad der gefundenen Fehler: **2**

Durchschnittliche Zeit zur Fehlerbehebung: **4 Minuten**

Testabdeckung: **25 %**  
_Hinweis: Unter Testabdeckung wird der Prozentuale Anteil der Klassen gemessen, der von Unit-Tests abgedeckt wird._

<!--Dieser Abschnitt enthält quantitative Daten über den Testprozess, wie z.B. die Anzahl der gefundenen Fehler, die Zeit zur Fehlerbehebung und die erreichte Testabdeckung.-->

## Empfehlungen

Durch die Durchführen der Test-Driven entwicklung wurden die Unit-Tests von denselben Entwicklern entworfen, die die späteren Komponenten entwickeln. Dies ist unter Umständen nicht sinnvoll, da einige Sonderfälle und Randbedingungen unbeachtet bleiben.  
Insgesamt wäre es sinnvoll die Entwicklung von Unit-Tests und der zugehörigen Softwarekomponenten von unterschiedlichen Entwicklern durchführen zu lassen.

Des Weiteren ist anzumerken, dass die Test-Driven Entwicklung nicht immer korrekt durchgeführt wurde. Häufig wurden die Softwarekomponenten zuerst entwickelt, was auch anhand verschiedenster Commits nachvollziehbar ist. Dies ist insoweit problematisch, da die anschließend entwickelten Unit-Tests nicht darauf abzielen die Softwarekomponenten auf Randbedingungen und Sonderfälle zu überprüfen, sondern den Fokus auf das "Präsentieren" der bekannten und definierten Eingaben legen. Hierdurch werden im Extremfall Sonderfälle gar nicht abgedeckt.  
Dies könnte vorgebeugt werden, indem im GitHub-Project explizit Tasks angefertigt werden, die das Anfertigen von Unit-Tests vorschreiben. Die Tasks zum Entwickeln der entsprechenden Softwarekomponente werden solange als "Blocked" markiert, bist der entsprechende Task bezüglich der Unit-Tests abgeschlossen ist. Damit dies eingehalten wird, müssen die entsprechenden Tasks jedoch ebenfalls von einem Entwickler eingestellt werden, der an der Entwicklung der Unit-Tests und Softwarekomponenten unbeteiligt ist.

<!--Dieser Abschnitt enthält Vorschläge zur Verbesserung des Testprozesses und der Qualität der Software.-->

## Schlussfolgerung

Da die Entwicklung der Software größtenteils Test-Driven verläuft, sind nur wenige Fehler durch Unit-Tests aufgefallen, da die entsprechenden Unit-Tests vor der tatsächlichen Komponentenentwicklung entworfen wurden.

Insgesamt hat dieses Verfahren insofern funktioniert, dass die zu betrachtenden Randbedingungen von Beginn an betrachtet wurden. Hierdurch konnten die Softwarekomponenten übersichtlicher entwickelt werden, was die Wartung erleichert.

<!--In diesem Abschnitt werden die wichtigsten Ergebnisse der Tests und der Gesamtstatus der Softwarequalität zusammengefasst.-->
