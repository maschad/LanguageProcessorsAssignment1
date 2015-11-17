/* Specification for ECOLI tokens */
// user customisations
package hpl.lang;
import hpl.sys.*;
import java_cup.runtime.*;


public class HPLLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    public int getChar()
    {
	return yychar + 1;
    }
    public int getLine()
    {
	return yyline + 1;
    }
    public String getText()
    {
	return yytext();
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public HPLLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public HPLLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private HPLLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 1;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		42
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,5:2,2,4,5,2,4:18,5,48,57,3,4,52,4:2,9,10,50,49,6,16,1,51,56,54:9,8,7,47" +
",36,47,4:2,27,42,55,33,30,35,25,55,23,55:2,44,24,28,55,26,55,31,40,29,41,55" +
",46,55:3,11,4,12,53,55,4,18,39,55,32,21,34,15,55,13,55:2,43,14,19,55,17,55," +
"22,37,20,38,55,45,55:3,4:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,134,
"0,1,2,1,3,1:9,4,1,5:2,1:5,6,1,2,7:4,8,7:7,1:4,9,1,8,5,7,1,10,2,11,12,13,14," +
"15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39," +
"40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64," +
"65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89," +
"90,91,92,93,94")[0];

	private int yy_nxt[][] = unpackFromString(95,58,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,46:2,15,112,46:3,82,46,50,46:2,115,46:3,85" +
",46,100,103,116,117,16,128,46:2,133,46:2,86,87,104,105,17,45,18,19,20,21,22" +
",23,46,81,24,-1:112,25,-1,25,-1:2,4,-1,4:55,-1:13,46,88,46,-1,46:2,26,46:16" +
",-1,46:10,-1:7,46:3,-1:37,47,-1:22,44,-1:52,23,-1,23,-1:14,46:3,-1,46:19,-1" +
",46:10,-1:7,46:3,-1:55,30,-1,30,-1,1,48:56,43,-1,48:56,-1:14,46:3,-1,46:7,8" +
"9,46:3,26,46:7,-1,46:10,-1:7,46:3,-1:18,55,-1:53,46:3,-1,46:15,27,46:3,-1,4" +
"6:10,-1:7,46:3,-1:27,57,-1:44,46:3,-1,46:16,28,46:2,-1,46:10,-1:7,46:3,-1:1" +
"9,59,-1:52,46:3,-1,46:3,29,46:15,-1,46:10,-1:7,46:3,-1:28,61,-1:43,46:3,-1," +
"46:12,29,46:6,-1,46:10,-1:7,46:3,-1:14,63,-1:57,46:3,51,46:19,-1,46:10,-1:7" +
",46:3,-1:24,65,-1:47,46:3,53,46:19,-1,46:10,-1:7,46:3,-1:20,67,-1:51,46:3,-" +
"1,46:3,31,46:15,-1,46:10,-1:7,46:3,-1:29,69,-1:42,46:3,-1,46:12,31,46:6,-1," +
"46:10,-1:7,46:3,-1:21,71,-1:50,46:3,-1,46:3,32,46:15,-1,46:10,-1:7,46:3,-1:" +
"30,73,-1:41,46:3,-1,46:12,33,46:6,-1,46:10,-1:7,46:3,-1:22,75,-1:49,46:3,-1" +
",46:4,34,46:14,-1,46:10,-1:7,46:3,-1:31,77,-1:40,46:3,-1,46:13,35,46:5,-1,4" +
"6:10,-1:7,46:3,-1:23,38,-1:48,46:3,-1,46:4,36,46:14,-1,46:10,-1:7,46:3,-1:3" +
"2,39,-1:39,46:3,-1,46:13,37,46:5,-1,46:10,-1:7,46:3,-1:23,40,-1:66,41,-1:27" +
",49,-1:52,23,-1,23,-1:14,46:3,-1,46:2,52,46:16,-1,46:10,-1:7,46:3,-1:22,79," +
"-1:66,80,-1:40,46:3,-1,46:11,54,46:7,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:4," +
"56,46:14,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:13,58,46:5,-1,46:10,-1:7,46:3," +
"-1:14,46:2,60,-1,46:19,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:8,62,46:10,-1,46" +
":10,-1:7,46:3,-1:14,46:3,-1,46:17,131,46,-1,46:10,-1:7,46:3,-1:14,46:3,-1,4" +
"6:18,132,-1,46:10,-1:7,46:3,-1:14,64,46:2,-1,46:19,-1,46:10,-1:7,46:3,-1:14" +
",46:3,-1,46:6,66,46:12,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:2,68,46:16,-1,46" +
":10,-1:7,46:3,-1:14,46:3,-1,46:11,70,46:7,-1,46:10,-1:7,46:3,-1:14,46,72,46" +
",-1,46:19,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:7,74,46:11,-1,46:10,-1:7,46:3" +
",-1:14,46,76,46,-1,46:19,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:7,78,46:11,-1," +
"46:10,-1:7,46:3,-1:14,46:3,-1,46:4,90,46:14,-1,46:10,-1:7,46:3,-1:21,83,-1:" +
"66,84,-1:41,46:3,-1,46:13,91,46:5,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46,92,46" +
":17,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:10,93,46:8,-1,46:10,-1:7,46:3,-1:14" +
",94,46:2,-1,46:19,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:6,95,46:12,-1,46:10,-" +
"1:7,46:3,-1:14,46:3,-1,46,96,46:17,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:10,9" +
"7,46:8,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46,98,46:17,-1,46:10,-1:7,46:3,-1:1" +
"4,46:3,-1,46:10,99,46:8,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46,106,46:17,-1,46" +
":10,-1:7,46:3,-1:20,101,-1:66,102,-1:42,46:3,-1,46:10,107,46:8,-1,46:10,-1:" +
"7,46:3,-1:14,46:3,-1,46:5,108,46:13,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:14," +
"109,46:4,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:5,110,46:13,-1,46:10,-1:7,46:3" +
",-1:14,46:3,-1,46:14,111,46:4,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:17,118,46" +
",-1,46:10,-1:7,46:3,-1:14,113,-1:67,114,-1:47,46:3,-1,46:18,119,-1,46:10,-1" +
":7,46:3,-1:14,46:3,-1,46:19,-1,46:2,120,46:7,-1:7,46:3,-1:19,121,-1:66,122," +
"-1:43,46:3,-1,46:19,-1,46:5,123,46:4,-1:7,46:3,-1:14,46:3,-1,46:19,-1,46,12" +
"4,46:8,-1:7,46:3,-1:18,125,-1:66,126,-1:44,46:3,129,46:19,-1,46:10,-1:7,46:" +
"3,-1:14,46:3,130,46:19,-1,46:10,-1:7,46:3,-1:14,46:3,-1,46:19,-1,46:4,127,4" +
"6:5,-1:7,46:3,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ //. on a line by itself is EOF
			  return new Symbol(sym.EOF);}
					case -3:
						break;
					case 3:
						{
                        //skip newline, but reset char counter
                        yychar = 0;
                      }
					case -4:
						break;
					case 4:
						{ // ignore line comments
                      }
					case -5:
						break;
					case 5:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -6:
						break;
					case 6:
						{ // ignore whitespace
                      }
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.COMMA);}
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.SEMI);}
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.COLON);}
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.LPAREN);}
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.RPAREN);}
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.LBRACKET);}
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.RBRACKET);}
					case -14:
						break;
					case 14:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.MINUS); }
					case -16:
						break;
					case 16:
						{ return new Symbol(sym.ASSIGN); }
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.CMP, yytext()); }
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.PLUS); }
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.TIMES); }
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.DIV); }
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.MOD); }
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.POW); }
					case -23:
						break;
					case 23:
						{
	       // Numerical constants
	       return new Symbol(sym.INTEGER,
				 new Integer(yytext()));
	       }
					case -24:
						break;
					case 24:
						{
			yybegin(STRING);
		}
					case -25:
						break;
					case 25:
						{
			// FRACTION
			return new Symbol(sym.FRACTION, new Double(yytext()));
		}
					case -26:
						break;
					case 26:
						{ return new Symbol(sym.IN); }
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.END);}
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.END);}
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.LET); }
					case -30:
						break;
					case 30:
						{
			// REAL no. used for defining frames
			return new Symbol(sym.REAL, new Double(yytext()));
		}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.WAIT); }
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.PAINT); }
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.PAINT); }
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.FRAME); }
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.FRAME); }
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.SUBFRAME); }
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.SUBFRAME); }
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.IMG_PAINTER);}
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.IMG_PAINTER);}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.DEF_PAINTER);}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.DEF_PAINTER);}
					case -42:
						break;
					case 42:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -43:
						break;
					case 43:
						{
			yybegin(YYINITIAL);
		}
					case -44:
						break;
					case 45:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -45:
						break;
					case 46:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -46:
						break;
					case 47:
						{ return new Symbol(sym.CMP, yytext()); }
					case -47:
						break;
					case 48:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -48:
						break;
					case 50:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -49:
						break;
					case 52:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -50:
						break;
					case 54:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -51:
						break;
					case 56:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -52:
						break;
					case 58:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -53:
						break;
					case 60:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -54:
						break;
					case 62:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -55:
						break;
					case 64:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -56:
						break;
					case 66:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -57:
						break;
					case 68:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -58:
						break;
					case 70:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -59:
						break;
					case 72:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -60:
						break;
					case 74:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -61:
						break;
					case 76:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -62:
						break;
					case 78:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -63:
						break;
					case 81:
						{
	       // Numerical constants
	       return new Symbol(sym.INTEGER,
				 new Integer(yytext()));
	       }
					case -64:
						break;
					case 82:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -65:
						break;
					case 85:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -66:
						break;
					case 86:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -67:
						break;
					case 87:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -68:
						break;
					case 88:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -69:
						break;
					case 89:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -70:
						break;
					case 90:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -71:
						break;
					case 91:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -72:
						break;
					case 92:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -73:
						break;
					case 93:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -74:
						break;
					case 94:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -75:
						break;
					case 95:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -76:
						break;
					case 96:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -77:
						break;
					case 97:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -78:
						break;
					case 98:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -79:
						break;
					case 99:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -80:
						break;
					case 100:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -81:
						break;
					case 103:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -82:
						break;
					case 104:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -83:
						break;
					case 105:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -84:
						break;
					case 106:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -85:
						break;
					case 107:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -86:
						break;
					case 108:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -87:
						break;
					case 109:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -88:
						break;
					case 110:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -89:
						break;
					case 111:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -90:
						break;
					case 112:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -91:
						break;
					case 115:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -92:
						break;
					case 116:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -93:
						break;
					case 117:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -94:
						break;
					case 118:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -95:
						break;
					case 119:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -96:
						break;
					case 120:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -97:
						break;
					case 123:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -98:
						break;
					case 124:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -99:
						break;
					case 127:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -100:
						break;
					case 128:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -101:
						break;
					case 131:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -102:
						break;
					case 132:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -103:
						break;
					case 133:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -104:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
