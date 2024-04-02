package Model.Pousada;


import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BalancoGerenciar {
    private static final String PATH_ARQUIVOS_BALANCO_JSON = "src/arquivos/balanco.json";

    private Balanco[] balancos;

    public BalancoGerenciar() {
        carregarDados();
    }

    public Balanco[] getBalancos() {
        return balancos;
    }

    public void entradaDeDinheiro(Double entrada){

        carregarDados();
        Balanco.setReceita(Balanco.getReceita()+entrada);
        calcularSaldo();
        salvarDados();
    }


    public void saidaDeDinheiro(Double saida) {
        carregarDados();
        Balanco.setDespesa(Balanco.getDespesa()+saida);
        calcularSaldo();
        salvarDados();
    }

    private void calcularSaldo() {
       Balanco.setSaldo(Balanco.getReceita() - Balanco.getDespesa());
    }

    public void salvarDados() {
        try (Writer writer = new FileWriter(PATH_ARQUIVOS_BALANCO_JSON)) {
            Gson gson = new Gson();
            gson.toJson(balancos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(PATH_ARQUIVOS_BALANCO_JSON)));
            Gson gson = new Gson();
            balancos = gson.fromJson(json, Balanco[].class); // Preencher o array balancos
            Balanco balanco = balancos[0]; // Assume que há apenas um objeto Balanco no array
            Balanco.setReceita(balanco.getReceita());
            Balanco.setDespesa(balanco.getDespesa());
            Balanco.setSaldo(balanco.getSaldo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
