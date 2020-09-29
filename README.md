# Simple Calculator

Ein einfacher Taschenrechner, um als Projekttemplate zu dienen.

## Erstelle Java Projekt mit Maven

Nach der initialen Erstellung des Projekts haben wir bisher nur die Lizenzinformationen und die README.md Datei angepasst.
Nun wollen wir ein Java-Projekt erstellen, das sich per Maven kompilieren lässt.

Dazu verwenden wir eine Vorlage von Maven für ein einfaches Java-Projekt. Im foglenden artifactid, package und ggfs. organizationid an das eigene Projekt/Team anpassen:
```
$ mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart
...
Define value for property 'groupId': de.hftstuttgart
Define value for property 'artifactId': simplecalc
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' de.hftstuttgart: : de.hftstuttgart.simplecalc
Confirm properties configuration:
...
 Y: : Y
```

Anzeigen der generierten Dateien:
```
$ tree
.
├── LICENSE
├── README.md
└── simplecalc
    ├── pom.xml
    └── src
        ├── main
        │   └── java
        │       └── de
        │           └── hftstuttgart
        │               └── simplecalc
        │                   └── App.java
        └── test
            └── java
                └── de
                    └── hftstuttgart
                        └── simplecalc
                            └── AppTest.java
```

Das Unterverzeichnis mit Namen der artifactid legt Maven automatisch an, aber das brauchen wir nicht:
```
$ mv simplecalc/* .
$ rmdir simplecalc
```

Dann machen wir noch ein Update auf Java 11, indem wir in der Datei pom.xml in den Tags maven.compiler.source und maven.compiler.target die Version 1.7 durch 11 ersetzen.

Nun können wir das Projekt compilieren und "packagen", d.h. eine Jar-Datei erstellen:
```
$ mvn package
...
$ java -jar target/simplecalc-1.0-SNAPSHOT.jar
no main manifest attribute, in target/simplecalc-1.0-SNAPSHOT.jar
```
Das Jar wurde erfolgreich gebaut aber es wurde keine Main-Klasse angegeben. Auch das können wir in der pom.xml angeben. Wir fügen im Tag plugin mit der artifactId maven-jar-plugin folgende Zeilen ein:
```
<configuration>
  <archive>
	  <manifest>
		  <mainClass>de.hftstuttgart.simplecalc.App</mainClass>
		</manifest>
	</archive>
</configuration>
```

Danach können wir eine ausführbare Jar machen:
```
$ mvn package
...
$ java -jar target/simplecalc-1.0-SNAPSHOT.jar
Hello World!
```

Mit git status sehen wir das die folgenden Dateien noch nicht getracked werden:
- pom.xml: die Maven Projektkonfiguration, diese wollen wir hinzufügen
- src/: Hier sind die Quellcodes, diese wollen wir auch hinzufügen
- target/: Hier kommt alles rein, was Maven erzeugt, z.B. Jar-Dateien, das wollen wir nicht zu git hinzufügen

Wir fügen eine Zeile mit target in die .gitignore Datei an. Danach wird target bei git status nicht mehr angezeigt. 
Wir fügen pom.xml und src hinzu, erstellen einen commit und pushen es an GitHub:
```
$ git add pom.xml
$ git add src
$ git add .gitignore
$ git commit -m 'Initial creation of Java project'
$ git push
```

## Java Projekt mit Eclipse öffnen


## User Story annehmen und Spezifikation anlegen

Given When Then ...

## Calculator Engine entwickeln

test schreiben

funktion schreiben mit vielen Zeilen

git ignore anpassen zwecks Eclipse

##  Pull Request

Kommentare. Tests nicht ausreichend.


## Continuous Integration

GitHub Actions

Build

Publish Test results Plugin

## Code Quality mit Sonar Cloud

SonarCloud

Jacoco Test coverage

GitHub Actions

## Accept Pull Request für Calculator Funktion

...


## User Acceptance Test für Swing GUI mit Selenium und JavaDriver

Maven Dependency

Surefire Konfiguration

GitHub Actions xvfb

commit / push

## Implementierung des Tests

Branch Anlegen

TestAnlegen mit IT im Namen

PageObject

AcceptanceUserAddsNumbersIT

... Fail ...

## Implementierung GUI

CalculatorWindow und Main Funktion mit Swing

Lokal testen und dann auch 
mvn verify

Dann Pull request

## Ggfs. Release und JAR

