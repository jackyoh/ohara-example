package idv.jack.ohara;

import com.island.ohara.common.data.Serializer;
import com.island.ohara.kafka.Consumer;

import java.util.List;
import java.util.NoSuchElementException;

public class OharaConsoleConsumer {
  public static void main(String args[]) {
    String topicName = System.getenv("TOPIC_NAME");
    String brokerURL = System.getenv("BROKER_URL");
    if (topicName == null || brokerURL == null) {
      throw new NoSuchElementException("Please set TOPIC_NAME and BROKER_URL environment variable.");
    }

    Consumer consumer = Consumer
      .builder()
      .topicName(topicName)
      .offsetFromBegin()
      .connectionProps(brokerURL)
      .build(Serializer.ROW, Serializer.BYTES);

    List<Consumer.Record> record = consumer.poll(java.time.Duration.ofSeconds(30), 1);
    record.forEach(x -> System.out.println(x.key()));
  }
}
