package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.alexandria.Cpf;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;
import java.util.Collections;

public class CorrentistaDao
{
    public CorrentistaDao()
    {
    
    }

    // Create Read Update Delete
    public void create(Correntista correntista)
    {
        ArrayList<Correntista> listaCorrentistas = readAll();
        listaCorrentistas.add(correntista);
        
        // salvar em arquivo texto
        Arquivo a = new Arquivo("correntista.dat");
        if (a.abrirEscrita())
        {
            for (Correntista c: listaCorrentistas)
            {
                a.escreverLinha( c.toString() );
            }
        }
        a.fecharArquivo();
    }
    
    public Correntista read( String cpf )
    {
        return null;
    }
    
    public Correntista read( Cpf cpf )
    {
        ArrayList<Correntista> listaCorrentistas = readAll();
        
        Correntista correntistaAchado = null;
        Correntista fake = new Correntista( cpf, "", "" );
        int index = listaCorrentistas.indexOf(fake);
        if (index >= 0)
        {
            correntistaAchado = listaCorrentistas.get(index);
        }
        return correntistaAchado;
    }
    
    public ArrayList<Correntista> readAll()
    {
        //Collections.sort(listaCorrentistas);
        //return listaCorrentistas;
        return null;
    }
    
    
}
