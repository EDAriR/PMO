package com.syntrontech.pmo.solr;

public class SolrBuilder {
	
	public static Solr newSolr(String solrUrl){
		return new SolrImp(solrUrl);
	}

}
