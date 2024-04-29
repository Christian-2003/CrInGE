In diesem Dokument sollen Pläne für den weiteren Verlauf des Projektes festgehalten werden.  

1. [Abschluss Demo](#abschluss-demo)
2. [Testplan](#testplan)

# Abschluss Demo
Im folgenden werden Features gelistet, welche in der Demo zum Ende des Semesters enthalten sein sollen.

### Geplant
- Map
- Spieler kann sich bewegen oder Map beweget sich hinter dem Spieler oder beides
- Events (Monster töten oder Spieler töten an Ort...)
- Kollision
- Turret
- Verknüpfung Engine zu Editor

### Optional
- Musik
- Online Multiplayer mit PvP und KOOP
- custom scripts
- OpenGL

# Testplan
Im folgendem werden die Pläne in bezug auf Tests festgelegt.

### Umfang der Tests
Wir werden ausschließlich Unit Tests verwenden.  
Dabei soll auf korrekte Funktion geprüft werden.  
Auf Methoden, wie Integrationstest, Validierungstests und Systemtests wird verzichtet.

### Testabdeckung
Die Testabdeckung soll einen Wert von circa 50% erreichen.

### Testwerkzeug
Zur Durchführung der Unittests wird JUnit verwendet.  
Zur Ermittlung der Testabdeckung steht noch kein Werkzeug fest.

### Verwaltung der Testfälle
Die Tests werden ebenso wie der Quellcode im Git abgelegt.  
Jeder Push in master soll alle Testfälle erfüllen.
