# CrInGE - Softwareanforderungen

## 1. Einleitung

Diese Software Anforderungsspezifikation (SRS) beschreibt alle Spezifikationen für die Anwendung "CrInGE". Dieses Dokument beinhaltet eine Übersicht über das Projekt, sowie dessen Anforderungen.

### 1.1 Übersicht

Um die Entwicklung von zweidimensionalen Videospielen zu erleichtern, soll eine Videospielengine entwickelt werden. Die Anwendung CrInGE beinhaltet einen Editor, welcher sowohl das zusammenstellen, sowie die Programmierung von Spielwelten ermöglicht. Damit soll die Hürde für interessierte Entwickler gesenkt werden, die zum Einstig in die Videospielentwicklung existiert.

### 1.2 Geltungsbereich

Das Projekt wird als eine Java Swing Anwendung realisiert. Dieses Dokument behandelt diese Anwendung als Ganzes.

Für Subsysteme können zukünftig weitere Software Anforderungsspezifikationen geschrieben werden, wenn dies nötig ist.

In diesem Dokument werden sowohl die funktionalen, als auch die nichtfunktionalen Anforderungen abgedeckt.

Des Weiteren werden keine Anforderungen in andere Dokumente ausgelagert. Hiervon ausgenommen sind Anforderungen, die (wie oben beschrieben) in weiteren Software Anforderungsspezifikationen für Subsysteme dokumentiert werden.

### 1.3 Definitionen, Akronyme und Abkürzungen

Im Nachfolgenden folgt eine List aller Definitionen, Akronyme und Abkürzungen, die im Weiteren Verlauf dieses Dokumentes verwendet werden.

Akronym | Bedeutung
--- | ---
CrInGE | <ins>C</ins>ompute<ins>r</ins>ized <ins>In</ins>tegrated <ins>G</ins>ame <ins>E</ins>ngine
API | Application Programmer Interface (Deutsch: Programmierschnittstelle)

### 1.4 Referenzen

Im Nachfolgenden folgt eine Liste aller Referenzen, die im weiteren Verlauf dieses Dokumentes verwendet werden.

Titel | Datum | Veröffentlichungsorganisation | Link
--- | --: | --- | ---
UI-MockUp | 25.10.2023 | CrInGE Entwicklungsteam | [UI-MockUp](./resources/UI%20Mockup/MochUp.png)
Anwendungsdiagramm | 25.10.2023 | CrInGE Entwicklungsteam | [Anwendungsfalldiagramm](./resources/UML/Anwendungsfalldiagramm.png)

### 1.6 UML-Anwendungsfalldiagramm

![Anwendungsfalldiagramm](./resources/UML/Anwendungsfalldiagramm.png)

### 1.7 Mock-Up

![UI-MockUp](./resources/UI%20Mockup/MochUp.png)

## 2. Funktionale Anforderungen

Dieser Abschnitt beschreibt die verschiedenen funktionalen Anforderungen an die Anwendung.

### 2.1 Übersicht

Die Anwendung ermöglicht dem Benutzer das erstellen von zweidimensionalen Videospielen.

Hierzu ermöglicht die Anwendung dem Benutzer das Zusammenbauen einer Videospielwelt über eine grafische Oberfläche. Darüber kann der Benutzer einzelne Objekte (sogenannte Sprites) in die Welt ziehen. Des Weiteren können für diese Sprites diverse Einstellung, wie beispielsweise das Kollisionsverhalten oder Ähnliches bearbeitet werden.

Darüber Hinaus stellt die Anwendung eine API bereit, über welche das Verhalten der Videospielwelt, sowie diverse Events (beispielsweise Berührungen des Spielers mit Objekten oder Ähnlichem) in der Programmiersprache Java implementiert werden können.

Des Weiteren kann der Benutzer das erstellte Videospiel anschließend in eine Jar-Datei kompilieren und exportieren. Hierdurch wird ermöglicht, dass das Videospiel ohne vorhande Videospielengine auf möglichst vielen Endgeräten problemlos ausgeführt werden kann.

### 2.2 Welteneditor

Die Anwendung beinhaltet einen grafischen Welteneditor, welcher dem Benutzer das Zusammenstellen einer Videospielwelt ermöglicht. Hierzu wird die erstellte Videospielwelt im zentralen Bereich des Editors dargestellt.

