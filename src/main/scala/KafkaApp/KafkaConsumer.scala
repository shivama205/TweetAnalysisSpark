package KafkaApp

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.streaming.kafka.KafkaUtils
import twitter4j.JSONObject

/**
  * Created by shivam on 10/03/16.
  */
object KafkaConsumer {
  def main(args: Array[String]) {

    val zkQuorum = "localhost:2181"
    val groupId = "firstGroup"
    val topics = List("test")
    val numThreads = 1


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("TweetSparkAnalysis")
    val ssc = new StreamingContext(sparkConf, Seconds(1))

    val topicMap = topics.map((_, numThreads)).toMap

    val directKafkaStream = KafkaUtils.createStream(ssc, zkQuorum, groupId, topicMap)
    directKafkaStream.foreachRDD((rdd, time) => {
      if (!rdd.isEmpty()) {
        rdd.flatMap(_.toString()).saveAsTextFile("/Users/shivam/Desktop/Kafka/")
      }
    })

    ssc.start()
    ssc.awaitTermination()
  }

}
