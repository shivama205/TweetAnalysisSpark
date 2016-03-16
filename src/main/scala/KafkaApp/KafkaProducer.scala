package KafkaApp

import KafkaApp.Util.KafkaUtils
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import org.apache.kafka.clients.producer.ProducerRecord
import twitter.Util.TwitterUtils
import twitter.streaming.TwitterStream
import twitter4j.Status

import scala.util.Random

/**
  * Created by shivam on 08/03/16.
  */
object KafkaProducer {
  val producer = new Producer[String, String](KafkaUtils.getProducerConfig)

  def main(args: Array[String]): Unit = {
    val twitterStream = TwitterStream.twitterStream
    twitterStream.addListener(new TwitterUtils.Listener(sendToKafka))
    twitterStream.sample()
  }

  def sendToKafka(status: Status): Unit = {
//    (1 to 1).foreach {
//      messageNum =>
//        val str = (1 to 10).map(x => Random.nextInt(10).toString).mkString(" ")
//        val message = new KeyedMessage[String, Array[Byte]]("test", str.getBytes())
//        producer.send(message)
//    }

    println(status.getUser.getName + " => " + status.getText)
    val msg = new KeyedMessage[String, String](KafkaUtils.getTopic, status.getUser.getName + " => " + status.getText)
    producer.send(msg)
  }
}
