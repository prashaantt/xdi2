package xdi2.core.impl.memory;

import xdi2.core.ContextNode;
import xdi2.core.Graph;
import xdi2.core.impl.AbstractGraph;

public class MemoryGraph extends AbstractGraph implements Graph {

	private static final long serialVersionUID = 8979035878235290607L;

	private int sortmode;

	private MemoryContextNode rootContextNode;

	MemoryGraph(MemoryGraphFactory graphFactory, int sortmode) {

		super(graphFactory);

		this.sortmode = sortmode;

		this.rootContextNode = new MemoryContextNode(this, null);
		this.rootContextNode.arcXri = null;
	}

	@Override
	public ContextNode getRootContextNode() {

		return this.rootContextNode;
	}

	@Override
	public void close() {

	}

	/*
	 * Misc methods
	 */

	int getSortMode() {

		return this.sortmode;
	}
}
