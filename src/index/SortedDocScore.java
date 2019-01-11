package index;


public class SortedDocScore extends DocScore implements Comparable{

		public SortedDocScore (double score, int doc_id, String content) {
			super(score,doc_id,content);
		}
		
		public SortedDocScore(DocScore ds) {
			super(ds);
		}
		
		//take this information and pass to the class that I inherit from
		@Override
		public boolean equals(Object arg0) {
			if (!(arg0 instanceof DocScore)) 
				return false;
			DocScore s2 = (DocScore)arg0;
			if (this._score == s2._score && this._content .equals(s2._content)) {
			return true;
			}
			else {
			return false;
			}
		}
		@Override
		public int hashCode(){
				//combine content hashcode for the score(while you will have to generate)
			int d = this._docID+this._content.hashCode()+(int)this._score;
			return d;
			}

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			if (!(arg0 instanceof SortedDocScore)) {
				return 1;
			}
			else {
			SortedDocScore s2 = (SortedDocScore)arg0;
			if (this.getScore()<s2._score) {
				return 1;
				
			}
			else if (this.getScore()==s2.getScore()) {
				return this.getContent().compareTo(s2.getContent());
			}
			else {
				return -1;
			}

		}	
		}
}
		//Alternative Method

//			if ((this._score>s2._score)) {
//				return -1;
//			}
//			else if (this._score==s2._score){
//				for (int i=0; i < this._content.length(); i++) 
//
//					if (this._content.charAt(i)<s2._content.charAt(i)) {
//						return -1;
//					}
//					else if (this._content.charAt(i)>s2._content.charAt(i)) {
//						return 0 ;
//					}
//					else {
//						continue;
//					}
//			}
//			return 0;

			
		
		


	
