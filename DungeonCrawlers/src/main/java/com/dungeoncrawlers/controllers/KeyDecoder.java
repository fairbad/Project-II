package com.dungeoncrawlers.controllers;

public class KeyDecoder {

	//Used to decode encoded AWS Keys
		public String deCode(String str) {
			String tempstr = "";
			for (int i = 0; i < str.length(); i++) {
				switch (str.charAt(i)) {
				case 'A':
					tempstr += "B";
					break;
				case 'B':
					tempstr += "C";
					break;
				case 'C':
					tempstr += "D";
					break;
				case 'D':
					tempstr += "E";
					break;
				case 'E':
					tempstr += "F";
					break;
				case 'F':
					tempstr += "G";
					break;
				case 'G':
					tempstr += "H";
					break;
				case 'H':
					tempstr += "I";
					break;
				case 'I':
					tempstr += "J";
					break;
				case 'J':
					tempstr += "K";
					break;
				case 'K':
					tempstr += "L";
					break;
				case 'L':
					tempstr += "M";
					break;
				case 'M':
					tempstr += "N";
					break;
				case 'N':
					tempstr += "O";
					break;
				case 'O':
					tempstr += "P";
					break;
				case 'P':
					tempstr += "Q";
					break;
				case 'Q':
					tempstr += "R";
					break;
				case 'R':
					tempstr += "S";
					break;
				case 'S':
					tempstr += "T";
					break;
				case 'T':
					tempstr += "U";
					break;
				case 'U':
					tempstr += "V";
					break;
				case 'V':
					tempstr += "W";
					break;
				case 'W':
					tempstr += "X";
					break;
				case 'X':
					tempstr += "Y";
					break;
				case 'Y':
					tempstr += "Z";
					break;
				case 'Z':
					tempstr += "A";
					break;
				// Lower Case
				case 'a':
					tempstr += "b";
					break;
				case 'b':
					tempstr += "c";
					break;
				case 'c':
					tempstr += "d";
					break;
				case 'd':
					tempstr += "e";
					break;
				case 'e':
					tempstr += "f";
					break;
				case 'f':
					tempstr += "g";
					break;
				case 'g':
					tempstr += "h";
					break;
				case 'h':
					tempstr += "i";
					break;
				case 'i':
					tempstr += "j";
					break;
				case 'j':
					tempstr += "k";
					break;
				case 'k':
					tempstr += "l";
					break;
				case 'l':
					tempstr += "m";
					break;
				case 'm':
					tempstr += "n";
					break;
				case 'n':
					tempstr += "o";
					break;
				case 'o':
					tempstr += "p";
					break;
				case 'p':
					tempstr += "q";
					break;
				case 'q':
					tempstr += "r";
					break;
				case 'r':
					tempstr += "s";
					break;
				case 's':
					tempstr += "t";
					break;
				case 't':
					tempstr += "u";
					break;
				case 'u':
					tempstr += "v";
					break;
				case 'v':
					tempstr += "w";
					break;
				case 'w':
					tempstr += "x";
					break;
				case 'x':
					tempstr += "y";
					break;
				case 'y':
					tempstr += "z";
					break;
				case 'z':
					tempstr += "a";
					break;
				//numbers
				case '0':
					tempstr += "1";
					break;
				case '1':
					tempstr += "2";
					break;
				case '2':
					tempstr += "3";
					break;
				case '3':
					tempstr += "4";
					break;
				case '4':
					tempstr += "5";
					break;
				case '5':
					tempstr += "6";
					break;
				case '6':
					tempstr += "7";
					break;
				case '7':
					tempstr += "8";
					break;
				case '8':
					tempstr += "9";
					break;
				case '9':
					tempstr += "0";
					break;
				//symbols
				case '/':
					tempstr += "+";
					break;
				case '+':
					tempstr += "/";
					break;
				}
			}
			return tempstr;
		}
	
}
