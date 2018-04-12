# ZooKeeper Command Client


## About

This library is a simple client for [ZooKeeper's four letter words commands](http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_zkCommands).



## Usage

```java
ZooKeeperCommand zkCommand = new ZooKeeperCommand("localhost", 2181);
zkCommand.areYouOk() // This returns true if ZooKeeper is running without errors.
```
