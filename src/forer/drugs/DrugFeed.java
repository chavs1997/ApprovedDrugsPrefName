package forer.drugs;

import java.util.*;

public class DrugFeed {

	private List<Molecule> molecules;
	private PageInfo page_meta;
	
	

	public DrugFeed(List<Molecule> molecules) {
		super();
		this.molecules = molecules;
	}

	public final List<Molecule> getMolecules() {
		return molecules;
	}

	public final PageInfo getPage_meta() {
		return page_meta;
	}

	
}
