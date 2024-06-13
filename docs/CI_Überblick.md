Seit 20.04.2024 ist die CI Pipeline in Git Hub eingerichtet und berichtet via eines 🏷️ Status badge in der [README.md](https://github.com/Christian-2003/CrInGE/blob/master/README.md) den momentanen Stand der Tests.

Die Funktionalität der Pipeline beschränkt sich auf das 🏗️ Bauen und 🧪 Testen der Anwendung.
Hierdurch soll die ⚙️ Operabilität des Systems nach jedem Push gewährleistet werden.
Dies wird mittels `mvn -B compile --file pom.xml` und `mvn test --file pom.xml` auf der neusten windows Version mit Maven getestet.
