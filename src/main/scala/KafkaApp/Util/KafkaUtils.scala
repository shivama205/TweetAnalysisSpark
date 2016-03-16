package KafkaApp.Util

import java.util.Properties

import com.typesafe.config.ConfigFactory
import kafka.javaapi.producer.Producer
import kafka.producer.ProducerConfig

/**
  * Created by shivam on 08/03/16.
  */
object KafkaUtils {
  private val conf = ConfigFactory.load()

  val getProducerConfig: ProducerConfig = {
      val props = new Properties()
      props.put("metadata.broker.list", conf.getString("kafka.broker.list"))
      props.put("serializer.class", "kafka.serializer.StringEncoder")
      props.put("request.required.acks", "1")
      new ProducerConfig(props)
    }

  val getTopic: String = {
    conf.getString("kafka.topic.name")
  }

}
