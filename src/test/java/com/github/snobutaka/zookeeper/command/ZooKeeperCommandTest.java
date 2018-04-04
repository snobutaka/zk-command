package com.github.snobutaka.zookeeper.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.curator.test.TestingServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ZooKeeperCommandTest {

    TestingServer zkServer;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Enable ZooKeeper (TestingServer) to execute four letter word commands
        System.setProperty("zookeeper.4lw.commands.whitelist", "*");
    }

    @Before
    public void setUp() throws Exception {
        zkServer = new TestingServer();
    }

    @After
    public void tearDown() throws Exception {
        if (this.zkServer != null) {
            this.zkServer.close();
        }
    }

    @Test
    public void testAreYouOK() throws Exception {
        ZooKeeperCommand zkCommand = new ZooKeeperCommand("localhost", zkServer.getPort());
        String res = zkCommand.areYouOK();
        assertThat(res, is("imok"));
    }
}
