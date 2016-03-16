package twitter.streaming

import twitter.Util.TwitterUtils
import twitter.Util.TwitterUtils.Listener
import twitter4j._

/**
  * Created by shivam on 04/03/16.
  */
object TwitterStream {
    def twitterStream = new TwitterStreamFactory(TwitterUtils.config).getInstance()
//    twitterStream.addListener(new Listener())
//    twitterStream.sample()
//    Thread.sleep(TwitterUtils.sleepSeconds)
//    twitterStream.cleanUp()
//    twitterStream.shutdown()
}
