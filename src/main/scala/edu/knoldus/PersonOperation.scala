package edu.knoldus


import org.apache.log4j.Logger

import scala.util.Random

object PersonOperation {
  val log: Logger = Logger.getLogger(this.getClass)

  def doTask(person: Person): Response = {
    person match {
      case Gamer(_) => person.task()
      case Trainer() => person.task()
      case Blogger(_) => person.task()
    }
  }


  sealed abstract class Person() {
    def task(): Response
  }

  case class Gamer(name: String) extends Person {

    override def task(): Response = {

      def innerRoll(times: Int): String = {
        val startLimit = 1
        val endLimit = 6
        startLimit + Random.nextInt(endLimit).toString match {
          case "1" | "6" if times == 2 => "winner"
          case "1" | "6" => innerRoll(times + 1)
          case _ => "loser"
        }
      }

      Response(s"$name is a ${innerRoll(0)}")
    }
  }

  case class Trainer() extends Person {
    override def task(): Response = {
      val limit = 51
      Response(s"Today's attendance is ${Random.nextInt(limit)}")
    }
  }

  case class Blogger(blogList: Map[String, Int]) extends Person {

    override def task(): Response = {

      def blog(mapOfBlog: Map[String, Int], times: Int): Map[String, Int] = {

        if (times == 5) {
          val topic: String = selectTopic(mapOfBlog)
          val count: Option[Int] = mapOfBlog.get(topic)
          mapOfBlog ++ Map((topic, count.fold(0)(identity) + 1))
        }
        else {
          val topic: String = selectTopic(mapOfBlog)
          val count: Option[Int] = mapOfBlog.get(topic)
          log.info(mapOfBlog)
          blog(mapOfBlog ++ Map((topic, count.fold(0)(identity) + 1)), times + 1)
        }
      }

      val res = blog(blogList, 1)
      log.info(res)
      Response(s" Favourite topic is ${getFavourite(res)}")
    }

    def selectTopic(mapOfBlog: Map[String, Int]): String = {
      val topics: List[String] = mapOfBlog.keySet.toList
      val res = topics(Random.nextInt(topics.length))
      log.info(res)
      res
    }

    def getFavourite(mapOfBlog: Map[String, Int]): String = {
      val max: Int = mapOfBlog.valuesIterator.max

      def innerFavourite(inputList: List[(String, Int)]): String = {
        inputList match {
          case Nil => "not valid"
          case head :: _ if head._2 == max => head._1
          case _ :: tail => innerFavourite(tail)
        }
      }

      val res = innerFavourite(mapOfBlog.toList)
      log.info(res)
      res

    }

  }

  case class Response(message: String)


}
