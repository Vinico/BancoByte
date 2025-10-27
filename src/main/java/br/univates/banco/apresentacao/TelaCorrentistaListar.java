package br.univates.banco.apresentacao;

import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;
import java.util.ArrayList;

public class TelaCorrentistaListar
{
    
    public void exibir()
    {
        CorrentistaDaoPostgres dao = new CorrentistaDaoPostgres();
        ArrayList<Correntista> lista = dao.readAll();
        for (Correntista c: lista)
        {
            System.out.println( c.getCpfFormatado()+"\t"+String.format("%-20s", c.getNome())+"\t"+c.getMunicipio() );
        }
        System.out.println();
    }
}
