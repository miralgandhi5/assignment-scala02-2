package edu.knoldus

import edu.knoldus.PersonOperation.{Blogger, Gamer, Trainer}
import org.apache.log4j.Logger

object Application extends App{
  val log = Logger.getLogger(this.getClass)
  val payment =1000
  val mode ="pay tm"
  val blogger = Blogger(Map[String,Int]("scala" -> 2, "java" -> 5, "kafka" -> 6))
  val trainer = Trainer()
  val gamer = Gamer("miral")
  log.info(s"Payment to be made for bill of $payment is ${Payment.paymentService(mode,payment)}\n")
  log.info(s"${blogger.blogList} before writing blog\n")
  log.info(s" Blogger ${PersonOperation.doTask(blogger)}\n")
  log.info(s" Gamer ${PersonOperation.doTask(gamer)}\n")
  log.info(s" Trainer ${PersonOperation.doTask(trainer)}\n")



}
