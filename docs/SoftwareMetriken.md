<img src="./resources/img/logo.png" height="100" align="right">

# CrInGE - Software Metriken

Diese Projekt soll qualitativ hochwertig sein und den üblichen Branchestandards bezüglich Wartbarkeit und Instandhaltung entspechen.
Deshalb wurden Metriken festgelgt, um die Softwarequalität zu messen.

## Verwendete Metriken

Für diese Projekt wurden die folgende drei Metriken festgelegt:

### 1. Linting

Um das schnelle Verständnis des Codes zu steigern, soll eine einheitliche und klare äußere Struktur angestrebt werden.  
Hierfür benötigt es einer wohl definierte Formattierung und Code Styles.

Deshalb ist das Maven Plugin _Checkstyle_ in das Projekt eingebunden worden. Hierdurch können sogenannte Reports erstellt werden,  
welche Tipps, Warnungen und Fehler bezüglich der äußeren Form auflisten.

Diese Fehler sollen als Fehler pro 100 Quellcodezeilen dargestellt werden, um eine Relation abzubilden.

#### Möglichkeiten zur Verbesserung

Der bevorzugte Weg, um Fehler zu beheben, ist das Betrachten von Regelverstößen um den Code entsprechend der Regel abzuändern.

In selten fällen können allerdings Regeln auch deaktiviert oder angepasst werden.  
So wurden beispielsweise bereits die Regeln "HiddenField" und "MagicNumber" deaktiviert, da diese im Team als aktzeptable Programmierweise festgelegt wurden.

### 2. Zyklomatische Komplexität

Da für das Verständnis des Codes nicht nur die äußere Form, sondern auch die logische Komplexität maßgeblich ist, wird zusätzlich die zyklomatische Komplexität der Klassen betrachtet.  
Diese spiegelt die Anzahl der möglichen Ablaufpfade wieder.

Diese werden mittels eines IntelliJ Plugins ermittelt.  
Dieses zeigt direkt zu jeder Klasse die zyklomatische Komplexität an und ermöglicht somit das Erheben von Messwerten.

Die zyklomatische Komplexität wird als Durchschnitt pro Klasse dargestellt. Die zyklomatische Komplexität einer Klasse soll möglichst geringer als 10 bleiben und einen Wert von 20 nicht überschreiten.

#### Möglichkeiten zur Verbesserung

Klassen mit hoher zyklomatischer Komplexität können unterteilt werden, um Teile ihrer Aufgabe in andere kleinere Klassen abzugeben.
Somit hat jede Klasse für sich eine niedrigere Komplexität.

### 3. Abhängigkeiten

Zuletzt soll die Anzahl der Abhängigkeiten zu anderen Bibliotheken maßgebend für das Projekt sein. Dies liegt daran, da die Minimierung der Abhängigkeiten eine zentrale Anforderung an das Projekt ist.

#### Möglichkeiten zur Verbesserung

Werden nur wenige Funktionen einer Bibliothek verwendet, können diese selbst implementiert werden, um die Abhängikeit von der Bibliothek aufzulösen.

## Stand zum 14.05.2024

| Metrik | Wert |
| --- | --- |
| Anzahl Codezeilen | ~ 8.900 |
| Checkstyle Fehler | 1.900 |
| Fehler pro 100 Codezeilen | 21 |
| Durchschnittliche zyklomatische Komplexität | 11,6 |
| Anzahl Abhängigkeiten | 1 |

Abhängigkeit ist nur OpenGL

## Endstand

| Metrik | Wert |
| --- | --- |
| Anzahl Codezeilen | ~ 11.700 |
| Checkstyle Fehler | 439 (Stand: 12.06.2024) |
| Fehler pro 100 Codezeilen | 4 |
| Durchschnittliche zyklomatische Komplexität | 11,1 |
| Anzahl Abhängigkeiten | 1 |

Abhängigkeit ist nur OpenGL

### Zusammenfassung der Verbesserung 

Bei dem Linting wurde eine Verbesserung von **80 %** erreicht.

Die zyklomatische Komplexität hat sich mit einer Verbesserung von **4 %** kaum verändert.

Die Abhängigkeiten haben sich nicht verändert, da diese bereits minimal sind.

## Außnahmen

Die zyklomatische Komplexität ist mit 11,1 nach wie vor recht hoch.  
Dies liegt nicht zuletzt an den Klassen des Package `game_engine.controller.data_handler`, deren Aufgabe es ist komplexe Datenstrukturen zu Parsen und Serialisieren.
Von einem Refactoring dieser Klassen wurde allerdings abgesehen, da dies gegen den Sinn dieser Metrik verstoßen würde.  
Grund dafür ist, dass ein Auslagern von Teilen des Codes die Gesamtübersicht der Klasse verschlechtern würde, da der Zusammenhang und Kontext der Aufgabe nicht mehr sinnvoll wäre.
