# Homework 1
#### Description: create cloud simulators for evaluating executions of applications in cloud datacenters with different characteristics and deployment models.
#### You can obtain this Git repo using the command git clone git@bitbucket.org:cs441_spring2019/homework1.git


# Cloud Computing using PaaS service model 

This project consists of 2 Cloud simulations. Each using a different policy with different specifications.
In order to compare and contrast the differing policies, the cloudlet specifications have been kept constant. 
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
    40 Cloudlets/tasks/workload, 
    40000 length of instructions, 
    300 kb input filesize, 
    400 kb output filesize, 
    1 core cpu
    Utilization model to full
4. Virtual Machines: 
    40 Virtual machines, 
    20 GB Storage disk, 
    2 GB RAM, 
    1 vCPU with 1000 mips CPU speed, 
    1000 kbits/s Bandwidth, 
    Timeshared scheduler for cloudlets execution

Simulation 2:
1. One Datacenter: 
    Four Host, 
        Four cores with 1000 mips each core, 
        8 GB RAM, 100 GB storage i.e. 100000 MB, 
        1 mbps i.e. 8000 Kbits/s network bandwidth measured as Kbits/s
2. One DataCenterBroker
3. CloudLets:
    40 Cloudlets/tasks/workload, 
    40000 length of instructions, 
    300 kb input filesize, 
    400 kb output filesize, 
    1 core cpu
    Utilization model to full
4. Virtual Machines: 
    40 Virtual machines, 
    20 GB Storage disk, 
    2 GB RAM, 
    1 vCPU with 1000 mips CPU speed, 
    1000 kbits/s Bandwidth, 
    Timeshared scheduler for cloudlets execution


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

```Minimum requirements```

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

1. Download 
2. build sbt
3. run 
```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system



### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Cloudsim](http://www.cloudbus.org/cloudsim/) - CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
* [Lightbend](https://github.com/lightbend/config) - Configuration library
* [SLF4J](https://www.slf4j.org/) - Logging Framework 
* [org.cloudbus.cloudsim.Log](~/lib/cloudsim-3.0.3.jar!/org/cloudbus/cloudsim/Log.class) - Printing Data logged by CloudSim 

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

