import java.util.ArrayList;
import java.util.List;

public class Parser {

    private List<Token> tokens;
    private int pozita;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pozita = 0;
    }

    private Token shiko() {
        if (pozita < tokens.size()) return tokens.get(pozita);
        return null;
    }

    private Token merr() {
        return tokens.get(pozita++);
    }

    private Token prit(Token.Tip tip) {
        Token t = merr();
        if (t.tip != tip)
            throw new RuntimeException("Gabim: pritej " + tip + " por u gjet " + t.tip);
        return t;
    }

    public Nod shprehje() {
        Nod majtas = term();
        while (shiko() != null && (shiko().tip == Token.Tip.PLUS || shiko().tip == Token.Tip.MINUS)) {
            Token op = merr();
            Nod djathtas = term();
            majtas = new Nod.NodOperacion(op.vlera, majtas, djathtas);
        }
        return majtas;
    }

    private Nod term() {
        Nod majtas = faktor();
        while (shiko() != null && (shiko().tip == Token.Tip.HERE || shiko().tip == Token.Tip.PJESETIM)) {
            Token op = merr();
            Nod djathtas = faktor();
            majtas = new Nod.NodOperacion(op.vlera, majtas, djathtas);
        }
        return majtas;
    }

    private Nod faktor() {
        Token t = shiko();
        if (t.tip == Token.Tip.NUMER) {
            merr();
            return new Nod.NodNumer(Double.parseDouble(t.vlera));
        }
        if (t.tip == Token.Tip.IDENTIFIER) {
            merr();
            return new Nod.NodVariabel(t.vlera);
        }
        throw new RuntimeException("Gabim ne shprehje tek: " + t);
    }

    public Nod instruksion() {
        Token t = shiko();

        if (t.tip == Token.Tip.AFISHO) {
            merr();
            Token emri = prit(Token.Tip.IDENTIFIER);
            prit(Token.Tip.FUNDLINJES);
            return new Nod.NodAfisho(emri.vlera);
        }

        if (t.tip == Token.Tip.LEXO) {
            merr();
            Token emri = prit(Token.Tip.IDENTIFIER);
            prit(Token.Tip.FUNDLINJES);
            return new Nod.NodLexo(emri.vlera);
        }

        if (t.tip == Token.Tip.IDENTIFIER) {
            Token emri = merr();
            prit(Token.Tip.BARAZ);
            Nod shprehja = shprehje();
            prit(Token.Tip.FUNDLINJES);
            return new Nod.NodCaktim(emri.vlera, shprehja);
        }

        throw new RuntimeException("Instruksion i panjohur: " + t);
    }

    public List<Nod> program() {
        List<Nod> instruksionet = new ArrayList<>();
        while (pozita < tokens.size()) {
            instruksionet.add(instruksion());
        }
        return instruksionet;
    }
}