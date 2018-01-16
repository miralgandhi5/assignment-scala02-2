package edu.knoldus

import edu.knoldus.PersonOperation.{Blogger, Gamer, Trainer}
import org.apache.log4j.Logger

object Application extends App{
  val log = Logger.getLogger(this.getClass)
  val payment =1000
  val mode ="pay tm"
  val blogger = Blogger(Map[String,Int]("Scala" -> 2, "Java" -> 5, "Kafka" -> 6))
  val trainer = Trainer()
  val gamer = Gamer("miral")
  log.info(s"Payment to be made for bill of $payment is ${Payment.paymentService(mode,payment)}")
  log.info(s"${blogger.blogList} before writing blog")
  log.info(PersonOperation.doTask(blogger))
  log.info(s"${blogger.blogList} after writing blog")
  log.info(PersonOperation.doTask(gamer))
  log.info(PersonOperation.doTask(trainer))



}
