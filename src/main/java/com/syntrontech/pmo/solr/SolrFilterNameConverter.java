package com.syntrontech.pmo.solr;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;

public class SolrFilterNameConverter {

	private static SolrFilterNameConverter converter = new SolrFilterNameConverter();
	private Set<Character> keyword = Stream.of("(", ")", " ")
			.map(s->s.charAt(0))
			.collect(Collectors.toSet());
	
	public static List<String> convertFilterNames(List<String> filters,SolrDoc solr) 
			throws ParamFormatErrorException {
		List<String> _filters = new CopyOnWriteArrayList<>();
		for(String filter : filters) {
			String _filter = converter.convertFilterName(filter, solr);
			_filters.add(_filter);
		}
		return _filters;
	}
	
	private String convertFilterName(String filter, SolrDoc doc) throws ParamFormatErrorException {
		return _convertFilterName(filter, new StringBuffer(), 0, doc);
	}
	

	private String _convertFilterName(String filter, StringBuffer _filter, int index, SolrDoc doc) throws ParamFormatErrorException {
		int next = filter.indexOf(':', index);
		if(next<0) {
			for(int i=index; i<filter.length();i++) {
				_filter.append(filter.charAt(i));
			}
			return _filter.toString();
		} else if('\\'==filter.charAt(next-1)){
			for(int i = index; i<next; i++) {
				_filter.append(filter.charAt(i));
			}
			return _convertFilterName(filter, _filter.append(':'), next+1, doc);
		} else {
			String f = filter.substring(index, next);
			String field = getField(f);
			for(int i = index; i < next ; i++) {
				char c = filter.charAt(i);
				if(keyword.contains(c)) {
					_filter.append(c);
				} else {
					if(next-field.length()-i==0) {
						_filter.append(doc.getFieldNameformTO(field)).append(':');
						i=next;
					} else {
						_filter.append(c);
					}
				}
			}
			return _convertFilterName(filter, _filter, next+1, doc);
		}
	}
	
	private String getField(String f) {
		StringBuffer sb = new StringBuffer();
		for(int i=f.length()-1;i>-1;i--) {
			char c = f.charAt(i);
			if(!keyword.contains(c)) {
				sb.append(c);
			} else {
				i=0;
			}
		}
		return sb.reverse().toString();
	}
}
