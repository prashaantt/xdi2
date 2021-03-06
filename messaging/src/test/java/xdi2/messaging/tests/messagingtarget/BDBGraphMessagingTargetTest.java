package xdi2.messaging.tests.messagingtarget;

import java.io.File;
import java.io.IOException;

import xdi2.core.Graph;
import xdi2.core.impl.keyvalue.bdb.BDBGraphFactory;

public class BDBGraphMessagingTargetTest extends AbstractGraphMessagingTargetTest {

	private static BDBGraphFactory graphFactory = new BDBGraphFactory();

	static {

		cleanup();
	}

	public static void cleanup() {

		File path = new File(graphFactory.getDatabasePath());

		if (path.exists()) {

			for (File file : path.listFiles()) {

				file.delete();
			}
		}
	}

	@Override
	protected Graph openNewGraph(String id) throws IOException {

		String databaseName = "xdi2-test-graph." + id;

		graphFactory.setDatabaseName(databaseName);

		Graph graph = graphFactory.openGraph();

		return graph;
	}
}
