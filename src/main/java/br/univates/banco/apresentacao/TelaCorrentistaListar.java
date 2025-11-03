package br.univates.banco.apresentacao;

import br.univates.alexandria.Entrada;
import br.univates.alexandria.persistence.Filter;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;
import java.util.ArrayList;

public class TelaCorrentistaListar
{
    
    public void exibir()
    {
        CorrentistaDaoPostgres dao = new CorrentistaDaoPostgres();
        String cidade = Entrada.leiaString("Cidade:");
        
        ArrayList<Correntista> lista = dao.readAll( new Filter<Correntista>() {
            @Override
            public boolean isAccept(Correntista c)
            {
                if (c.getMunicipio().equalsIgnoreCase(cidade))
                {
                    return true;
                }
                return false;
            }
        } );
        
        for (Correntista c: lista)
        {
            System.out.println( c.getCpfFormatado()+"\t"+String.format("%-20s", c.getNome())+"\t"+c.getMunicipio() );
        }
        System.out.println();
    }
}
