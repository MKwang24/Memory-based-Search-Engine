package index;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import io.DocSource;
import score.TermScoringFun;
import tokenizer.Tokenizer;
import index.DocScore;
import index.SortedDocScore;

public class InvertedIndex extends Index{
	private HashMap<String, HashMap<Integer,Integer>> _index;//HashMap<String term>, HashMap<Integer docID ,Integer term_frequency>>
	private HashMap<String, Integer> _docFreq;//HashMap<String term, Integer frequency>


	
public InvertedIndex( DocSource doc_source,Tokenizer tokenizer, TermScoringFun scoring) {
		super(doc_source,tokenizer,scoring);
		//_scoring.init(this);
		_index = new HashMap<String, HashMap<Integer,Integer>>();
		_docFreq = new HashMap<String, Integer>(); 

	}

@Override
public void buildIndex() {
	// TODO Index all files in DocSource
	ArrayList <String> al = new ArrayList<String>();
	for (int i = 0; i <_docSource.getNumDocs();i++) {

		al.clear();
		String f = _docSource.getDoc(i);
		al.addAll(_tokenizer.tokenize(f));
		for (String s : al) {
			if (!_index.containsKey(s)){
				_index.put(s,new HashMap<Integer,Integer>());
				_index.get(s).put(i,1);
			}
			else if (_index.containsKey(s)&&_index.get(s).get(i)!=null) {
				
				_index.get(s).put(i,_index.get(s).get(i)+1);
				
				}

			else if (_index.containsKey(s)&&_index.get(s).get(i)==null){
				_index.get(s).put(i,1);
			}
			_docFreq.put(s, _index.get(s).values().size());

			}	
		}
	} 
	
	

@Override
public Integer getDocumentFreq(String term) {
	// TODO Return document frequency of the term
	return _docFreq.get(term);
}

@Override
public ArrayList<DocScore> getSortedSearchResults(String query) {
	// TODO Return a ranked list of search results for the provided query
	ArrayList<DocScore> dscore = new ArrayList<DocScore>();
	TreeSet<SortedDocScore> tscore = new TreeSet<SortedDocScore>();
	ArrayList <String> al = new ArrayList<String>();
	al.addAll(_tokenizer.tokenize(query));
	HashMap<Integer,Double> docwscore = new HashMap<Integer,Double>(); 
	for (String s : al) {
		if (_index.get(s)==null) {
			continue;
		}
		else {
			for (int docID : _index.get(s).keySet()) {
				//double score = _scoring.scoreToken(s, _index.get(s).get(docID));
				if (docwscore.containsKey(docID)) {
				docwscore.put(docID, docwscore.get(docID)+_scoring.scoreToken(s, _index.get(s).get(docID)));
				}
				else if (!docwscore.containsKey(docID)){
					docwscore.put(docID, _scoring.scoreToken(s, _index.get(s).get(docID)));
				}
			}
		}
	}
	for (int i : docwscore.keySet()) {
		SortedDocScore ds = new SortedDocScore(docwscore.get(i),i,_docSource.getDoc(i));
		tscore.add(ds);
	}
	dscore.addAll(tscore);
	return dscore;
	}

@Override
public String toString() {
	// TODO Write the correct toString fxn
	return this.toString(); 
}
}
