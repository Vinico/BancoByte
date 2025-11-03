package br.univates.banco.persistencia;

import br.univates.alexandria.persistence.Filter;
import br.univates.banco.negocio.Correntista;

public class Filtro implements Filter<Correntista>
{

    @Override
    public boolean isAccept( Correntista c )
    {
        if (c.getMunicipio().equalsIgnoreCase("olinda"))
        {
            return true;
        }
        return false;
    }
    
}
