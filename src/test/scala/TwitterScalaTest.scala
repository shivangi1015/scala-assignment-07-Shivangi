package com.knoldus

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class TwitterScalaTest extends FunSuite {

  val tweetsObj = new TwitterScala
  val noOfTweets = Await.result(tweetsObj.searchingQuery("#scala"), 6.second)

  test("Testing whether tweets are fetched or not?") {
    assert(noOfTweets > 0)
  }

  test("Testing number of tweets") {
    assert(noOfTweets == 100)
  }
  test("Testing average number of retweets") {
    assert((Await.result(tweetsObj.calculateRetweets("#scala"), 6.second)) / 100 == 2)
  }

  test("Testing average number of likes") {
    assert((Await.result(tweetsObj.calculateLikes("#scala"), 6.second)) / 100 == 0)
  }

}


