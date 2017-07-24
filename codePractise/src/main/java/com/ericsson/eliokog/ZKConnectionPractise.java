package com.ericsson.eliokog;

import static org.apache.zookeeper.CreateMode.EPHEMERAL;
import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import com.ericsson.zookeeper.ZooKeeperConnection;

// TODO: Auto-generated Javadoc
/**
 * The Class ZKConnectionPractise.
 */
public class ZKConnectionPractise {

	/** The zk conn. */
	private static ZooKeeperConnection zkConn = null;
	private static ZooKeeper zk = null;
	private static final Map<String, Object> nodeCache = new HashMap<>();
	private static final String DELIMITER = "/";
	private static final List<String> children = new ArrayList<>();

	/** The Constant path. */
	private static final String path = "/tests";
	private static final String dataPath = "/tests/subtest/node2/childnode2";

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
//	public static void main(final String[] args) {
//		Watcher watcher = new ZKConnectionWatcher();
//		try {
//			zk = new ZooKeeper("10.175.146.197:6181", 10000, watcher);
//			createRecursive(zk, dataPath, getBytes("hello World!"), OPEN_ACL_UNSAFE, EPHEMERAL);
//			watcher.waitUntilConnectionSet(10000);
//			cacheAllNode(path);
//			Thread.sleep(100000);
//		} catch (IOException | InterruptedException | KeeperException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Cache all node.
	 *
	 * @param path
	 *            the path
	 */
	private static void cacheAllNode(String path) {
		NodeWatcher nodeWatcher = new NodeWatcher();
		try {
			List<String> children = zk.getChildren(path, nodeWatcher);
			nodeCache.put(path, zk.getData(path, null, null));
			children.stream().forEach(childPath -> {
				try {
					String fullPath = path + DELIMITER + childPath;
					nodeCache.put(fullPath, zk.getData(fullPath, null, null));
					if (!zk.getChildren(fullPath, null).isEmpty()) {
						cacheAllNode(fullPath);
					}

				} catch (KeeperException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			System.out.println(nodeCache);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void createRecursive(ZooKeeper zk, String path, byte[] data, List acl, CreateMode createMode)
			throws InterruptedException, KeeperException {
		try {
			if (zk.exists(path, null) == null) {
				zk.create(path, data, acl, createMode);
			}
		} catch (KeeperException.NoNodeException e) {
			String childPath = path.substring(0, path.lastIndexOf(DELIMITER));
			createRecursive(zk, childPath, data, acl, createMode);
			createRecursive(zk, path, data, acl, createMode);
		}
	}

	/**
	 * The Class NodeWatcher.
	 */
	private static class NodeWatcher implements Watcher {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.
		 * WatchedEvent)
		 */
		@Override
		public void process(final WatchedEvent event) {
			EventType eventType = event.getType();
			if (eventType == EventType.NodeCreated) {
				try {
					nodeCache.put("", getObject());
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (eventType == EventType.NodeChildrenChanged) {

			} else if (eventType == EventType.NodeDataChanged) {

			} else if (eventType == EventType.NodeDeleted) {

			} else if (eventType == eventType.None) {

			}
		}
	}

	private static class ZKConnectionWatcher implements Watcher {
		CountDownLatch latch = new CountDownLatch(1);

		@Override
		public void process(WatchedEvent event) {
			if (event.getState() == KeeperState.SyncConnected) {
				System.out.println("Connection established");
				latch.countDown();
			}
		}

		void waitUntilConnectionSet(int timeout) {
			try {
				latch.await(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				System.out.println("Interrupt while connecting to Zookeeper!");
				e.printStackTrace();
			}
		}

	}

	/**
	 * Gets the bytes.
	 *
	 * @param obj
	 *            the obj
	 * @return the bytes
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] getBytes(final Object obj) throws IOException {

		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bo);
		os.writeObject(obj);
		return bo.toByteArray();

	}

	/**
	 * Gets the object.
	 *
	 * @return the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public static Object getObject() throws IOException, ClassNotFoundException {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(zkConn.getData(path));
			ObjectInputStream os = new ObjectInputStream(in);
			Object o = os.readObject();
			System.out.println(o);
			return o;
		} catch (KeeperException | InterruptedException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
