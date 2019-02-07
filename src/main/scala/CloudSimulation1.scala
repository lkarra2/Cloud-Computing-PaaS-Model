package main.java

/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation
 *               of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009, The University of Melbourne, Australia
 *
   1. One Datacenter:
   		Four Host,
  			Four cores (with 1000 mips each core),
   			8 GB RAM,
   			100 GB storage (i.e. 100000 MB),
   			1 mbps (i.e. 8000 Kbits/s network bandwidth measured as Kbits/s)
	2. One DataCenterBroker
	3. CloudLets:
		40 Cloudlets/tasks/workload,
		40000 length of instructions,
		300 kb input filesize,
		400 kb output filesize,
		quad-core cpu
		utilization model to full
	4. Virtual Machines:
		40 Virtual machines,
		20 GB Storage disk,
		2 GB RAM,
		1 vCPU with 1000 mips CPU speed,
		1000 kbits/s Bandwidth,
		Timeshared scheduler for cloudlets execution
 */

import org.cloudbus.cloudsim._
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple
import java.text.DecimalFormat
import java.util
import java.util.{ArrayList, Calendar, LinkedList, List}

import com.typesafe.config.{Config, ConfigFactory}


/**
  * A simple example showing how to create a datacenter with one host and run one
  * cloudlet on it.
  */
object CloudSimulation1 {
  /** The cloudlet list. */
    private var cloudletList = null
  /** The vmlist. */
  private var vmlist = null

  //Create instance of config
  val config: Config = ConfigFactory.load("settings.conf")

  /**
    * Creates main() to run this example.
    *
    * @param args the args
    */
  @SuppressWarnings(Array("unused")) def main(args: Array[String]): Unit = {
    Log.printLine("Starting CloudSimExample1...")
    try { // First step: Initialize the CloudSim package. It should be called
      // before creating any entities.
      val num_user = config.getInt("setting.numuser")
      // number of cloud users
      val calendar = Calendar.getInstance
      val trace_flag = config.getBoolean("setting.traceflag") // mean trace events
      // Initialize the CloudSim library
      CloudSim.init(num_user, calendar, trace_flag)
      // Second step: Create Datacenters
      // Datacenters are the resource providers in CloudSim. We need at
      // list one of them to run a CloudSim simulation
      val datacenter0 = createDatacenter("Datacenter_0")
      // Third step: Create Broker
      val broker = createBroker
      val brokerId = broker.getId
      // Fourth step: Create one virtual machine
      val vmlist = new util.ArrayList[Vm]
      // VM description
      val vmid = config.getInt("vm.vmid")
      val mips = config.getInt("vm.mips")
      val size = config.getInt("vm.size")
      // image size (MB)
      val ram = config.getInt("vm.ram")
      // vm memory (MB)
      val bw = config.getInt("vm.bw")
      val pesNumber = config.getInt("vm.pesNumber")
      // number of cpus
      val vmm = config.getString("vm.vmm")
      // VMM name
      // create VM
      var vmId = 0
      while (vmId < 40) {
        val vm = new Vm(vmId, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared)
        // add the VM to the vmList
        vmlist.add(vm)

        {
          vmId += 1; vmId - 1
        }
      }
      // submit vm list to the broker
      broker.submitVmList(vmlist)
      // Fifth step: Create one Cloudlet
      val cloudletList = new util.ArrayList[Cloudlet]
      // Cloudlet properties
      val length = config.getInt("cloudlet.length")
      val fileSize = config.getInt("cloudlet.fileSize")
      val outputSize = config.getInt("cloudlet.outputSize")
      var cloudletId = config.getInt("cloudlet.cloudletId")
      val utilizationModel = new UtilizationModelFull
      while ( cloudletId < 40) {
        val cloudlet = new Cloudlet(cloudletId, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel)
        cloudlet.setUserId(brokerId)
        // add the cloudlet to the list
        cloudletList.add(cloudlet)

        {
          cloudletId += 1; cloudletId - 1
        }
      }

      //cloudletList = foldLeft(cloudletList.add(new Cloudlet(cloudletId, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel)), 1, 40 )

      //cloudlet.setVmId(vmid);
      // submit cloudlet list to the broker
      broker.submitCloudletList(cloudletList)
      // Sixth step: Starts the simulation
      CloudSim.startSimulation
      CloudSim.stopSimulation()
      //Final step: Print results when simulation is over
      val finalCloudletExecutionResults = broker.getCloudletReceivedList
      printCloudletList(finalCloudletExecutionResults)
      Log.printLine("CloudSimExample1 finished!")
    } catch {
      case e: Exception =>
        e.printStackTrace()
        Log.printLine("Unwanted errors happen")
    }
  }

