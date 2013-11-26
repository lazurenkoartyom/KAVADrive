/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.logic;

import java.util.Random;

/**
 *
 * @author Aleksey
 */
public class TokenFactory {
           
    public static String getNewValue(){
        class Token{
            private static final int KEY_LENGTH = 16;
            private static final int CHAR_BEGIN = 0x30;//"0"=0x30
            private static final int CHAR_END = 0x5a;//"Z"=0x5a,"z"=0x7a
            private final int[] EXCEPTED_CHARS = 
                {0x3a,0x3b,0x3c,0x3d,0x3e,0x3f,0x40};//[]{}!?...
                //,0x5b,0x5c,0x5d,0x5e,0x5f,0x60
            private char[] key = new char[KEY_LENGTH];

            private Token(){
            }

            private String generate(){
                for(int i=0;i<KEY_LENGTH;i++){
                    key[i]= getRandomChar(); 
                }
                return String.copyValueOf(key);
            }

            private char getRandomChar(){
                Random random = new Random();
                int charCode;
                int range = CHAR_END-CHAR_BEGIN+1;
                do{
                    charCode = CHAR_BEGIN + random.nextInt(range);
                }while(isExceptedChar(charCode));
                return (char)charCode;
            }

            private boolean isExceptedChar(int value) {
                for ( int i : EXCEPTED_CHARS )
                    if (i == value) return true;
                return false;
            }
        }
        return new Token().generate();
    }
    
}