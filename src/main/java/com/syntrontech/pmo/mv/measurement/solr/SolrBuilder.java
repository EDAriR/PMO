package com.syntrontech.pmo.mv.measurement.solr;

public class SolrBuilder {
	
	public static Solr newSolr(String solrUrl){
		return new SolrImp(solrUrl);
	}

}
