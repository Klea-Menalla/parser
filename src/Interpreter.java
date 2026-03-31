import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {

    private Map<String, Double> variablat = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void ekzekuto(List<Nod> program) {
        for (Nod nod : program) {
            ekzekutoNod(nod);
        }
    }

    private void ekzekutoNod(Nod nod) {
        if (nod instanceof Nod.NodCaktim) {
            Nod.NodCaktim caktim = (Nod.NodCaktim) nod;
            double vlera = vlereso(caktim.shprehja);
            variablat.put(caktim.emri, vlera);
        }
        else if (nod instanceof Nod.NodAfisho) {
            Nod.NodAfisho afisho = (Nod.NodAfisho) nod;
            if (!variablat.containsKey(afisho.emri))
                throw new RuntimeException("Variabli '" + afisho.emri + "' nuk eshte deklaruar!");
            double vlera = variablat.get(afisho.emri);
            if (vlera == (long) vlera) System.out.println(afisho.emri + " = " + (long) vlera);
            else                       System.out.println(afisho.emri + " = " + vlera);
        }
        else if (nod instanceof Nod.NodLexo) {
            Nod.NodLexo lexo = (Nod.NodLexo) nod;
            System.out.print("Jep vleren per " + lexo.emri + ": ");
            double vlera = scanner.nextDouble();
            variablat.put(lexo.emri, vlera);
        }
    }

    private double vlereso(Nod nod) {
        if (nod instanceof Nod.NodNumer)
            return ((Nod.NodNumer) nod).vlera;

        if (nod instanceof Nod.NodVariabel) {
            String emri = ((Nod.NodVariabel) nod).emri;
            if (!variablat.containsKey(emri))
                throw new RuntimeException("Variabli '" + emri + "' nuk eshte deklaruar!");
            return variablat.get(emri);
        }

        if (nod instanceof Nod.NodOperacion) {
            Nod.NodOperacion op = (Nod.NodOperacion) nod;
            double majtas   = vlereso(op.majtas);
            double djathtas = vlereso(op.djathtas);
            switch (op.operator) {
                case "+": return majtas + djathtas;
                case "-": return majtas - djathtas;
                case "*": return majtas * djathtas;
                case "/":
                    if (djathtas == 0) throw new RuntimeException("Pjesetim me zero!");
                    return majtas / djathtas;
            }
        }

        throw new RuntimeException("Nod i panjohur!");
    }
}