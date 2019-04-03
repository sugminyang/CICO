package kr.ac.bike.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.bike.model.Result;
import kr.ac.bike.service.CPDBServiceImple;

@Controller
public class HomeController {
	@Autowired
	CPDBServiceImple cpdbService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		String searchTarget = "lung cancer";
		
		model.addAttribute("searchTarget", searchTarget);
		return "home";	//main .jsp 
	}

	//TODO. 1. chemicalName으로 검색	Acetonitrile
	//TODO. 2. DO ID로 검색	hepatocellular carcinoma
	//TODO. 3. tissue로 검색	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String table(@Validated String searchTarget,@Validated String select_multiple, Model model) {
		model.addAttribute("searchType", select_multiple);
		model.addAttribute("searchTarget", searchTarget);
		model.addAttribute("dynamicColumn", "geneSymbol");
		System.out.println(searchTarget);
		List<Result> lists = null;
		
		switch(select_multiple)
		{
			case "DiseaseName":
				String diseaseName = searchTarget.replace(" ", "+");
	
				lists = cpdbService.getExperimentsWithDiseaseName(diseaseName);
				if(lists == null) 	{
					lists = new ArrayList<Result>();
				}
			break;
		
			case "DOID":
				String DOID = searchTarget.replace(":", "%3A");
				lists = cpdbService.getExperimentsWithDOID(DOID);
				if(lists == null) 	{
					lists = new ArrayList<Result>();
				}
			break;
			
			case "chemicalName":
				String chemName = searchTarget.replace(",", "%2C");
				lists = cpdbService.getExperimentsWithChemicalName(chemName);
				if(lists == null) 	{
					lists = new ArrayList<Result>();
				}
			break;
			
			case "CAS":
				lists = cpdbService.getExperimentsWithCAS(searchTarget);
				if(lists == null) 	{
					lists = new ArrayList<Result>();
				}
			break;
			
			case "tissue":
				lists = cpdbService.getExperimentsWithTissue(searchTarget);
				if(lists == null) 	{
					lists = new ArrayList<Result>();
				}
			break;
			
			case "tumorType":
				lists = cpdbService.getExperimentsWithTumorType(searchTarget);
				if(lists == null) 	{
					lists = new ArrayList<Result>();
				}
				model.addAttribute("dynamicColumn", "affectedTissue");
			break;
			
			default:
				System.out.println("Selected Error");
		}
		
		model.addAttribute("lists", lists);
		return "home";
	}
	
	
}