Darüber Hinaus befindet sich in diesem Welteneditor eine Liste an Objekten, die sich in der Videospielwelt befinden, aus welcher der Benutzer beliebige Objekte auswählen und inspizieren kann.

Für das jeweils ausgewählte Objekt stellt ein weiterer Bereich des Editors die Möglichkeit bereit, dessen Einstellungen (bspw. Kollisionsverhalten, Farbe, Textur, Durchsichtigkeit, ID, ...) zu bearbeiten.

In einem weiteren Bereich des Welteneditors soll der Benutzer vorgefertigte Formen (bspw. Rechtecke, Dreiecke, Kreise, Lichtquellen, ...) auswählen und in die Videospielwelt ziehen können. Hier kann der Benutzer ebenfalls eigene sogenannte Assets erstellen und aufbewahren. Damit solche Assets besser angepasst werden können, soll der Benutzer hier auch die Möglichkeit haben, eigene Texturen hochzuladen.

Diese Anforderung basiert auf den folgenden User Stories:

1. [Als Spieleentwickler möchte ich die Welt, in der mein Spiel stattfindet, durch Drag and Drop zusammenbauen können, damit ich meiner gestalterischen Kreativität freien Lauf lassen kann](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=41353628)
2. [Als Spieleentwickler möchte ich das Kollisionsverhalten von Objekten bearbeiten können](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=41354086)
3. [Als Spieleentwickler möchte ich eigene Texturen laden können](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=42294330)

Der Aufwand für diese Anforderung wird auf **hoch** geschätzt.

> :warning: **UI-Mockup(Projekt Editor)** und **UML-Verhaltensdiagramm(e)** hinzufügen.

#### 2.2.1 Voraussetzungen

Damit dies möglich ist, muss die Videospielwelt im Backend modelliert und bearbeitet werden können. Dazu müssen wiederrum individuelle Objekte im Backend modelliert und bearbeitet werden können.

Des Weiteren müssen sowohl die Objekte, als auch die Videospielwelt in einem geeigneten Dateiformat gespeichert werden können.

#### 2.2.2 Nachbedingungen

Nachdem die Videospielwelt vom Benutzer erstellt wurde, kann diese über die API programmiert werden. Hierzu muss die API existieren.

### 2.3 Programmierschnittstelle (API)

Die Anwendung ermöglicht dem Benutzer das programmieren des Verhaltens des Videospiels. Hiermit ist bspw. gemeint, dass der Benutzer Events (z.B. Berührung des Spielers mit einem Objekt), Bewegungen von objekten (z.B. Feindliche Kreaturen) oder Ähnliches programmieren kann. Hierzu ist eine API nötig.

Die API ermöglicht dem Benutzer den Zugriff auf alle Objekte in der Videospielwelt. Der Benutzer kann über die API die Attribute sämtlicher Objekte der Videospielwelt bearbeiten.

Darüber Hinaus kann der Benutzer Events registrieren, die dann vom Videospiel während der Ausführung getestet und dann eventuell ausgeführt werden.

Die API wird in Java zur Verfügung gestellt, damit der Benutzer zugriff auf die Umfangreiche Standardbibliothek hat und die Implementation des Benutzers problemlos in das Videospiel integriert werden kann.

Diese Anforderung basiert auf den User Stories:

1. [Als Spieler möchte ich Fähigkeiten meines Charakters leveln können](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=41353987)
2. [Als Spieleentwickler möchte ich auf grundlegende physikalische Funktionalitäten der Engine zugreifen, sodass ich diese beliebig verwenden kann.](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=41354193)
3. [Als Spielentwickler möchte ich Musik/Geräusche zu meinem Spiel hinzufügen, sodass dieses ein immersives Spielerlebnis bieten kann.](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=41652124)

Der Aufwand für diese Anforderung wird auf **hoch** geschätzt.

> :warning: **UI-Mockup(Object Inspector)** und **UML-Verhaltensdiagramm(e)** hinzufügen.

#### 2.3.1 Voraussetzungen

Damit dies möglich ist, muss die Videospielwelt über die grafische Oberfläche programmiert werden können. Darüber Hinaus müssen die Objekte der Videospielwelt im Backend modelliert und bearbeitet werden können.

Zudem muss ein System zum verwalten der vom Benutzer registrierten Events existieren.

#### 2.3.2 Nachbedingungen

N/A

