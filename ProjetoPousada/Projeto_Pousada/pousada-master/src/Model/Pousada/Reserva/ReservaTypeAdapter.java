package Model.Pousada.Reserva;

import Model.Pousada.Reserva.Reserva;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Classe que controla como o estado da reserva é serializado e deserializado em JSON
 */
public class ReservaTypeAdapter extends TypeAdapter<Reserva> {
    @Override
    public void write(JsonWriter out, Reserva reserva) throws IOException {
        out.beginObject();
        out.name("estado").value(reserva.getEstado().getClass().getName());
        out.endObject();
    }

    @Override
    public Reserva read(JsonReader in) throws IOException {
        Reserva reserva = new Reserva();

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("estado")) {
                String className = in.nextString();
                try {
                    Class<?> clazz = Class.forName(className);
                    EstadoAbstratoReserva estado = (EstadoAbstratoReserva) clazz.newInstance();
                    reserva.setEstado(estado);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                in.skipValue();
            }
        }
        in.endObject();

        return reserva;
    }
}

