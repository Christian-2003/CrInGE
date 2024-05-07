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

**TODO**
In diesem Abschnitt werden die spezifischen Testaufgaben, Zeitpläne und Ressourcen beschrieben, die zum Erreichen der Testziele erforderlich sind.

## Testfälle

In diesem Abschnitt werden die spezifischen Testfälle aufgeführt, die ausgeführt wurden, einschließlich des Status "bestanden/nicht bestanden" und der während der Tests gefundenen Fehler. (Sie können auf das Repository mit den Anwendungsfällen verweisen.)

## Testergebnisse

In diesem Abschnitt werden die Testergebnisse zusammengefasst, einschließlich der wichtigsten gefundenen Fehler, ihres Schweregrads und der zu ihrer Behebung unternommenen Schritte. (Sie können einen Link zu den von Ihrem Testwerkzeug erstellten Testberichten einfügen oder diese kopieren.)

## Metriken

Dieser Abschnitt enthält quantitative Daten über den Testprozess, wie z.B. die Anzahl der gefundenen Fehler, die Zeit zur Fehlerbehebung und die erreichte Testabdeckung.

## Empfehlungen

Dieser Abschnitt enthält Vorschläge zur Verbesserung des Testprozesses und der Qualität der Software.

## Schlussfolgerung

In diesem Abschnitt werden die wichtigsten Ergebnisse der Tests und der Gesamtstatus der Softwarequalität zusammengefasst.
