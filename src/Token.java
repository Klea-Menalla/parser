public class Token {

    public enum Tip {
        NUMER,
        IDENTIFIER,
        BARAZ,
        PLUS,
        MINUS,
        HERE,
        PJESETIM,
        AFISHO,
        LEXO,
        FUNDLINJES
    }

    public Tip tip;
    public String vlera;

    public Token(Tip tip, String vlera) {
        this.tip = tip;
        this.vlera = vlera;
    }

    @Override
    public String toString() {
        return "[" + tip + " : '" + vlera + "']";
    }
}