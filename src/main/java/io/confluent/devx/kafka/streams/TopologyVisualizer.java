package io.confluent.devx.kafka.streams;

import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TopologyVisualizer {

  public static final String DEFAULT_KAFKA_STREAMS_VIZ_URL = "https://zz85.github.io/kafka-streams-viz/";
  protected static final String WORDCOUNT_TOPOLOGY = "Topologies:\n"
                                                     + "   Sub-topology: 0\n"
                                                     + "    Source: Source (topics: [streams-plaintext-input])\n"
                                                     + "      --> Process\n"
                                                     + "    Processor: Process (stores: [Counts])\n"
                                                     + "      --> Sink\n"
                                                     + "      <-- Source\n"
                                                     + "    Sink: Sink (topic: streams-wordcount-processor-output)\n"
                                                     + "      <-- Process\n";

  public static String topologyStringToBase64String(String topology) {
    return Base64.getEncoder().encodeToString(topology.getBytes());
  }

  public static String visualize(String topology) {
    final String s = DEFAULT_KAFKA_STREAMS_VIZ_URL + "#" + topologyStringToBase64String(topology);
    log.debug("Copy/Paste URL to the browser to see visualized topology: \n{}", s);
    return s;
  }
  
}
