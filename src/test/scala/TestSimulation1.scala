import java.util
import java.util.Calendar

import main.scala.CloudSimulation1.{createBroker, createDatacenter, createVmList}
import org.cloudbus.cloudsim.Vm
import org.cloudbus.cloudsim.core.CloudSim
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import org.slf4j.LoggerFactory


class TestSimulation1 extends AssertionsForJUnit {

  val logger = LoggerFactory.getLogger(classOf[TestSimulation1])

  @Before def Initialize(): Unit = {
    // First step: Initialize the CloudSim package. It should be called
    // before creating any entities.
    val num_user = 1
    // number of cloud users
    val calendar = Calendar.getInstance
    val trace_flag = false
    // Initialize the CloudSim library
    CloudSim.init(num_user, calendar, trace_flag)
  }

  @Test def verifyCISCreated(): Unit = {
    val cisId = CloudSim.getEntity(1)
    logger.debug("Verifying Cloud Information Service Created")
    //logger.debug("cis" + cisId)
    val cisClass = cisId.getClass+""
    //logger.debug("cisClass" + cisClass)
    assertTrue(cisClass.contains("org.cloudbus.cloudsim.core.CloudInformationService"))
  }


  @Test def verifyVmListCreated(): Unit = {
    val broker = createBroker
    val brokerId = broker.getId
    val (vmlist: util.ArrayList[Vm], pesNumber: Int, range: Range) = createVmList(brokerId)
    logger.debug("Verifying VmList Created")
    //logger.debug("VmList" + vmlist)
    val vmlistClass = vmlist.getClass+""
    //logger.debug("vmlistClass" + vmlistClass)
    assertTrue(vmlistClass.contains("java.util.ArrayList"))
  }

  @Test def verifyVmCount(): Unit = {
    val broker = createBroker
    val brokerId = broker.getId
    val (vmlist: util.ArrayList[Vm], pesNumber: Int, range: Range) = createVmList(brokerId)
    logger.debug("Verifying Vm Count")
    //logger.debug("VmList Count" + vmlist.size())
    val vmcount = vmlist.size()
    assertTrue(vmcount == 40)
  }

  @Test def verifyDatacenterCreated(): Unit = {
    val datacenter = createDatacenter("Datacenter1")
    logger.debug("Verifying Datacenter Created")
    //logger.debug("datacenter" + datacenter)
    val datacenterClass = datacenter.getClass+""
    //logger.debug("datacenterClass" + datacenterClass)
    assertTrue(datacenterClass.contains("org.cloudbus.cloudsim.Datacenter"))
  }

  @Test def verifyBrokerCreated(): Unit = {
    val broker = createBroker
    val brokerId = broker.getId
    logger.debug("Verifying Broker Created")
    //logger.debug("broker" + broker)
    val brokerClass = broker.getClass+""
    //logger.debug("brokerClass" + brokerClass)
    assertTrue(brokerClass.contains("org.cloudbus.cloudsim.DatacenterBroker"))
  }
}
