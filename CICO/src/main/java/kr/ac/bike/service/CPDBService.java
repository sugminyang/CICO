package kr.ac.bike.service;

import java.util.List;

import kr.ac.bike.model.Result;

public interface CPDBService {
	List<Result> getExperimentsWithDiseaseName(String diseaseName);
	List<Result> getExperimentsWithDOID(String DOID);
	List<Result> getExperimentsWithChemicalName(String searchTarget);
	List<Result> getExperimentsWithCAS(String searchTarget);
	List<Result> getExperimentsWithTissue(String searchTarget);
	List<Result> getExperimentsWithTumorType(String searchTarget);
}
