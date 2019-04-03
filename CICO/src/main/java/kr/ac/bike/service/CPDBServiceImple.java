package kr.ac.bike.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.bike.model.Result;
import kr.ac.bike.utils.Utils;

@Service("CPDBService")
public class CPDBServiceImple implements CPDBService{
	private String baseURL = "http://147.47.41.92:2020/";

	@Override
	public List<Result> getExperimentsWithDiseaseName(String DiseaseName)	{
		String request_url =baseURL + "sparql?query=PREFIX+cpdb%3A+%3Chttp%3A%2F%2Fcpdb.bike.snu.ac.kr%2Fcpdb%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+map%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%23%3E%0D%0APREFIX+db%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%3E%0D%0ASELECT+DISTINCT+%3Fexperiment+%3FchemName+%3Fdosage+%3Fspecies+%3Fdoid+%3FgeneSymbol+%3Freference+WHERE+%7B%0D%0A+%3Fdo+cpdb%3AhasDiseaseName+%22" + DiseaseName + "%22.%0D%0A+%3Fdo+cpdb%3AhasDOID+%3Fdoid.%0D%0A+%3Fdo+cpdb%3AhasGeneSymbol+%3FgeneSymbol.%0D%0A%0D%0A+%3Feffect+cpdb%3Aeffect_sameAsDOID+%3Fdoid.%0D%0A+%3Feffect+cpdb%3AhasEffectOfExperiments+%3Fexperiment.+%0D%0A+%3Fexperiment+cpdb%3AdosageOfPossiblyInducedCarcinogenesis+%3Fdosage.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasExperimentalModelSystem+%3Fmodel.%0D%0A+%3Fmodel+cpdb%3AhasSystemSpecies+%3Fspecies.%0D%0A%0D%0A+%3Fexperiment+cpdb%3Apotentially_induced_carcinogenesis_chemical+%3FchemicalObj.%0D%0A+%3FchemicalObj+cpdb%3AhasChemicalsName+%3FchemName.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasReferenceOfExperiment+%3Freference.%0D%0A%7D%0D%0A%0D%0Aorder+by+%3Fdoid%0D%0Alimit+1000&output=json";		
		List<Result> lists = Utils.sparqlQuery(request_url,false);
		return lists;
	}

	public List<Result> getExperimentsWithDOID(String DOID)	{
		String request_url =baseURL + "sparql?query=PREFIX+cpdb%3A+%3Chttp%3A%2F%2Fcpdb.bike.snu.ac.kr%2Fcpdb%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+map%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%23%3E%0D%0APREFIX+db%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%3E%0D%0ASELECT+DISTINCT+%3Fexperiment+%3FchemName+%3Fdosage+%3Fspecies+%3FdiseaseName+%3FgeneSymbol+%3Freference+WHERE+%7B%0D%0A+%3Fdo+cpdb%3AhasDOID+%22" + DOID + "%22.%0D%0A+%3Fdo+cpdb%3AhasDiseaseName+%3FdiseaseName.%0D%0A+%3Fdo+cpdb%3AhasGeneSymbol+%3FgeneSymbol.%0D%0A%0D%0A+%3Feffect+cpdb%3Aeffect_sameAsDOID+%22" + DOID + "%22.%0D%0A+%3Feffect+cpdb%3AhasEffectOfExperiments+%3Fexperiment.+%0D%0A+%3Fexperiment+cpdb%3AdosageOfPossiblyInducedCarcinogenesis+%3Fdosage.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasExperimentalModelSystem+%3Fmodel.%0D%0A+%3Fmodel+cpdb%3AhasSystemSpecies+%3Fspecies.%0D%0A%0D%0A+%3Fexperiment+cpdb%3Apotentially_induced_carcinogenesis_chemical+%3FchemicalObj.%0D%0A+%3FchemicalObj+cpdb%3AhasChemicalsName+%3FchemName.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasReferenceOfExperiment+%3Freference.%0D%0A%7D%0D%0A%0D%0Alimit+1000%0D%0A&output=json";
		List<Result> lists = Utils.sparqlQuery(request_url,false);
		return lists;
	}


