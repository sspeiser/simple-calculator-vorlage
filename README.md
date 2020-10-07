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

Über Menü File . Import . Existing Maven Project

Eclipse-Dateien in .gitignore hinzufügen
```
.classpath
.project
.settings
```
Dann wieder hinzufügen per git add, git commit und git push.

## User Story annehmen und Spezifikation anlegen

In Jira die User Story auswählen. In Jira/Confluence/... eine Spezifikation entwickeln.

Beispielsweise der Given-Then-Then-Logik folgen: https://martinfowler.com/bliki/GivenWhenThen.html

Für Story einen neuen Branch anlegen. FEATURE-NAME durch entsprechenden Identifier nach Ihrem im Projekt-Team festgelegten Schema ersetzen.
```
$ git branch FEATURE-NAME
$ git checkout FEATURE-NAME
$ git push --set-upstream origin FEATURE-NAME
```

Test entwickeln mit JUnit.

Implementierung schreiben.

Mit mvn verify lokal testen. Implementieren bis alle Tests erfolgreich sind.
Dann per git add/commit/push zu GitHub schicken.

##  Pull Request und Code Review

Auf github.com kann ein Pull Request erstellt werden - also ein Wunsch die Änderungen aus dem Feature-Branch in den Master-Branch zu integrieren.

Hier kann nun eine Diskussion und Feedback aus dem Code Review abgelegt werden.

Siehe: https://guides.github.com/introduction/flow/

## Continuous Integration

Auf github.com können Sie im Reiter "Actions" einen Continuous Integration Workflow anlegen. Java mit Maven steht schon als Template zur Verfügung.

Bei Erstellung des Workflows und auch ggfs. Änderungen an Ihrer pom.xml darauf achten in welchem Branch Sie sich befinden.

In Ihrem lokalen Repository im Feature-Branch können Sie die Änderungen aus dem Master-Branch integrieren:
```
$ git pull
...
$ git merge origin/master
...
```

Falls es Merge-Konflikte gibt, dann bearbeiten Sie die entsprechenden Dateien manuell und fügen Sie dann per git add wieder hinzu.

## Code Quality mit Sonar Cloud

Sonar Cloud können Sie entsprechend der Anweisungen auf https://sonarcloud.io einrichten. Sie können sich mit GitHub einloggen. Für Public Repositories ist der Dienst kostenlos.

Damit Code Test Coverage funktioniert müssen wir aus der pom.xml das PluginManagement-Tag entfernen und folgendes hinzufügen:
```
    <plugin>
      <groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<version>0.8.6</version>
				<executions>
					<execution>
						<id>default-prepare-agent</id>
						<goals>
							<goal>prepare-agent</goal>
						</goals>
					</execution>
					<execution>
						<id>report</id>
						<goals>
							<goal>report</goal>
						</goals>
						<phase>verify</phase>
					</execution>
				</executions>
			</plugin>
```

Auch hier müssen Sie die Änderungen aus dem Master wieder in Ihren Feature-Branch mergen - siehe oben.


## User Acceptance Test für Swing GUI mit Selenium und JavaDriver

Für das GUI-Testing von Swing GUIs mit dem Selenium Framework, das für Web-Oberflächen gängig ist müssen wir Marathon (https://marathontesting.com/javadriver/) hinzufügen. Dazu muss in die pom.xml:
```
    <dependency>
			<groupId>com.jaliansystems</groupId>
			<artifactId>marathon-java-driver</artifactId>
			<version>5.4.0.0</version>
		</dependency>
```

Die Unterteilung von Unit Tests und Integration Tests können wir über folgende Konfiguration des Surefire Plugins erreichen (pom.xml):
```
     <plugin>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.22.1</version>
				<executions>
					<execution>
						<phase>integration-test</phase>
						<goals>
							<goal>test</goal>
						</goals>
						<configuration>
							<excludes>
								<exclude>none</exclude>
							</excludes>
							<includes>
								<include>**/*IT</include>
							</includes>
						</configuration>
					</execution>
				</executions>
			</plugin>
```
Dadurch werden alle Tests, die mit IT im Klassennamen enden in der Test-Phase ignoriert (Standardverhalten) und dann aber in der verify Phase ausgeführt.

GitHub Actions braucht eine virtuelle graphische Oberfläche, um die GUI Tests auszuführen.
Dazu passen wir den Build and analyze Step wie folgt an:
```
      - name: Install xvfb
        run: sudo apt-get install xvfb
      - name: Build and analyze
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}  # Needed to get PR information, if any
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: xvfb-run --auto-servernum mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar
```

## Implementierung des Tests

Der GUI Test wird mit IT im Namen angelegt.

Nützliche Links:
- Einführungsdokument vom Hersteller: https://marathontesting.com/blog-posts/unit-testing-swing-apps/
- Konferenzpräsentation: https://confengine.com/selenium-conf-2016/proposal/2532/java-swing-java-fx-application-testing-using-selenium-webdriver

Es empfiehlt sich mit PageObjects zu arbeiten: https://martinfowler.com/bliki/PageObject.html

Selenium Doku zu PageObjects: https://www.selenium.dev/documentation/en/guidelines_and_recommendations/page_object_models/

Weiterführende Tipps für UI Testing: https://www.blazemeter.com/blog/top-15-ui-test-automation-best-practices-you-should-follow

## Implementierung GUI

Implementierung der GUI und lokales testen. 
Wenn mvn verify lokal erfolgreich ist, dann Code auf GitHub pushen und Pull Request bearbeiten (Code Review, Diskussion, etc.).

Wenn Pull Request akzeptiert wird, dann auf github.com in den Master mergen.