  /**
    * Creates the datacenter.
    *
    * @param name the name
    * @return the datacenter
    */
  private def createDatacenter(name: String) = { // Here are the steps needed to create a PowerDatacenter:
    // 1. We need to create a list to store
    // our machine
    val hostList = new util.ArrayList[Host]
    // 2. A Machine contains one or more PEs or CPUs/Cores.
    // In this example, it will have only one core.
    val peList = new util.ArrayList[Pe]
    val mips = config.getInt("datacenter.mips")
    // 3. Create PEs and add these into the list.
    //for a quad-core machine, a list of 4 PEs is required:
    peList.add(new Pe(0, new PeProvisionerSimple(mips))) // need to store Pe id and MIPS Rating
    peList.add(new Pe(1, new PeProvisionerSimple(mips)))
    peList.add(new Pe(2, new PeProvisionerSimple(mips)))
    peList.add(new Pe(3, new PeProvisionerSimple(mips)))
    // 4. Create Host with its id and list of PEs and add them to the list
    // of machines
    val ram = config.getInt("datacenter.ram")
    // host memory (MB)
    val storage = config.getInt("datacenter.storage")
    // host storage
    val bw = config.getInt("datacenter.bw")

    hostList.add(new Host(0, new RamProvisionerSimple(ram), new BwProvisionerSimple(bw), storage, peList, new VmSchedulerSpaceShared(peList))) // This is our machine
    hostList.add(new Host(1, new RamProvisionerSimple(ram), new BwProvisionerSimple(bw), storage, peList, new VmSchedulerSpaceShared(peList)))
    hostList.add(new Host(2, new RamProvisionerSimple(ram), new BwProvisionerSimple(bw), storage, peList, new VmSchedulerSpaceShared(peList)))
    hostList.add(new Host(3, new RamProvisionerSimple(ram), new BwProvisionerSimple(bw), storage, peList, new VmSchedulerSpaceShared(peList)))
    // 5. Create a DatacenterCharacteristics object that stores the
    // properties of a data center: architecture, OS, list of
    // Machines, allocation policy: time- or space-shared, time zone
    // and its price (G$/Pe time unit).
    val arch = config.getString("datacenter.arch")
    // system architecture
    val os = config.getString("datacenter.os")
    // operating system
    val vmm = config.getString("datacenter.vmm")
    val time_zone = config.getInt("datacenter.time_zone")
    // time zone this resource located
    val cost = config.getDouble("datacenter.cost")
    // the cost of using processing in this resource
    val costPerMem = config.getDouble("datacenter.costPerMem")
    // the cost of using memory in this resource
    val costPerStorage = config.getDouble("datacenter.costPerStorage")
    // the cost of using storage in this
    // resource
    val costPerBw = config.getDouble("datacenter.costPerBw")
    // the cost of using bw in this resource
    val storageList = new util.LinkedList[Storage]
    // we are not adding SAN
    // devices by now
    val characteristics = new DatacenterCharacteristics(arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw)
    // 6. Finally, we need to create a PowerDatacenter object.
    try {
      val datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0)
      datacenter
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  /**
    * Creates the broker.
    *
    * @return the datacenter broker
    */
  private def createBroker: DatacenterBroker = {
    try {
      val broker = new DatacenterBroker("Broker")
      return broker
    } catch {
      case e: Exception =>
        e.printStackTrace()
        return null
    }
  }

  /**
    * Prints the Cloudlet objects.
    *
    * @param list list of Cloudlets
    */
  private def printCloudletList(list: util.List[_<:Cloudlet]): Unit = {
    val size = list.size
    var cloudlet = null
    val indent = "    "
    Log.printLine()
    Log.printLine("========== OUTPUT ==========")
    Log.printLine("Cloudlet ID" + indent + "STATUS" + indent + "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time")
    val dft = new DecimalFormat("###.##")
    var i = 0
    while (i < size) {
      val cloudlet = list.get(i)
      Log.print(indent + cloudlet.getCloudletId + indent + indent)
      if (cloudlet.getCloudletStatus == Cloudlet.SUCCESS) {
        Log.print("SUCCESS")
        Log.printLine(indent + indent + cloudlet.getResourceId + indent + indent + indent + cloudlet.getVmId + indent + indent + dft.format(cloudlet.getActualCPUTime) + indent + indent + dft.format(cloudlet.getExecStartTime) + indent + indent + dft.format(cloudlet.getFinishTime))
      }

      {
        i += 1; i - 1
      }
    }
  }
}