RuntimeExceptionApplication2 (r2)
-

Die r2 ist eine Erweiterung der RuntimeExceptionApplication um die Module CustomActivityOnCrash, StateMachine und ConfigParser.
Mit der r2 wird demonstriert, dass der in der Bachelorarbeit entworfene Ansatz CAOC Bibliothek mit Stacktrace-Analyse funktioniert, wenn der UncaughtExceptionHandler korrekt aufgerufen wird. Dazu muss man die App außerhalb von AVARE ausführen.

Gibt man zum Beispiel das Wort "Test" als Nachricht der geworfenen Exception an, kann man unter "ERROR DETAILS" nachlesen, dass es einen Absturz aufgrund des Mikrofon Hooks gab. Des Weiteren kann man in der JSON Datei sehen, dass die Interventionsstufe korrekt erhöht wurde.  

Hinweis: Der Zugriff auf den Speicher muss freigegeben sein und die Testdatei exampleSettingsR2.json verwendet werden.
