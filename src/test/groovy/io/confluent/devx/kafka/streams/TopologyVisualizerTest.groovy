package io.confluent.devx.kafka.streams

import groovy.util.slurpersupport.GPathResult
import groovyx.net.http.HTTPBuilder
import spock.lang.Shared
import spock.lang.Specification

import static groovyx.net.http.ContentType.HTML
import static groovyx.net.http.ContentType.TEXT
import static io.confluent.devx.kafka.streams.TopologyVisualizer.topologyStringToBase64String

class TopologyVisualizerTest extends Specification {

  @Shared
  static HTTPBuilder http

  def myTopology = TopologyVisualizer.WORDCOUNT_TOPOLOGY

  def expectedString = """VG9wb2xvZ2llczoKICAgU3ViLXRvcG9sb2d5OiAwCiAgICBTb3VyY2U6IFNvdXJjZSAodG9waWNzOiBbc3RyZWFtcy1wbGFpbnRleHQtaW5wdXRdKQogICAgICAtLT4gUHJvY2VzcwogICAgUHJvY2Vzc29yOiBQcm9jZXNzIChzdG9yZXM6IFtDb3VudHNdKQogICAgICAtLT4gU2luawogICAgICA8LS0gU291cmNlCiAgICBTaW5rOiBTaW5rICh0b3BpYzogc3RyZWFtcy13b3JkY291bnQtcHJvY2Vzc29yLW91dHB1dCkKICAgICAgPC0tIFByb2Nlc3MK"""

  def "TopologyStringToBase64String should convert topology to base64 string"() {
    when:
    def base64 = topologyStringToBase64String(myTopology)
    then:
    base64 == expectedString
  }

  def "visualize should get full url"() {
    when:
    http = new HTTPBuilder(TopologyVisualizer.visualize(myTopology))
    def (html, respStatus) =
    http.get(contentType: HTML) { response, reader ->
      [reader, response.status]
    }

    then:
    respStatus == 200
    html instanceof GPathResult
    html.BODY.H2.text() == 'Kafka Streams Topology Visualizer'
  }


}