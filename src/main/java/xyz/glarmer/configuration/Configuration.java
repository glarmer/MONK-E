package xyz.glarmer.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Configuration {
    HashMap<String, Option> options = new HashMap<String, Option>();
    String path;
    File configurationFile;

    public Configuration(String path)
    {
        this.path = path;

        Option<String> botTokenOption = new Option<>("BOT_TOKEN", "Your bot token from the Discord developer portal", "discordToken");
        options.put("BOT_TOKEN", botTokenOption);
        loadOrCreateConfiguration();


    }

    public void loadOrCreateConfiguration()
    {
        configurationFile = new File(this.path);
        try (Scanner reader = new Scanner(configurationFile)) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if (data.contains("=")) {
                    String optionName = data.split("=")[0];
                    String optionValue = data.split("=")[1];


                    System.out.println(optionName + ": " + optionValue);
                    options.get(optionName).value = optionValue;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No configuration file found, creating configuration");
            createConfiguration();
        }
    }

    private void createConfiguration()
    {
        try {
            configurationFile = new File(this.path); // Create File object
            if (configurationFile.createNewFile()) {
                System.out.println("File created: " + configurationFile.getName());
                System.out.println("Populating new configuration file...");

                FileWriter writer = new FileWriter(this.path);

                for (Option option : options.values()) {
                    writer.write("# " + option.description + "\n");
                    writer.write(option.name + "=" + option.defaultValue);
                    writer.write("\n");
                }
                writer.close();
                System.out.println("Successfully populated the configuration file.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the configuration file.");
            e.printStackTrace();
        }
    }

    public Option getOption(String optionName) {
        return options.get(optionName);
    }
}
