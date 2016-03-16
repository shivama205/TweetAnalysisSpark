package twitter.Util

import com.typesafe.config.ConfigFactory
import twitter4j.{StallWarning, StatusDeletionNotice, Status, StatusListener}
import twitter4j.conf.{Configuration, ConfigurationBuilder}

/**
  * Created by shivam on 08/03/16.
  */
object TwitterUtils {
  private val conf = ConfigFactory.load()

  val config: Configuration = new ConfigurationBuilder()
      .setOAuthConsumerKey(conf.getString("twitter.OAuth.consumerKey"))
      .setOAuthConsumerSecret(conf.getString("twitter.OAuth.consumerSecret"))
      .setOAuthAccessToken(conf.getString("twitter.OAuth.accessToken"))
      .setOAuthAccessTokenSecret(conf.getString("twitter.OAuth.accessTokenSecret"))
      .build()

  val sleepSeconds: Long = conf.getLong("twitter.sleep.duration")

  class Listener(send:Status => Unit) extends StatusListener {
    override def onStallWarning(warning: StallWarning): Unit = {}

    override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {}

    override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {}

    override def onStatus(status: Status): Unit = { send(status) }

    override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}

    override def onException(ex: Exception): Unit = throw ex
  }

}