### 2.4. Videospiel exportieren

Damit das Videospiel auf möglichst vielen Endgeräten problemlos ausgeführt werden kann, ohne dass die Videospielengine CrInGE installiert ist, sollen erstellte Videospiele exportiert werden können.

Hierzu kann der Benutzer einige Einstellungen, wie beispielsweise die zur kompilierung verwendete Java-Version oder Ähnliches bearbeiten.

Anschließend kann der Benutzer das Spiel in eine Jar-Datei exportieren.

Diese Anforderung basiert auf der User Story:

1. [Als Spieleentwickler möchte ich das entwickelte Spiel exportieren können, sodass es einfach von Spielern gespielt werden kann.](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=41353971)

Der Aufwand für diese Anforderung wird auf **mittel** geschätzt.

> :warning: **UI-Mockup(Projekt Auswahl [Exportieren hinzufügen])** und **UML-Verhaltensdiagramm(e)** hinzufügen.

#### 2.4.1 Voraussetzungen

Damit das Videospiel exportiert werden kann, muss eine Videospielwelt erstellt und programmiert worden sein.

#### 2.4.2 Nachbedingungen

N/A

### 2.5 Physikengine

Damit der Benutzer möglichst effizient Videospiele entwickeln kann, soll dieser auf grundlegende physikalische Funktionalitäten zurückgreifen können.

Diese physikalischen Funktionalitäten werden über die [API](#23-programmierschnittstelle-api) bereitgestellt.

Zu den funktionalitäten zählen bspw. Das Berechnen von Gravitationskräften oder Flugbahnen.

Diese Anforderung basiert auf der User Story:

1. [Als Spieleentwickler möchte ich auf grundlegende physikalische Funktionalitäten der Engine zugreifen, sodass ich diese beliebig verwenden kann.](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=41354193)

Der Aufwand für diese Anforderung wird auf **niedrig** geschätzt.

> :warning: **UI-Mockup(hat kein)** und **UML-Verhaltensdiagramm(e)** hinzufügen.

#### 2.5.1 Voraussetzungen

Damit die grundlegenden physikalischen Funktionalitäten verwendet werden können, muss die Videospielwelt (teilweise) erstellt worden und programmierbar sein.

#### 2.5.2 Nachbedingungen

N/A

## 3. Nichtfunktionale Anforderungen

Im Nachfolgenden sollen alle nichtfunktionalen Anforderungen für die Anwendung CrInGE dokumentiert werden.

### 3.1 Benutzerfreundlichkeit

#### 3.1.1 Objektinspektor

Der Objektinspektor realisiert die Bearbeitung der einzelenen Objekte (beschrieben in [2.2 Welteneditor](#22-welteneditor)) und soll als Popup-Fenster realisiert werden. Hierdurch soll verhindert werden, dass der Bearbeitungsbereich für die Videospielwelt verkleinert wird. Des Weiteren soll hierdurch das Gefühl des Entwicklers erleichtert werden.

Diese Anforderung basiert auf folgender User Story:  
[Als Spieleentwickler möchte ich eine möglichst große Weltendarstellung haben, um eine verbesserte Übersicht zu behalten](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=42338777)

#### 3.1.2 Objektgruppierung

Im [Welteneditor](#22-welteneditor) sollen Objekte gruppiert werden können, damit verschiedene Einstellungen einheitlich für alle Objekte einer solchen Gruppe getroffen werden können.

Diese Anforderung basiert auf folgender User Story:  
[Als Spieleentwickler möchte ich Blöcke gruppieren können, um deren Eigenschaften gleichzeitig zu bearbeiten](https://github.com/users/Christian-2003/projects/2/views/5?pane=issue&itemId=42294373)

<!--
Kategorien: Benutzerfreundlichkeit, Zuverlässigkeit, Leistung, Effizienz, Integrität, Wartbarkeit, Flexibilität, Testbarkeit, Wiederverwendbarkeit, Sicherheit.
-->

## 4. Technische Einschränkungen

Bei dieser Anwendung soll, soweit möglich, auf externe Bibliotheken verzichtet werden. Hiervon ausgenommen ist die Java Standardbibliothek. Des Weiteren behält sich das Entwicklungsteam vor, bei Bedarf und nach gründlicher Ausarbeitung einzelne externe Bibliotheken zu inkludieren, was entsprechend gekennzeichnet wird.
