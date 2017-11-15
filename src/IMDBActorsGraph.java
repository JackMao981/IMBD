import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IMDBActorsGraph extends IMDBGraph{

	public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
		super(actorsFilename, actressesFilename);
	}

	@Override
	public Node getNodeByName(String name) {
		return mActors.get(name);
	}

	@Override
	public Collection<? extends Node> getNodes() {
		return mActors.values();
	}
}