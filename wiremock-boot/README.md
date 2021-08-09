# wiremock-boot

The application uses a standalone Wiremock server, that needs to be started manually.
Run the Wiremock jar from wiremock-boot/standalone directory.

```bash
java -jar wiremock-jre8-standalone-2.29.1.jar
```
The application will use the mappings from the 'mappings' folder.

The tests are using Wiremock as an imported library to show the functionality. In order to run them the standalone process needs to be stopped.