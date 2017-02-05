package com.knoldus

import twitter4j.{Query, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TwitterScala {


  // config work to create a twitter object
  val configurationBuilder = new ConfigurationBuilder
  configurationBuilder.setDebugEnabled(true)
    .setOAuthConsumerKey("consumerKey")
    .setOAuthConsumerSecret("ConsumerSecret")
    .setOAuthAccessToken("AccessToken")
    .setOAuthAccessTokenSecret("AccessToken")
  val twitterfac = new TwitterFactory(configurationBuilder.build)
  val twitter = twitterfac.getInstance

  
  /*
      Method to Search tweets by particular query
   */
  val max = 100

  def searchingQuery(queryString: String): Future[Int] = Future {

    val query = new Query(queryString)
    query.setCount(max)
    val listOfQuery = twitter.search(query)
    val tweets = listOfQuery.getTweets.toList
    tweets.length
  }

  /*
     *  Method to calculate total Likes on particular query
     */
  def calculateLikes(queryString: String): Future[Int] = Future {

    val query = new Query(queryString)
    query.setCount(max)
    val listOfqueries = twitter.search(query)
    val tweets = listOfqueries.getTweets.toList
    val numberOfLikes = tweets.map(s => s.getFavoriteCount)
    numberOfLikes.sum
  }

  /**
    * calculating total retweets on particular query
    */
  def calculateRetweets(queryString: String): Future[Int] = Future {

    val query = new Query(queryString)
    query.setCount(max)
    val listOfQuery = twitter.search(query)
    val tweets = listOfQuery.getTweets.toList
    val numberOfRetweets = tweets.map(s => s.getRetweetCount)
    numberOfRetweets.sum
  }
}

