package kr.ac.bike.model;

public class Result {
	private String experiment;
	private String chemicalName;
	private String dosage;
	private String species;
	private String doInfo;
	private String geneSymbol;
	private String tissue;
	private String reference;
	private boolean hasGene = true;
	
	public Result(String experiment, String chemicalName, String dosage, String species, String doInfo,
			String geneSymbol, String reference) {
		super();
		this.experiment = experiment;
		this.chemicalName = chemicalName;
		this.dosage = dosage;
		this.species = species;
		this.doInfo = doInfo;
		this.geneSymbol = geneSymbol;
		this.reference = reference;
	}
	public Result(String experiment, String chemicalName, String dosage, String species, String doInfo,
			String tissue, String reference,boolean hasGene) {
		super();
		this.experiment = experiment;
		this.chemicalName = chemicalName;
		this.dosage = dosage;
		this.species = species;
		this.doInfo = doInfo;
		this.tissue = tissue;
		this.reference = reference;
		this.hasGene = hasGene;
	}

	public String getDoInfo() {
		return doInfo;
	}

	public void setDoInfo(String doInfo) {
		this.doInfo = doInfo;
	}

	public String getGeneSymbol() {
		if(geneSymbol == null)	{
			return tissue;
		}
		else if(geneSymbol.length() != 0)	{
			return geneSymbol;
		}
		else	{
			return tissue;
		}
	}
	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}

	public String getExperiment() {
		return experiment;
	}

	public void setExperiment(String experiment) {
		this.experiment = experiment;
	}

	public String getChemicalName() {
		return chemicalName;
	}

	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "Result [doInfo=" + doInfo + ", geneSymbol=" + geneSymbol + ", experiment=" + experiment + ", chemicalName="
				+ chemicalName + ", dosage=" + dosage + ", species=" + species + ", reference=" + reference + "]";
	}

}
