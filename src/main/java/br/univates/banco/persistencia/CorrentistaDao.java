package br.univates.banco.persistencia;

import br.univates.alexandria.Cpf;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;
import java.util.Collections;

public class CorrentistaDao
{
    private static ArrayList<Correntista> listaCorrentistas = new ArrayList<Correntista>();

    public CorrentistaDao()
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





    public Correntista read( Cpf cpf )
    {
        Correntista achei = null;
        Correntista fake = new Correntista( cpf, "", "" );
        int index = listaCorrentistas.indexOf(fake);
        if (index >= 0)
        {
            achei = listaCorrentistas.get(index);
        }
        return achei;
    }

    public void update(Correntista correntista){

        if (correntista != null) {
            Correntista fake = new Correntista( correntista.getCpf(), "", "" );
            int index = listaCorrentistas.indexOf(fake);
            listaCorrentistas.set(index, correntista);

        }

    }

    public void delete(Cpf cpf){
        Correntista fake = new Correntista( cpf, "", "" );
        int index = listaCorrentistas.indexOf(fake);
        listaCorrentistas.remove(index);
    }

    public ArrayList<Correntista> readAll()
    {
        Collections.sort(listaCorrentistas);
        return listaCorrentistas;
    }
    
    
}
