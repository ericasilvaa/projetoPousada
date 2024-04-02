package Model.Pousada.Reserva;

import Model.Pousada.Quarto;
import Model.Pousada.Reserva.Reserva;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static Model.Pessoas.ClienteGerenciar.getListaClientes;

/**
 * Classe responsável por gerenciar as reservas
 */
public class ReservaGerenciar {
    private static ArrayList<Reserva> listaReservas = new ArrayList<>();
    private static final String PATH_ARQUIVO_RESERVAS_JSON = "src/arquivos/reservas.json";

    public ReservaGerenciar() {
        carregarDados();
    }

    public static ArrayList<Reserva> getListaReservas() {
        ReservaGerenciar reservaGerenciar = new ReservaGerenciar();
        reservaGerenciar.carregarDados();
        return listaReservas;
    }

    public void imprimirReservas() {
        Iterator<Reserva> reservaIterator = listaReservas.iterator();
        int contador = 0;
        while (reservaIterator.hasNext()) {
            Reserva reserva = reservaIterator.next();
            System.out.println(contador + " - \n" + reserva);
            contador++;
        }
    }

    public void imprimirReservasComAte30DiasDeHoje(){
        Date hoje = new Date();
        boolean trueSeAteUmMesAntes ;

        for (Reserva listaReserva : listaReservas) {
            Date dataComparar = listaReserva.getDataEntrada();
            if(ReservaGerenciar.calcularUmMesAntesInicioReserva(dataComparar)){
                System.out.println(listaReserva.toString());
            }
        }
    }

    public void cadastroReserva(Date dataDeEntrada, Date dataDeSaida, Quarto quartoInstanciado,
                                Double precoReserva, String observacoes, int idCliente) {

        // criação da reserva
        Reserva novaReserva = new Reserva();
        novaReserva.setDataEntrada(dataDeEntrada);
        novaReserva.setDataSaida(dataDeSaida);
        novaReserva.setQuarto(quartoInstanciado);
        novaReserva.setPreco(precoReserva);
        novaReserva.setDescricao(observacoes);
        novaReserva.setCliente(getListaClientes().get(idCliente));
        System.out.println(novaReserva.toString());

        boolean cadastroOcorreu = listaReservas.add(novaReserva);

        if (cadastroOcorreu) {
            System.out.println("Reserva cadastrada");
            salvarDados();
        }

        salvarDados();
    }

    public static Date calcularUmaSemanaAntesInicioReserva(int i){
        Date umaSemanaAntes;
        umaSemanaAntes = getListaReservas().get(i).getDataEntrada();

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(umaSemanaAntes);
        calendario.add(Calendar.DATE, -7);

        umaSemanaAntes = calendario.getTime();

        return umaSemanaAntes;
    }

    public static Date calcularUmMesAntesInicioReserva(int i){
        Date umMesAntes;
        umMesAntes = getListaReservas().get(i).getDataEntrada();

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(umMesAntes);
        calendario.add(Calendar.DATE, -30);

        umMesAntes = calendario.getTime();

        return umMesAntes;
    }

    public static boolean calcularUmaSemanaAntesInicioReserva(Date dataInicial) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataInicial);
        calendario.add(Calendar.DATE, -7);
        Date umaSemanaAntes = calendario.getTime();

        long diferenca = dataInicial.getTime() - umaSemanaAntes.getTime();

        long diferencaEmDias = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);

        // true se hoje está até uma semana depois de umaSemanaAnte
        return diferencaEmDias <= 7;
    }

    public static boolean calcularUmMesAntesInicioReserva(Date hoje) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(hoje);
        calendario.add(Calendar.DATE, -7);
        Date umaSemanaAntes = calendario.getTime();

        long diferenca = hoje.getTime() - umaSemanaAntes.getTime();

        long diferencaEmDias = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);

        // true se hoje está até um mes depois de umaSemanaAnte
        return diferencaEmDias <= 30;
    }


    public void salvarDados() {
        try (Writer writer = new FileWriter(PATH_ARQUIVO_RESERVAS_JSON)) {
            Gson gson = new Gson();
            gson.toJson(listaReservas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(PATH_ARQUIVO_RESERVAS_JSON)));
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(EstadoAbstratoReserva.class, new InstanceCreator<EstadoAbstratoReserva>() {
                        @Override
                        public EstadoAbstratoReserva createInstance(Type type) {
                            // Retorna uma instância concreta da interface EstadoAbstratoReserva
                            return new ReservaPreliminarEstado();
                        }
                    })
                    .create();
            listaReservas = gson.fromJson(json, new TypeToken<ArrayList<Reserva>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static{
        ReservaGerenciar reservaGerenciar = new ReservaGerenciar();
        reservaGerenciar.carregarDados();
        for (Reserva listaReserva : getListaReservas()) {
            if(listaReserva.getEstado().toString().equals("Reserva Preliminar")){
                // pegar data inicial
                Date dataInicial= listaReserva.getDataEntrada();

                // descobrir se data inicial
                boolean seDiaUmaSemanaAntes = ReservaGerenciar.calcularUmaSemanaAntesInicioReserva(dataInicial);

                if (seDiaUmaSemanaAntes){

                }
            }
        }
    }
}


