package com.github.snobutaka.zookeeper.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.curator.test.TestingServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ZooKeeperCommandTest {

    TestingServer zkServer;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Enable ZooKeeper (TestingServer) to execute all four letter words commands
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

    ZooKeeperCommand getZkCommand() {
        return new ZooKeeperCommand("localhost", zkServer.getPort());
    }

    @Test
    public void testAreYouOk() throws IOException {
        ZooKeeperCommand zkCommand = getZkCommand();
        assertTrue(zkCommand.areYouOk());
    }

    @Test(expected = IOException.class)
    public void testAreYouOkWhenZooKeeperIsDown() throws Exception {
        ZooKeeperCommand zkCommand = getZkCommand();
        this.zkServer.stop();
        assertFalse(zkCommand.areYouOk());
    }
}
