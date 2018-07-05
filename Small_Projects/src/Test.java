	/**
	 * Complete a function that takes two parameters, a string and an integer.
	 * 
	 * The function will return another string that is similar to the input
	 * string, but with certain characters removed according to the following
	 * rule:
	 * 
	 * The function must remove a character from a "run" when the length of the
	 * run is greater than the given integer parameter n. A run is a sequence of
	 * consecutive characters in a string that are the same character.
	 * 
	 * Example: removeChars("aaab", 2) should return "aab" 
	 * Example: removeChars("aabb", 1) should return "ab" 
	 * Example: removeChars("aabbaa", 1) should return "aba" 
	 * Example: removeChars("aaaabbacccc", 3) should return "aaabbaccc"
	 */
public class Test {
	
	public static void main(String[] args)
	{
		String test = "aaab";
		String test2 = "aabb";
		String test3 = "aabbaa";
		String test4 = "aaaabbacccc";
		String answer = removeChars(test, 2);
		System.out.println(answer);
		answer = removeChars(test2, 1);
		System.out.println(answer);
		answer = removeChars(test3, 1);
		System.out.println(answer);
		answer = removeChars(test4, 3);
		System.out.println(answer);
	}
	
	public static String removeChars(String str, int n) 
	{
		char [] arr = str.toCharArray();
		String result= str;
		for(int i = 0; i < arr.length; i++)
		{
			int c = 0;// count for values
			for(int l = i; l < arr.length; l++)//Begin at last letter
			{				
				if(arr[i] == arr[l])// Same letter?
				{	
					c++; //count how many times the same value is repeated
					if(c > n && l < result.length())
					{
						StringBuilder st = new StringBuilder(result);
						st.deleteCharAt(l-1);
						result = new String(st);
						break;
					}//end if
				}//end if
			}//end for
		}//end for
		return result;
	}
	
    /*
     * Complete the oddNumbers function below.
     */
    static int[] oddNumbers(int l, int r) {
        int size = r - l - 1;
        if(size == 0)
            size = 1;
        int a = 0;
        int[] answer = new int[size];
        for(int i = l; i <= r; i++){
            if((i % 2) != 0){
                answer[a] = i;
                a++;
            }//end if
        }//end for
        return answer;
    }
}

