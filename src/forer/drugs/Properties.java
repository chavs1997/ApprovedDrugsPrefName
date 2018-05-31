package forer.drugs;

public class Properties {

	private int aromatic_rings;
	private String full_molformula;
	private String full_mwt;
	private String molecular_species;

	
	
	public Properties(int aromatic_rings, String full_molformula, String full_mwt, String molecular_species) {
		super();
		this.aromatic_rings = aromatic_rings;
		this.full_molformula = full_molformula;
		this.full_mwt = full_mwt;
		this.molecular_species = molecular_species;
	}

	public final int getRings() {
		return aromatic_rings;
	}

	public final String getFormula() {
		return full_molformula;
	}

	public final String getWeight() {
		return full_mwt;
	}

	public final String getSpecies() {
		return molecular_species;
	}

}
