package br.univates.banco.persistencia;

import br.univates.alexandria.Cpf;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;
import java.util.Collections;

public class CorrentistaDaoRam
{
    private static ArrayList<Correntista> listaCorrentistas;

    public CorrentistaDaoRam()
    {
        if (listaCorrentistas == null)
        {
            listaCorrentistas = new ArrayList();
        }
    }

    // Create Read Update Delete
    public void create(Correntista correntista)
    {
        listaCorrentistas.add(correntista);
    }
    
    public Correntista read( String cpf )
    {
        return null;
    }
    
    public Correntista read( Cpf cpf )
    {
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
        Collections.sort(listaCorrentistas);
        return listaCorrentistas;
    }
    
    
}
