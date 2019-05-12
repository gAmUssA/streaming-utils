package io.confluent.devx.kafka.config

import spock.lang.Specification

class ConfigLoaderTest extends Specification {

  def "LoadConfig should throw exception"() {

    when:
    ConfigLoader.loadConfig(fileName)

    then:
    def error = thrown(expectedException)
    error.message == expectedMessage

    where:
    fileName | expectedException | expectedMessage
    'blah'   | RuntimeException  | """${fileName} does not exist.
You need a file with client configuration, either create one or run `ccloud init` if you are a Confluent Cloud user"""
  }
}