	public List<Result> getExperimentsWithChemicalName(String chemName) {
		String request_url =baseURL + "sparql?query=PREFIX+cpdb%3A+%3Chttp%3A%2F%2Fcpdb.bike.snu.ac.kr%2Fcpdb%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+map%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%23%3E%0D%0APREFIX+db%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%3E%0D%0ASELECT+DISTINCT+%3Fexperiment+%3FchemName+%3Fdosage+%3Fspecies+%3FdiseaseName+%3FgeneSymbol+%3Freference+WHERE+%7B%0D%0A+%3FchemicalObj+cpdb%3AhasChemicalsName+%22" + chemName + "%22.%0D%0A+%3FchemicalObj+cpdb%3AhasCAS+%3FchemName.%0D%0A+%3Fexperiment+cpdb%3Apotentially_induced_carcinogenesis_chemical+%3FchemicalObj.%0D%0A+%0D%0A%3Fexperiment+cpdb%3AdosageOfPossiblyInducedCarcinogenesis+%3Fdosage.%0D%0A+%3Fexperiment+cpdb%3AhasReferenceOfExperiment+%3Freference.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasExperimentalModelSystem+%3Fmodel.%0D%0A+%3Fmodel+cpdb%3AhasSystemSpecies+%3Fspecies.%0D%0A%0D%0A+%3Feffect+cpdb%3AhasEffectOfExperiments+%3Fexperiment.+%0D%0A+%3Feffect+cpdb%3Aeffect_sameAsDOID+%3Fdoid.%0D%0A%0D%0A+%3Fdo+cpdb%3AhasDOID+%3Fdoid.%0D%0A+%3Fdo+cpdb%3AhasDiseaseName+%3FdiseaseName.%0D%0A+%3Fdo+cpdb%3AhasGeneSymbol+%3FgeneSymbol.%0D%0A%7D%0D%0A%0D%0Alimit+1000&output=json";
		List<Result> lists = Utils.sparqlQuery(request_url,false);
		return lists;
	}

	public List<Result> getExperimentsWithCAS(String CAS) {
		String request_url =baseURL + "sparql?query=PREFIX+cpdb%3A+%3Chttp%3A%2F%2Fcpdb.bike.snu.ac.kr%2Fcpdb%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+map%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%23%3E%0D%0APREFIX+db%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%3E%0D%0ASELECT+DISTINCT+%3Fexperiment+%3FchemName+%3Fdosage+%3Fspecies+%3FdiseaseName+%3FgeneSymbol+%3Freference+WHERE+%7B%0D%0A+%3FchemicalObj+cpdb%3AhasCAS+%22" + CAS + "%22.%0D%0A+%3FchemicalObj+cpdb%3AhasChemicalsName+%3FchemName.%0D%0A+%3Fexperiment+cpdb%3Apotentially_induced_carcinogenesis_chemical+%3FchemicalObj.%0D%0A+%0D%0A%3Fexperiment+cpdb%3AdosageOfPossiblyInducedCarcinogenesis+%3Fdosage.%0D%0A+%3Fexperiment+cpdb%3AhasReferenceOfExperiment+%3Freference.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasExperimentalModelSystem+%3Fmodel.%0D%0A+%3Fmodel+cpdb%3AhasSystemSpecies+%3Fspecies.%0D%0A%0D%0A+%3Feffect+cpdb%3AhasEffectOfExperiments+%3Fexperiment.+%0D%0A+%3Feffect+cpdb%3Aeffect_sameAsDOID+%3Fdoid.%0D%0A%0D%0A+%3Fdo+cpdb%3AhasDOID+%3Fdoid.%0D%0A+%3Fdo+cpdb%3AhasDiseaseName+%3FdiseaseName.%0D%0A+%3Fdo+cpdb%3AhasGeneSymbol+%3FgeneSymbol.%0D%0A%7D%0D%0A%0D%0Alimit+1000&output=json";
		List<Result> lists = Utils.sparqlQuery(request_url,false);
		return lists;
	}

