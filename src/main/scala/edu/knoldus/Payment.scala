package edu.knoldus


object Payment {

  def paymentService(mode: String,payment: Double): Double = {
    mode.toLowerCase match {
      case "pay tm"|"free charge" => payment + (0.02 * payment)
      case "net banking" => payment + 5
      case "card payment" => payment + 1.5
      case "cash" => payment
    }
  }

}


