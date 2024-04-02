package Model.Pousada.Reserva;

import Model.Pousada.Reserva.Reserva;
import Model.Pousada.Reserva.ReservaPreliminarEstado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * Interface que recebe solicitações do contexto do padrão State.
 * Define os métodos para transição de estados de uma reserva.
 * Se uma classe representar algum estado de uma reserva, essa classe deve implementar essa interface.
 */
public interface EstadoAbstratoReserva {

    /**
     * Método abstrato responsável por definir estado atual da reserva como preliminar
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Retorna um novo estado ativo do objeto caso seja possível a mudança de estado interno do objeto
     */
    EstadoAbstratoReserva definirComoPreliminar(Reserva reserva);

    /**
     * Método abstrato responsável por definir estado atual da reserva como definitiva
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Retorna um novo estado ativo do objeto caso seja possível a mudança de estado interno do objeto
     */
    EstadoAbstratoReserva definirComoDefinitiva(Reserva reserva);
    /**
     * Método abstrato responsável por definir estado atual da reserva como cancelada
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Retorna um novo estado ativo do objeto caso seja possível a mudança de estado interno do objeto
     */
    EstadoAbstratoReserva definirComoCancelada(Reserva reserva);

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(EstadoAbstratoReserva.class, new InstanceCreator<EstadoAbstratoReserva>() {
                @Override
                public EstadoAbstratoReserva createInstance(Type type) {
                    // Retorna uma instância concreta da interface EstadoAbstratoReserva
                    return new ReservaPreliminarEstado();
                }
            })
            .create();


}
