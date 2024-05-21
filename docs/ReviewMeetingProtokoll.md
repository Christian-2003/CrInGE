# Protokoll des Review Meeting bezüglich des Projektes CrInGE
Das Meetings am 16.05.2024 began um 10:02 Uhr und endete um 11:07 Uhr.

## Anwesenheit
Anwesend waren Jannik, Tim, Christian und Felix.  
Kai fehlte gesundheitsbedingt.

Das Meeting wurde von Christian geführt.  
Das Protokoll hat von Tim angefertigt.

## Schwerpunkt des Meetings
Wie vorab mit dem Entwicklungsteam besprochen wurde das Package "game_dataHandler" für das Review ausgewählt.  
Jenes soll die verschiedenen Datenstrukturen des Editors und der Engine parsen, serialisieren und in einander umwandeln können.

Grund für die Wahl ist die Komplexität aufgrund der verschiedenen Datenstrukturen,  
sowie die Kritikalität für das funktionale Zusammenspiel zwischen den beiden Subsystemen.

## Methodik und Kriterien des Reviews
Das Review wurde als sytematischer Walkthrough des Codes aller Klassen durchgeführt.

Dabei wurde insbesondere auf Codequalität und Wartbarkeit geachtet.

## Komponenten des Reviews
Im Folgenden werden alle betrachteten Packages und Klassen mit den zugehörigen Ergebnissen gelistet.

### exceptions
Dieses Package wurde zur vollsten Zufriedenheit implementiert.

## game_dataformat
Die nicht genutzen Methoden ergeben logisch Sinn und sind für eine zukünftige Nutzung von Bedeutung.  
Daher ist dieses Package sogar mehr als zufriedenstellend implementiert.
 
## save files
Diese Dateien gehören nicht in den Source Code sondern in die Ressources der Tests, da diese nur zu Testzwecken verwendet werden.
 
## FileCoder
Das einmalige Matching via Regex ist fragwürdig auch wenn es korrekt arbeitet.  
Ein rekursives traversieren des Baumes würde den Code leichter verständlich machen.

Sonst ist die Implementation zufriedenstellend.
 
## Loader
Der Regex in Zeile 165 ist nichgt optimal und daher viel zu groß. Außerdem sollte dies im Sinne der Wartbarkeit aufgeteilt werden.  
Die Funktion decodeToVarible hat eine zyklomatische Komplexität von 33. Das ist viel zu groß und sollte dringend in mehrere kleiner Teilmethoden resultieren.  
Die Attribute einer Klasse mit Konstruktor sollten grundsätzlich nicht static sein, da diese sonst über alle Objekt der Klasse geteilt werden.
Der Standartkonstruktor sollte keinen festen Pfad zu einem Save Game enthalten, da dieser sonst nicht ausgetauscht werden kann.  
Das Reparieren von Save Files während dem Laden ist nocht aufgabe der Klasse. Hier werden unnötige Resourcen gebraucht. Diese Aufgabe sollte in eine neue Klasse abgetreten werden.  
Die Einrückung via Regex und startswith zu prüfen ist momentan sehr statisch und die Notwendigkeit bezüglich der Datenstruktur ist nicht gegeben.

## Ergebnisse des Meetings
Nach ausführlicher Betrachtung des *game_datahandler* wurden folgende Schlüsse gezogen:

### Abgeleitete Aufgaben
Da der Code seine funktionalen Anforderungen erfüllt und für die Demo immernoch einige Entwicklungsarbeit ansteht, werden die vorhandenen Ressourcen auf das Erstellen der Demo gelenkt und von einem erneuten Refactoring abgesehen.

### Bewährte Praktiken
Die funktionalen Anforderungen an das Package wurden erfüllt und fristgerecht abgegeben.  
Außerdem sind im Sinne der Vollständigkeit Funktionen Implementiert, welche momentan noch nicht genutzt werden, allerdings in Zukunft eine Rolle spielen können.

### Verbesserungspotenzial
Die JavaDoc Kommentare müssen nach jeder Änderung des Codes auf Korrektheit geprüft werden, um diese stehts aktuell zu halten.  
Außerdem soll die Standart Maven Projekt Strukur eingehalten werden.

Durch mehrere kleine Funktionen und Klassen, können große zyklomatische Komplexität und Modularität erreicht werden.  
Beim arbeiten mit Bäumen können diese mittels Rekursion traversiert werden, um die Wartbarkeit zu erhöhen.
