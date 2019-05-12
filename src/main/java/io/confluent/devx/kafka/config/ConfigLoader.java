package io.confluent.devx.kafka.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigLoader {

  // use ccloud config 
  private static final String DEFAULT_CONFIG_FILE =
      System.getProperty("user.home") + File.separator + ".ccloud" + File.separator + "config";

  static Properties loadConfig() {
    log.info("Path isn't specified. Using Confluent Cloud ☁ config from default location");
    return loadConfig(DEFAULT_CONFIG_FILE);
  }

  public static Properties loadConfig(final String configFile) {
    if (!Files.exists(Paths.get(configFile))) {
      throw new RuntimeException(configFile
                                 + " does not exist.\nYou need a file with client configuration, either create one or run `ccloud init` if you are a Confluent Cloud ☁ user");

    }
    log.info("Loading configs from: {}", configFile);
    final Properties cfg = new Properties();
    try (InputStream inputStream = new FileInputStream(configFile)) {
      cfg.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return cfg;
  }
}
