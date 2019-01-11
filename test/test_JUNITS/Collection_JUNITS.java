package test_JUNITS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import index.SortedDocScore;
import io.FileDocSource;
import io.FileFinder;
import score.TFScoringFun;
import score.TermScoringFun;
import tokenizer.IndexingTokenizer;
import tokenizer.Tokenizer;;

public class Collection_JUNITS {

	@Test
	public void testIndexingTokenizer() {
		Tokenizer tok = new IndexingTokenizer();
		ArrayList<String> tokens = tok.tokenize("A state-of-the-art product.");
		assertEquals("Failed lowercase", "a", tokens.get(0));
		assertEquals("Failed hyphen test", "state-of-the-art", tokens.get(1));
		assertEquals("Failed simple token", "product", tokens.get(2));
	}
	@Test
	public void testSortedDocScore() {
		SortedDocScore doc1 = new SortedDocScore(1.0,1,"This is 1st JUNIT test project 5");
		SortedDocScore doc2 = new SortedDocScore(1.0,1,"This is 2nd JUNIT test project 5");
		SortedDocScore doc1copy = new SortedDocScore(doc1);
		assertNotSame("Failed ordering", 0, doc1.compareTo(doc2));
		assertTrue("Failed equals",doc1.equals(doc1copy));
		assertNotSame("Failed HashCode",doc1.hashCode(),doc1copy.hashCode());
	}
	@Test
	public void testFileDocSource() {
		FileDocSource fds = new FileDocSource("files/Part1/awards_1994/awd_1994_00");
		ArrayList<File> _fds = FileFinder.GetAllFiles("files/Part1/awards_1994/awd_1994_00");
		assertTrue("Failed File Sourcing",_fds.size()==fds.getNumDocs());
	}
	@Test
	public void testTFScoringFun() {
		TermScoringFun tsf = new TFScoringFun();
		assertTrue("Failed scoring",3.0==tsf.scoreToken("computer", 3));
		
	}
	@Test
	public void testnaming() {
		Tokenizer tok = new IndexingTokenizer();
		Tokenizer soln_tok = new soln.tokenizer.IndexingTokenizer();
		String soln = soln_tok.getClass().toString().substring(11, soln_tok.getClass().toString().length());
		String org = tok.getClass().toString().substring(6, tok.getClass().toString().length());
		assertEquals("Failed Naming",soln,org);
		
	}
}
