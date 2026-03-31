public abstract class Nod {

    public static class NodNumer extends Nod {
        public double vlera;
        public NodNumer(double vlera) { this.vlera = vlera; }
    }

    public static class NodVariabel extends Nod {
        public String emri;
        public NodVariabel(String emri) { this.emri = emri; }
    }

    public static class NodOperacion extends Nod {
        public String operator;
        public Nod majtas;
        public Nod djathtas;
        public NodOperacion(String operator, Nod majtas, Nod djathtas) {
            this.operator = operator;
            this.majtas   = majtas;
            this.djathtas = djathtas;
        }
    }

    public static class NodCaktim extends Nod {
        public String emri;
        public Nod shprehja;
        public NodCaktim(String emri, Nod shprehja) {
            this.emri     = emri;
            this.shprehja = shprehja;
        }
    }

    public static class NodAfisho extends Nod {
        public String emri;
        public NodAfisho(String emri) { this.emri = emri; }
    }

    public static class NodLexo extends Nod {
        public String emri;
        public NodLexo(String emri) { this.emri = emri; }
    }
}