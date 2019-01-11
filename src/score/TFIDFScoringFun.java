package score;

import index.Index;

public class TFIDFScoringFun implements TermScoringFun{
	private Index _index;
	@Override
	public void init(Index s) {
		// TODO Auto-generated method stub
		_index = s;
	}

	@Override
	public double scoreToken(String term, int freq) {
		// TODO Auto-generated method stub
//		TFIDFd,t=(1 + log10(TFd,t))·log10(NDFt) (use Java’sMath.log10()for this).  
//		HereTFd,tis the frequency (count)of termtin documentd,
//		DFtis the frequency (count) of the number of documents in the corpus (dataset) that contain termtand
//		Nis the total number of documents. See the lecture slides for a justificationof this term scoring function.
		int N = _index.getDocSource().getNumDocs();
		int TFdt = freq;
		int Dft = _index.getDocumentFreq(term);
//		System.out.println("\n"+TFdt);
//		System.out.println(N);
//		System.out.println(Dft+"\n");
//		System.out.println((1+Math.log10(2))*Math.log10(2.5));
		return (double)((1+Math.log10(TFdt))*Math.log10((double)((N+0.0)/Dft)));
	}

}
