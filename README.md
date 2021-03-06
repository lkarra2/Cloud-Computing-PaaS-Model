# Cloud Computing using PaaS service model 

This project consists of 2 Cloud simulations. Each using a different policy with different specifications.
In order to compare and contrast the differing policies, the cloudlet specifications have been kept constant. 

Since the cloud simulations are providing platform to run any application as a cloudlet by the user, the model being used is, Platform as a Service(PaaS).

The following are the policies for each simulation

Simulation 1:
1. Four Datacenters: 
    - Four Host, 
        - Four cores with 1000 mips each core, 
        - 8 GB RAM, 
        - 1000 GB storage i.e. 100000 MB, 
        - 1 mbps i.e. 8000 Kbits/s network bandwidth measured as Kbits/s
2. One DataCenterBroker
3. CloudLets:
    - 40 Cloudlets/tasks/workload, 
    - 40000 length of instructions, 
    - 300 kb input filesize, 
    - 400 kb output filesize, 
    - 1 core cpu
    - Utilization model to full
4. Virtual Machines: 
    - 40 Virtual machines, 
    - 20 GB Storage disk, 
    - 2 GB RAM, 
    - 1 vCPU with 1000 mips CPU speed, 
    - 1000 kbits/s Bandwidth, 
    - Timeshared scheduler for cloudlets execution

Simulation 2:
1. One Datacenter: 
    - Two Hosts, 
        - Four cores with 1000 mips each core, 
        - 8 GB RAM, 100 GB storage i.e. 100000 MB, 
        - 1 mbps i.e. 8000 Kbits/s network bandwidth measured as Kbits/s
2. One DataCenterBroker
3. CloudLets:
    - 40 Cloudlets/tasks/workload, 
    - 40000 length of instructions, 
    - 300 kb input filesize, 
    - 400 kb output filesize, 
    - 1 core cpu
    - Utilization model to full
4. Virtual Machines: 
    - 40 Virtual machines, 
    - 20 GB Storage disk, 
    - 2 GB RAM, 
    - 1 vCPU with 1000 mips CPU speed, 
    - 1000 kbits/s Bandwidth, 
    - Spaceshared scheduler for cloudlets execution

### Results

The success or failure of the simulation can be broken down and understood by analyzing the following characteristics:
```
1. If a cloudlet could execute fully within the time taken to run the simulation, SUCCESS is printed by Log
2. The costs of the User and our cost are printed at the end. If the cost of the user is greater than cost by us, then it is a success.
```
The screenshots of the results of both simulations have been added under ./src/res/

### Prerequisites

What things you need to install the software and how to install them

1. Cloudsim
2. Scala
```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

1. Download .zip, unzip
2. Open in IntelliJ or move to project folder
```
Steps to run on SBT
1. build
2. run
3. Choose option [1]Cloudsimulation1 [2]Cloudsimulation2
```


```
Steps to run on IntelliJ
1. Open project
2. Build project
3. Run Simulation
```

## Running the tests

The tests have been divided in 2 files, TestSimulation1 and TestSimulation2 containing tests of 1 and 2 respectively.
To run the tests,
```
1. Run Tests on IntelliJ OR
2. run test - on SBT shell
```


## Built With

* [Cloudsim](http://www.cloudbus.org/cloudsim/) - CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
* [Lightbend](https://github.com/lightbend/config) - Configuration library
* [SLF4J](https://www.slf4j.org/) - Logging Framework 
* [org.cloudbus.cloudsim.Log](~/lib/cloudsim-3.0.3.jar!/org/cloudbus/cloudsim/Log.class) - Printing Data logged by CloudSim 


## Authors

Lakshmi Manaswi Karra - 657276611 - [https://github.com/lkarra2]

## Acknowledgments

* Mark Grechanik for assigning this homework

