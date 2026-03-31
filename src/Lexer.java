import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private String kodi;
    private int pozita;

    public Lexer(String kodi) {
        this.kodi = kodi;
        this.pozita = 0;
    }

    public List<Token> tokenizo() {
        List<Token> tokens = new ArrayList<>();

        while (pozita < kodi.length()) {
            char c = kodi.charAt(pozita);

            if (Character.isWhitespace(c)) { pozita++; continue; }

            if (c == ';') { tokens.add(new Token(Token.Tip.FUNDLINJES, ";")); pozita++; continue; }
            if (c == '=') { tokens.add(new Token(Token.Tip.BARAZ,      "=")); pozita++; continue; }
            if (c == '+') { tokens.add(new Token(Token.Tip.PLUS,       "+")); pozita++; continue; }
            if (c == '-') { tokens.add(new Token(Token.Tip.MINUS,      "-")); pozita++; continue; }
            if (c == '*') { tokens.add(new Token(Token.Tip.HERE,       "*")); pozita++; continue; }
            if (c == '/') { tokens.add(new Token(Token.Tip.PJESETIM,   "/")); pozita++; continue; }

            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (pozita < kodi.length() && (Character.isDigit(kodi.charAt(pozita)) || kodi.charAt(pozita) == '.')) {
                    num.append(kodi.charAt(pozita));
                    pozita++;
                }
                tokens.add(new Token(Token.Tip.NUMER, num.toString()));
                continue;
            }

            if (Character.isLetter(c)) {
                StringBuilder fjala = new StringBuilder();
                while (pozita < kodi.length() && Character.isLetterOrDigit(kodi.charAt(pozita))) {
                    fjala.append(kodi.charAt(pozita));
                    pozita++;
                }
                String s = fjala.toString();
                if (s.equals("Afisho"))    tokens.add(new Token(Token.Tip.AFISHO, s));
                else if (s.equals("Lexo")) tokens.add(new Token(Token.Tip.LEXO, s));
                else                       tokens.add(new Token(Token.Tip.IDENTIFIER, s));
                continue;
            }

            pozita++;
        }

        return tokens;
    }
}