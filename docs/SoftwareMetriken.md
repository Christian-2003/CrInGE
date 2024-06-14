<img src="./resources/img/logo.png" height="100" align="right">

# CrInGE - Software Metriken

Diese Projekt soll qualitativ hochwertig sein und den üblichen Branchestandarts bezüglich Wartbarkeit und Instandhaltung entspechen.
Deshalb wurden Metriken festgelgt, um die Softwarequalität zu messen.

## Verwendete Metriken

Für diese Projekt wurden die folgende drei Metriken festgelegt:

### 1. Linting

Um das schnelle Verständnis des Codes zu steigern, soll eine einheitliche und klare äußere Struktur angestrebt werden.  
Hierfür benötigt es einer wohl definierte Formatierung und Code Styles.

Deshalb ist das Maven Plugin Checkstyle in das Projekt eingebunden. Hierdurch können Reports erstellt werden,  
welche Tipps, Warnungen und Fehler bezüglich der äußeren Form auflisten.

Dargestellt werden sollen diese als Fehler pro 100 Zeilen Code, um ein Relation abzubilden.

#### Möglichkeiten zur Verbesserung

Der bevorzugte Weg, um Fehler zu beheben, ist die Regelverstöße zu betrachten und den Code entsprechend der Regel abzuändern.

In selten fällen können allerdings Regeln auch deaktiviert oder angepasst werden werden.  
So wurden beispielsweise beriets die Regeln "HiddenField" und "MagicNumber" deaktiviert,  
da diese im Team als aktzeptable Programmierweise festgelegt wurden.

### 2. Zyklomatische Komplexität

Da für das Verständnis des Codes nicht nur die äußere Form sondern die logische Komplexität maßgeblich ist,  
wird zusätzlich die zyklomatische Komplexität der Klassen betrachtet.  
Diese spiegelt die Anzahl der möglichen Ablaufpfade wieder.

Diese werden mittels eines IntelliJ Plugins betrachtet.  
Dieses zeigt direkt zu jeder Klasse die zyklomatische Komplexität an und ermöglicht somit das Erheben von Messwerten.

Dargestellt werden soll die zyklomatische Komplexität als Durchschnitt pro Klasse.
Dabei soll Diese möglichst unter 10 bleiben und einen Wert von 20 nicht überschreiten.

#### Möglichkeiten zur Verbesserung

Klassen mit hoher zyklomatischer Komplexität sollten unterteilt werden, um teile ihrer Aufgabe in andere kleinere Klassen abzugeben.
Somit hat jede Klasse für sich eine niedrigere Komplexität.

### 3. Dependencies

Zuletzt soll die Anzahl der Dependencies maßgebend für das Projekt sein,  
da der Sinn und oberste Anforderung an die Software ist mit möglichst keinen Abhängigkeiten zu arbeiten.

#### Möglichkeiten zur Verbesserung

Werden nur wenige Funktionen einer Bibliothek verwendet können diese eigens implementiert werden,  
um die Abhängikeit von der Bibliothek aufzulösen.

## Stand zum 14.05.2024

| Metrik | Wert |
| --- | --- |
| Lines of Code | ~ 8.900 |
| Checkstyle Errors | 1.900 |
| Errors per 100 Lines | 21 |
| Durchschnittliche zyklomatische Komplexität | 11,6 |
| Anzahl Dependencies | 1 |

Dependency ist nur OpenGL

## Endstand

| Metrik | Wert |
| --- | --- |
| Lines of Code | ~ 11.700 |
| Checkstyle Errors | 439 (Stand: 12.06.2024) |
| Errors per 100 Lines | 4 |
| Durchschnittliche zyklomatische Komplexität | 11,1 |
| Anzahl Dependencies | 1 |

Dependency ist nur OpenGL

### Zusammenfassung der Verbesserung 

Bei dem Linting wurde eine Verbesserung von 80% erreicht.

Die zyklomatische Komplexität hat sich mit einer Verbesserung von 4% kaum verändert.

Die Dependencies haben sich nicht verändert, da diese bereits ziemlich minimal sind.

## Außnahmen

Die zyklomatische Komplexität ist mit 11,1 nach wie vor recht hoch.  
Dies liegt nicht zuletzt an den Klassen des Package "game_engine.controller.data_handler", deren Augabe es ist komplexe Datenstrukturen zu parsen und serialisieren.
Von einem Refactoring dieser Klassen wurde allerdings abgesehen, da dies gegen den Sinn dieser Metrik verstoßen würde.  
Grund dafür ist, dass ein Auslagern von Teilen des Codes die Gesamtübersicht der Klasse verschlechtern würde,  da der Zusammenhang und Kontext der Aufgabe nicht mehr sinnvoll wäre.
