# PaytmLabs SDE Challenge

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

## Design Question

Design A Google Analytic like Backend System.
We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

### Requirements

1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.



# Coding Question

## Implementation

I write this data structure by using a queue to store the value and use a sum to record the sum of values, and a list to store all values, when the total number of value is more than N, queue will pop and sum will minus the pop value, when we want to calculate average we will use the sum to divide the size of queue.

Time complexity:

getItems(): O(1)

add(): O(1)

isEmpty(): O(1)

getAverage(): O(1)

Space complexity: O(n)





# 										Google Analytics Design



## What is google Analytic

Google Analytic is a tool that can collect the data of visitors and then do analysis by using these data, in order to scale this application, i choose microservices route to build this application and integrate important features to complete a user friendly, security and high performance data analysis platform.



## Requirements:

1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.



### Estimation:

Let’s assume on average a user has 200 visitors every day.

**Traffic estimates:** Let’s assume 300M daily active users with each user fetching their visitor data on an average of five times a day. This will result in 1.5B requests per day or approximately 17,500 requests per second. For visitors, every user has 200 visitors on average, totally, this will result in 60B records per day or approximately 700000 records per second.

**Storage estimates:** On average, let’s assume we need to have around 50 records in every user’s feed that we want to keep in memory for a quick fetch. Let’s also assume that on average each post would be 1KB in size. This would mean that we need to store roughly 50KB of data per user. To store all this data for all the active users we would need 15TB of memory. If a server can hold 10GB we would need around 150 machines to keep the recent visitors in memory for all active users. 



### Component Design:

#### API GateWay:

I choose the microservices route to build the application and API GateWay is service a that provides a single-entry point for certain groups of microservices. The most important role the API gateway plays is ensuring reliable processing of every API call. In addition, the API gateway provides the ability to design API specs, help provide enterprise-grade security, and manage APIs centrally. 



#### Service Registration/Discovery:

Service Registration is a process of a service registering its location in a central registry. It usually registers its host and port and sometimes authentication credentials, protocols, versions numbers, and/or environment details. Service Discovery is a process of a client application querying the central registry to learn of the location of services. Service Registration and Discovery have become very crucial to keep architecture clean and also for the team to focus on a core part of development rather than maintenance and resolving fires. That is why i try to use service registration to manager our microservices.



#### OAuth2 Authentication:

The OAuth2 authorization framework is a protocol that allows a user to grant a third-party web site or application access to the user's protected resources, without necessarily revealing their long-term credentials or even their identity. In our application, the OAuth2 will ensure the security authentication for users.



#### Load Balancer:

In our application, we use load balancer to distribute the traffic, . It helps to spread the traffic across a cluster of servers to improve responsiveness and availability of applications, websites or databases. Load Balancer also keeps track of the status of all the resources while distributing requests. If a server is not available to take new requests or is not responding or has elevated error rate, Load Balancer will stop sending traffic to such a server.

Typically a load balancer sits between the client and the server accepting incoming network and application traffic and distributing the traffic across multiple backend servers using various algorithms. By balancing application requests across multiple servers, a load balancer reduces individual server load and prevents any one application server from becoming a single point of failure, thus improving overall application availability and responsiveness.

In general the first advantage of load balancer is that users can experience faster, uninterrupted service. Users won’t have to wait for a single struggling server to finish its previous tasks. Instead, their requests are immediately passed on to a more readily available resource. So by using load balancer we can provide customers with metric in a short time delay. Another advantage is that it can reduce the downtime and increase performance,  because load balancer support many servers and if a server is not available, it can send request to another server, this will enable you to shut off any server for maintenance and channel traffic to your other resources without disrupting work in any location. This way you can reduce the downtime, maintain the uptime and improve the performance.



#### SpringBoot Microservices:

Using a microservices approach to application development can improve resilience and expedite the time to market, but breaking apps into fine-grained services offers complications. With fine-grained services and lightweight protocols, microservices offers increased modularity, making applications easier to develop, test, deploy, and, more importantly, change and maintain. With microservices, the code is broken into independent services that run as separate processes. 



#### Data Pipeline:

##### Apache Kafka:

Apache Kafka is used for building real-time streaming data pipelines that reliably get data between many independent systems or applications. It has a buffer that store the data temporarily and reduce the backpressure of data pipeline, Kafka Streams is also a pretty fast, lightweight stream processing solution that works best if all of the data ingestion is coming through Apache Kafka. The ingested data is read directly from Kafka by Apache Spark for stream processing and creates Timeseries Ignite RDD (Resilient Distributed Datasets).



##### Apache Spark:

Apache Spark is a data analysis tool enables scalable, high-throughput, fault-tolerant stream processing of live data streams. In our case Apache Spark is a perfect choice in our case. . This is because Spark achieves high performance for both batch and streaming data, using a state-of-the-art DAG scheduler, a query optimizer, and a physical execution engine. So our platform can do analysis on real time data from data stream and reprocess historical data from static data storages.  After processing the data, the result will show on the dashboard and store in the cache. 

![Image for post](https://miro.medium.com/max/1440/1*6NW39JzuZPwCIK11o2kL2Q.png)



##### Elastic Search:

Elasticsearch is a distributed, RESTful search and analytics engine capable of addressing a growing number of use cases. As the heart of the Elastic Stack, it centrally stores your data for lightning fast search, fine‑tuned relevancy, and powerful analytics that scale with ease. In our case, Using elastic search help us to do a full text retrievial.



##### Cache: 

Cache will enable you to make vastly better use of the resources you already have as well as making otherwise unattainable product requirements feasible. Caches take advantage of the locality of reference principle: recently requested data is likely to be requested again. They are used in almost every computing layer: hardware, operating systems, web browsers, web applications, and more. A cache is like short-term memory: it has a limited amount of space, but is typically faster than the original data source and contains the most recently accessed items. In our case we need a cache to store our analysis result, so we use redis as our cache memory because redis can handle with different kind of datatypes and all data are stored in RAM, so the speed of this system is phenomenal, often performing even better than Memcached.  



##### Data storage:

In this part we use mysql to store our data, because we have to handle a large amount of write request, so we will implement a master slave replication with reading/writing split, because we have  billions of write events per day, this technology will ease the pressure on the database. 

The critical database operation – WRITES (updates, deletes, alters, etc), always take place on master so that master’s data snapshot is the latest one. READS take place on the slaves, thus distributing the loads to slaves with a dedicated master for WRITES only. This improves performance of the system. Different databases under a master can be replicated to separate slaves to keep load on any database to the minimum and thus to improve performance.

![MySQL master-slave replication](https://uploads.toptal.io/blog/image/127627/toptal-blog-image-1543512385306-1f627e3afafe9665f763469ba0a283d3.png)





### Summary:

In general, we use a microservice route to build this application with some features including API gateway, service registration, authentication and spring boot microservice. 

Integrating a cache can help this application have a better performance and get the recently processed data quickly. 

Using Apache Kafka and Apache Spark build a data analysis pipe and process real time stream data，output time series data and process historical data stored in mysql. 

Build a slave-master data storage with mysql to store the visitor data and split read and write to ease the pressure on the large amount of read write query everyday.

Load Balancer can help this application distribute the traffic and reduce the response time of this application to make sure customers can get the result metrics in a short time, instead of this, Load Balancer reduce the downtime dramatically, even one server is down, load balancer can help you choose another server to process the data.



