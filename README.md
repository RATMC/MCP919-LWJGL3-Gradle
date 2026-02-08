# MCP919-LWJGL3-Gradle

## Credits
- Thanks to [LofizKittyCat](https://github.com/LofizKittyCat/GradleMCPBase) for the original GradleMCPBase project
- Thanks to [EldoDebug](https://github.com/EldoDebug/Lwjgl-Fusion) for the LWJGL 3 integration
- Credits to Markelectro for the marCloud repo

## Building shadowJar

To build a shadowJar (fat jar) that includes all dependencies, run the following command:

```bash
./gradlew shadowJar
```

The resulting jar will be located in the `build/libs` directory with the name `MCP919-LWJGL3-1.8.9-all.jar`.

## Running the Application

### Using Gradle task
```bash
./gradlew Run
```

### Using Java directly
After building the shadowJar, you can run it with:
```bash
java -jar build/libs/MCP919-LWJGL3-1.8.9-all.jar
```

## Notes

- This project requires Java 8 to build and run
- Make sure you have the JDK 8 installed and configured properly
