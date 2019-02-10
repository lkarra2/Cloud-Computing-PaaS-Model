package main.scala

import org.cloudbus.cloudsim.{Cloudlet, DatacenterCharacteristics}

class CostCalculation {
  var cost = 0.0

  def monthlyCost(characteristics: DatacenterCharacteristics, ram: Int, storage: Int, bw: Int ): Double = {
    cost = (characteristics.getCostPerBw*bw + characteristics.getCostPerMem*ram + characteristics.getCostPerStorage*storage)*2000000
    cost
  }

  def getCost: Double = {
    cost
  }

}
