package tokenizer;

import java.util.ArrayList;

/** Provides a single method that turns a String s into a list of
 *  individual tokens (i.e., word terms) for indexing.
 * 
 */
public interface Tokenizer {
	public abstract ArrayList<String> tokenize(String s);
}
//deal with a-b-c situation
// deal with capital letter and smaller case letter