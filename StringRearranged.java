/**
 * 
 * Given a string S. check of the letter can be rearranged so that two characters that are 
 * adjacent to each other are not the same. If possible output any possible result. 
 * If no possible, return the empty string.
 * 
 * Input : S ="aab"
 * Output : "aba"
 * 
 * Input : S="aaab"
 * Output :""
 * 
 * Note : S will be consist of lowercase latters and have length in range [1,500]
 * 
 */

import java.util.PriorityQueue;

public class StringRearranged {

	public static void main(String[] args) {
		
		System.out.println(new StringRearranged().getString("aaabc"));
	}
	
	private String getString(String s) {
		
		int [] cntArr=new int[26];
		
		for(int i=0;i<s.length();i++) {
			cntArr[s.charAt(i)-'a']++;
		}
		
		PriorityQueue<FreqCharPair> pQueue=new PriorityQueue<FreqCharPair>((a1,a2)->a2.freq-a1.freq);
		
		for(int i=0;i<cntArr.length;i++) {
			
			if(cntArr[i]>0) {
				pQueue.add(new FreqCharPair(cntArr[i],(char)('a'+i)));
			}
		}
		
		String result="";
		
		//Taking tempPair because we do not want pair for next iteration. Without tempPair for String "aaabc" it will fail.
		FreqCharPair tempPair=new FreqCharPair(-1, '$');
		
		while(!pQueue.isEmpty()) {
			
			FreqCharPair pair=pQueue.poll();
			result+=pair.chr;
			
			pair.freq--;
			
			if(tempPair.freq>0) {
				pQueue.add(tempPair);
			}
			
			tempPair=pair;
		}
		
		if(result.length()!=s.length()) {
			result="";
		}
		return result;
	}

}


class FreqCharPair {
	
	int freq;
	char chr;
	
	FreqCharPair(int freq, char chr){
		this.freq=freq;
		this.chr=chr;	
	}
}