<img src="./resources/img/logo.png" height="100" align="right">

# CrInGE - Architektonische Anforderungen

## 1. Einleitung

Dieses Dokument beschreibt die Softwarearchitektur und alle daraus entstehenden Anforderungen für die Anwendung "CrInGE".

### 1.1 Übersicht

Um die Softwarearchitektur der Videospielengine "CrInGE" und des dazugehörigen Videospieleditors klar zu definieren und festzuhalten, sollen in diesem Dokument alle relevanten Anforderungen an die zugehörige Softwarearchitektur zentral dokumentiert werden. Damit soll sichergestellt werden, dass die Entwicklung der Software stringent den dokumentierten Anforderungen genügt und eine Software entsprechender Qualität produziert wird.

### 1.2 Geltungsbereich

Dieses Dokument beschreibt alle Anforderungen für die Anwendung "CrInGE". Dies beinhaltet sowohl alle Anforderungen an die Videospielengine, als auch an den Videospieleditor.

Sollte dies notwendig sein, können Subsysteme zukünftig mit ihren eigenen architektonischen Anforderungen ausgestattet werden. Dies wird dann in diesem Dokument entsprechend gekennzeichnet. Aktuell ist dies jedoch nicht geplant.

### 1.3 Definitionen, Akronyme und Abkürzungen

Im Nachfolgenden folgt eine List aller Definitionen, Akronyme und Abkürzungen, die im Weiteren Verlauf dieses Dokumentes verwendet werden.

<!-- Bitte beachten: Die Einträge in dieser Tabelle sollen alphabetisch (nach dem Akronym) sortiert werden! -->
Akronym | Bedeutung
--- | ---
API | <ins>A</ins>pplication <ins>P</ins>rogrammer <ins>I</ins>nterface (Deutsch: Programmierschnittstelle)
ASR | <ins>A</ins>rchitecutrally <ins>s</ins>ignificant <ins>r</ins>equirements
CrInGE | <ins>C</ins>ompute<ins>r</ins>ized <ins>In</ins>tegrated <ins>G</ins>ame <ins>E</ins>ngine
MVC | <ins>M</ins>odel-<ins>V</ins>iew-<ins>C</ins>ontroller

### 1.4 Referenzen

Im Nachfolgenden folgt eine Liste aller Referenzen, die im weiteren Verlauf dieses Dokumentes verwendet werden.

<!-- Bitte beachten: Die Einträge in dieser Tabelle sollen nach dem Veröffentlichungsdatum sortiert werden! -->
Titel | Datum | Veröffentlichungsorganisation | Link
--- | --: | --- | ---
CrInGE Logo | 01.11.2023 | CrInGE Entwicklungsteam | [CrInGE Logo](./resources/img/logo.png)

## 2. Architektonische Darstellung
Dieses Projekt verwendet das MVC-Entwurfsmuster sowohl für den Videospieleditor, als auch für die Videospielengine. Beide genannten Bestandteile des Projektes werden mit Java Swing entwickelt, weshalb sich dieses Entwurfsmuster anbietet.

Darüber Hinaus ermöglicht das MVC-Entwurfsmuster eine saubere und übersichtliche Trennung der Geschäftslogik und der Darstellung. Hierdurch kann übersichtlicherer Quellcode entstehen, was die Entwicklung und Weiterentwicklung der Anwendung erleichtert.


