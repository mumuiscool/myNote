# myNote

About the Spring Reactive

1、Reactive programming - primarily functional programming, declaring what needs to be done to the data, rather than telling the machine how to do it step by step.

2、This is advantageous for scenarios with high concurrency and continuous input of large amounts of data, and the latest data needs to be displayed in real time.

3、Concurrency is often constrained by physical resources such as the number of connections and network bandwidth. Assuming that the bandwidth of a single network adapter on a server is 100MiB/s (1000Mbps ETH), a server of 16 GB +2.5 GB *4 can handle far more than 100MiB/s of data without a complex algorithm such as common memory bandwidth is 10GiB/s, and common SSD storage bandwidth is 4GiB/s by the PICE 3.0+.

4、Reactive does not solve the problem of high latency, although it does reduce the time it takes to establish a connection per concurrency.

5、It is mainly used in continuous data I/O scenarios, but does not perform well in scenarios with many common management operations.

6、A lot of purely functional programming uses a lot of memory and is difficult to optimize
The barrier to entry for developers will be high then Imperative programer.

7、The effect produced by the use of a single project is of little significance, which requires the collaborative use of all modules to produce value.
