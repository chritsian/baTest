StateMachineNextTest
------

Es wurde getestet, ob die next Methoden der Klasse StateMachine funktionieren. Des Weitern wurde die Einbindung als Modul getestet, sowie der ConfigParser.
Zum Testen wurde die JSON Datei exampleSettingsStateNext.json verwendet.

Nach Klicken des in der TestApp angezeigten Buttons wurde folgende Ausgabe gelogged:

2019-04-24 14:01:03.964 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: -
2019-04-24 14:01:03.964 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: 1BLOCKED
2019-04-24 14:01:04.001 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: 2NO_SOUND
2019-04-24 14:01:04.010 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: 3NEUTRAL_SOUND
2019-04-24 14:01:04.010 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: 1BLOCKED
2019-04-24 14:01:04.016 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: 2BLACK_PICTURE
2019-04-24 14:01:04.022 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: 3NEUTRAL_PICTURE
2019-04-24 14:01:04.022 31016-31016/com.example.stateMachineTest D/STATE_MACHINE_TEST: -

Dies entsprach der erwarteten Ausgabe und der Test war erfolgreich.

