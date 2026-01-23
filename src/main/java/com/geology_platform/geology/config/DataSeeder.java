package com.geology_platform.geology.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Automatically runs the populate_db.py script on application startup.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // We run this in a separate thread to avoid blocking the main application startup
        // and to ensure the server is ready to accept requests.
        new Thread(() -> {
            try {
                File scriptFile = new File("populate_db.py");
                if (!scriptFile.exists()) {
                    System.out.println("⚠️ populate_db.py not found in project root. Skipping data population.");
                    return;
                }

                System.out.println("🚀 Starting Data Population Script...");

                // Give the server a moment to ensure it's fully ready to accept connections
                Thread.sleep(3000);

                // Build the process to run python
                // Note: Assumes 'python' is in your system PATH. Use "python3" if on Linux/Mac.
                ProcessBuilder processBuilder = new ProcessBuilder("python", "populate_db.py");
                processBuilder.redirectErrorStream(true); // Merge stderr into stdout
                
                Process process = processBuilder.start();

                // Read the output from the script and print it to the Java console
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("[POPULATE-DB] " + line);
                    }
                }

                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("✅ Data Population Completed Successfully.");
                } else {
                    System.out.println("❌ Data Population Failed with exit code: " + exitCode);
                }

            } catch (Exception e) {
                System.err.println("❌ Error running data population script: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}
