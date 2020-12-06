import java.util.*;
public class LexicalAnalyzer {

    //public ArrayList<String> tokens;
    public ArrayList<String> tokenType;
    
    public LexicalAnalyzer() {

        //tokens = new ArrayList<>();
        tokenType = new ArrayList<>();


        tokenType.add("KEYWORD"); //tokenID 0
        tokenType.add("GPR");     //tokenID 1
        tokenType.add("FPR");     //tokenID 2
        tokenType.add("ERROR");   //tokenID 3

        //tokens = process(input);

    }

    public ArrayList<String> process(String input){
        //String[] words = input.split("[\n, ]+");
        ArrayList<String> tokenList = new ArrayList<>();
        char ch;

        int i = 0, start = 0;
        while(i < input.length()){
            ch = input.charAt(i);

            if(ch == ',' || ch == ' ' || ch == '\n'){
                tokenList.add(input.substring(start, i));
                start = i+1;
            }
            i++;
        }
        tokenList.removeAll(Arrays.asList("",null));

        return tokenList;
    }

    public String tokenIdentifier(String word) {

        char c = 0, prev = 0, prev2 = 0;
        int num = 0,state = 0, tokenID = 3;

        for(int i = 0; i <= word.length(); i++){

            if(i < word.length()) {
                c = word.charAt(i); //by letter
                num = Character.getNumericValue(c);
            }
            
            
            if(i >= 1)
                prev = word.charAt(i-1); //previous char

            if(i >= 2)
                prev2 = word.charAt(i-2); //2nd to the prev char

            switch (state) {
                case 0: {
                    if(c == 'D')
                        state = 1;
                    else if(c == 'R')
                        state = 11;
                    else if(c == 'F') 
                        state = 13;
                    else if(c == '$')
                        state = 12;
                    else {
                        tokenID = 3; //error
                        state = 19; // hell state
                    }
                        
                }
                break;

                case 1: {
                    if(c == 'A')
                        state = 2;
                    else if(c == 'M')
                        state = 7;
                    else {
                        tokenID = 3; //error
                        state = 19; 
                    }
                }
                break;

                case 2: {
                    if(c == 'D')
                        state = 3;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 3: {
                    if(c == 'D')
                        state = 4;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 4: {
                    if(c == 'I')
                        state = 5;
                    else if(c == 'U')
                        state = 6;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 5: {
                    if(c == 'U')
                        state = 6;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 6: {
                    tokenID = 0; //keyword
                }
                break;

                case 7: {
                    if(c == 'U')
                        state = 8;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 8: {
                    if(c == 'L')
                        state = 9;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 9: {
                    if((c == 'T') && ((i+1) == word.length()))
                        //state = 10; 
                        state = 6;
                    else {
                        state = 10;
                    }
                }
                break;

                case 10: {
                    if(c == 'U')
                        state = 6;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 11: {
                    if((num == 0) || (num >= 4 && num <= 9))
                        state = 14;
                    else if (num == 3)
                        state = 15;
                    else if (num == 1 || num == 2)
                        state = 17;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 12: {
                    if(c == 'F')
                        state = 13;
                    else if((num == 0) || (num >= 4 && num <= 9))
                        state = 14;
                    else if (num == 3)
                        state = 15;
                    else if (num == 1 || num == 2)
                        state = 17;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 13: {
                    if((num == 0) || (num >= 4 && num <= 9))
                        state = 14;
                    else if (num == 3)
                        state = 15;
                    else if (num == 1 || num == 2)
                        state = 17;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 14: {
                    //char prev3 = word.charAt(i-3);
                    if(prev2 == 'R' || prev2 == '$')
                        tokenID = 1; //gpr
                    else if (prev2 == 'F')
                        tokenID = 2; //fpr
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 15: {
                    if((prev2 == 'R' || prev2 == '$') && ((i+1) != word.length()))
                        tokenID = 1; //gpr
                    else if ((prev == 'F')  && ((i+1) != word.length()))
                        tokenID = 2; //fpr
                    else
                        state = 16;
                }
                break;

                case 16: {
                    char prev3 = word.charAt(i-3);
                    if((num == 0 || num == 1) && (prev3 == 'R' || prev3 == '$'))
                        tokenID = 1; //gpr
                    else if((num == 0 || num == 1) && (prev3 == 'F'))
                        tokenID = 2; //fpr
                    
                }
                break;

                case 17: {
                    if((prev2 == 'R' || prev2 == '$')  && ((i+1) != word.length()))
                        tokenID = 1; //gpr
                    else if ((prev2 == 'F') && ((i+1) != word.length()))
                        tokenID = 2; //fpr
                    else if(((i+1) != word.length()))
                        state = 18;
                    else {
                        tokenID = 3; //error
                        state = 19;
                    }
                }
                break;

                case 18: {
                    char prev3 = word.charAt(i-3);
                    if((num >= 0 && num <= 9) && (prev3 == 'R' || prev3 == '$'))
                        tokenID = 1; //gpr
                    else if((num >= 0 && num <= 9) && (prev3 == 'F'))
                        tokenID = 2; //fpr
                }
                break;

                case 19: { //hell state
                    tokenID = 3;
                }
                break;
            }

        }

        return tokenType.get(tokenID);

    }


}
