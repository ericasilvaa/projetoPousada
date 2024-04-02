package Model.Pousada;

import Model.Pousada.Reserva.Reserva;
import Model.Pousada.Reserva.ReservaGerenciar;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe responsável por gerenciar os quartos da pousada.
 */

public class QuartoGerenciar {

    private static QuartoGerenciar quartoGerenciar = new QuartoGerenciar();
    private static Quarto[] listaQuartos = new Quarto[10];
    private static final String ARQUIVOS_QUARTOS_JSON = "src/arquivos/quartos.json";

    public QuartoGerenciar() {
        carregarDados();
    }

    public static Quarto[] getListaQuartos() {
        QuartoGerenciar quartoGerenciar = new QuartoGerenciar();
        quartoGerenciar.carregarDados();
        return listaQuartos;
    }

    public static void setListaQuartos(Quarto[] listaQuartos) {
        QuartoGerenciar.listaQuartos = listaQuartos;
    }

    public static Quarto selecionarQuarto(int i, ArrayList<String> periodoDeDatasSelecionado){

        quartoGerenciar.carregarDados();

        Quarto quarto = new Quarto();

        quarto.setNumeroQuarto(listaQuartos[i].getNumeroQuarto());
        quarto.setApelido(listaQuartos[i].getApelido());
        quarto.setTipo(listaQuartos[i].getTipo());
        quarto.setArrayDatas(periodoDeDatasSelecionado);
        quarto.setDisponibilidade(false);
        quarto.setCapacidade(listaQuartos[i].getCapacidade());
        quarto.setPreco(listaQuartos[i].getPreco());
        quarto.setDespesaQuarto(listaQuartos[i].getDespesaQuarto());

        return quarto;
    }

    public void salvarDados() {
        try (Writer writer = new FileWriter(ARQUIVOS_QUARTOS_JSON)) {
            Gson gson = new Gson();
            gson.toJson(listaQuartos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(ARQUIVOS_QUARTOS_JSON)));
            Gson gson = new Gson();
            listaQuartos = gson.fromJson(json, Quarto[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean descobrirSeQuartoEstaDisponivel(ArrayList<String> datasDoPeriodo, int numeroDoQuarto) throws ParseException {
        ArrayList<Reserva> listaReservas = ReservaGerenciar.getListaReservas();
        boolean quartoDisponivel = true;

        // percorrer datas do periodo da reserva
        for (String dataIndividualDoPeriodoDaReserva : datasDoPeriodo) {
            Date dataLocal = new SimpleDateFormat("dd/MM/yyyy").parse(dataIndividualDoPeriodoDaReserva);

            // percorrer lista de reservas
            for (Reserva reserva : listaReservas) {
                if (reserva.getQuarto().getNumeroQuarto() == numeroDoQuarto &&
                        !reserva.getDataSaida().before(dataLocal) &&
                        !reserva.getDataEntrada().after(dataLocal)) {
                    // Quarto está ocupado nessa data
                    quartoDisponivel = false;
                    break;
                }
            }

            if (!quartoDisponivel) {
                break;
            }
        }

        if (quartoDisponivel) {
            System.out.println("Quarto disponível para as datas fornecidas");
            return true;
        } else {
            System.out.println("Quarto indisponível para as datas fornecidas");
            return false;
        }
    }

}
