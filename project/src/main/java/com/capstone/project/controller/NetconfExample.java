//package com.capstone.project.controller;
//
//import org.opendaylight.netconf.api.NetconfMessage;
//import org.opendaylight.netconf.client.NetconfClientDispatcher;
//import org.opendaylight.netconf.client.NetconfClientSession;
//import org.opendaylight.netconf.client.NetconfClientSessionListener;
//import org.opendaylight.netconf.client.conf.NetconfClientConfiguration;
//import org.opendaylight.netconf.client.conf.NetconfClientConfigurationBuilder;
//import org.opendaylight.netconf.nettyutil.handler.ssh.client.NetconfSshClient;
//import org.opendaylight.netconf.nettyutil.handler.ssh.client.NetconfSshClientSessionNegotiatorFactory;
//import org.opendaylight.netconf.util.messages.NetconfMessageUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Future;
//
//public class NetconfExample {
//
//    private static final Logger LOG = LoggerFactory.getLogger(NetconfExample.class);
//
//    public static void main(String[] args) {
//        try {
//            // Initialize the SSH client for NETCONF
//            NetconfSshClient sshClient = new NetconfSshClient();
//            sshClient.start();
//
//            // Set up the client configuration
//            NetconfClientConfiguration clientConfig = NetconfClientConfigurationBuilder.create()
//                .withAddress(new InetSocketAddress("192.168.1.1", 830)) // Replace with your server address and port
//                .withSessionListener(NetconfClientSessionListener.NOOP)
//                .withAuthHandler((address, clientSession, clientDispatcher) -> {
//                    // Replace with your username and password for the SSH connection
//                    sshClient.authPassword("your-username", "your-password");
//                })
//                .build();
//
//            // Set up the NetconfClientDispatcher
//            NetconfClientDispatcher clientDispatcher = new NetconfClientDispatcher(
//                sshClient,
//                new NetconfSshClientSessionNegotiatorFactory(sshClient),
//                NetconfClientSessionListener.NOOP);
//
//            // Connect to the server
//            Future<NetconfClientSession> futureSession = clientDispatcher.createClient(clientConfig);
//            NetconfClientSession session = futureSession.get();
//
//            // Create and send the get-config request
//            String getConfigRequest = "<rpc message-id=\"1\"><get-config><source><running/></source></get-config></rpc>";
//            NetconfMessage getConfigMessage = NetconfMessageUtil.stringToNetconfMessage(getConfigRequest);
//            Future<NetconfMessage> futureResponse = session.sendRequest(getConfigMessage);
//            NetconfMessage response = futureResponse.get();
//
//            // Print the response
//            System.out.println("Response: " + response.getDocument().toString());
//
//            // Close the session and SSH client
//            session.close();
//            sshClient.stop();
//
//        } catch (Exception e) {
//            LOG.error("Error occurred during NETCONF operation", e);
//        }
//    }
//}