	public List<Result> getExperimentsWithTissue(String tissue) {
		String request_url =baseURL + "sparql?query=PREFIX+cpdb%3A+%3Chttp%3A%2F%2Fcpdb.bike.snu.ac.kr%2Fcpdb%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+map%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%23%3E%0D%0APREFIX+db%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%3E%0D%0ASELECT+DISTINCT+%3Fexperiment+%3FchemName+%3Fdosage+%3Fspecies+%3Ftissue+%3Fdoid+%3FgeneSymbol+%3Freference+WHERE+%7B%0D%0A+%3Ftissue+cpdb%3AhasTissueName+%22" + tissue +"%22.%0D%0A+%3Feffect+cpdb%3AaffectedTissue+%3Ftissue.%0D%0A+%3Feffect+cpdb%3AhasEffectOfExperiments+%3Fexperiment.+%0D%0A+%3Feffect+cpdb%3Aeffect_sameAsDOID+%3Fdoid.%0D%0A%0D%0A+%3Fdo+cpdb%3AhasDOID+%3Fdoid.%0D%0A+%3Fdo+cpdb%3AhasDiseaseName+%3FdiseaseName.%0D%0A+%3Fdo+cpdb%3AhasGeneSymbol+%3FgeneSymbol.%0D%0A%0D%0A%3Fexperiment+cpdb%3AdosageOfPossiblyInducedCarcinogenesis+%3Fdosage.%0D%0A+%3Fexperiment+cpdb%3AhasReferenceOfExperiment+%3Freference.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasExperimentalModelSystem+%3Fmodel.%0D%0A+%3Fmodel+cpdb%3AhasSystemSpecies+%3Fspecies.%0D%0A%0D%0A+%3Fexperiment+cpdb%3Apotentially_induced_carcinogenesis_chemical+%3FchemicalObj.%0D%0A+%3FchemicalObj+cpdb%3AhasChemicalsName+%3FchemName.%0D%0A%0D%0A%7D%0D%0A%0D%0Alimit+1000%0D%0A&output=json";
		List<Result> lists = Utils.sparqlQuery(request_url,false);
		return lists;
	}

	public List<Result> getExperimentsWithTumorType(String tumorType) {
		String request_url =baseURL + "sparql?query=PREFIX+cpdb%3A+%3Chttp%3A%2F%2Fcpdb.bike.snu.ac.kr%2Fcpdb%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+map%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%23%3E%0D%0APREFIX+db%3A+%3Chttp%3A%2F%2F147.47.41.92%3A2020%2Fresource%2F%3E%0D%0ASELECT+DISTINCT+%3Fexperiment+%3FchemName+%3Fdosage+%3Fspecies+%3Fdoid+%3FtissueName+%3Freference+WHERE+%7B%0D%0A+%3Ftumor+cpdb%3AhasTumorType+%22"+tumorType+"%22.%0D%0A+%3Feffect+cpdb%3AaffectedTumorType+%3Ftumor.%0D%0A+%3Feffect+cpdb%3AaffectedTissue+%3Ftissue.%0D%0A+%3Ftissue+cpdb%3AhasTissueName+%3FtissueName.%0D%0A%0D%0A+%3Feffect+cpdb%3AhasEffectOfExperiments+%3Fexperiment.+%0D%0A+%3Feffect+cpdb%3Aeffect_sameAsDOID+%3Fdoid.%0D%0A%0D%0A%3Fexperiment+cpdb%3AdosageOfPossiblyInducedCarcinogenesis+%3Fdosage.%0D%0A+%3Fexperiment+cpdb%3AhasReferenceOfExperiment+%3Freference.%0D%0A%0D%0A+%3Fexperiment+cpdb%3AhasExperimentalModelSystem+%3Fmodel.%0D%0A+%3Fmodel+cpdb%3AhasSystemSpecies+%3Fspecies.%0D%0A%0D%0A+%3Fexperiment+cpdb%3Apotentially_induced_carcinogenesis_chemical+%3FchemicalObj.%0D%0A+%3FchemicalObj+cpdb%3AhasChemicalsName+%3FchemName.%0D%0A%0D%0A%7D%0D%0Aorder+by+desc%28%3Fdoid%29%0D%0Alimit+1000%0D%0A&output=json";
		List<Result> lists = Utils.sparqlQuery(request_url,true);
		return lists;
	}
}
