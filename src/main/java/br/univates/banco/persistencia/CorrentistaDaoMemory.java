package br.univates.banco.persistencia;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;
import java.util.Collections;

public class CorrentistaDaoMemory
{
    private static ArrayList<Correntista> listaCorrentistas;

    public CorrentistaDaoMemory()
    {
        if (listaCorrentistas == null)
        {
            listaCorrentistas = new ArrayList();
        }
    }

    // Create Read Update Delete
    public void create(Correntista correntista)
    {
        // verificação de integridade contralada diretamente pelo sistema
        Correntista c = read( correntista.getCpf() );
        if (c == null) // o novo correntista não pode existir na base
        {
            listaCorrentistas.add(correntista);
        }
    }
    
    public Correntista read( String cpf )
    {
        Correntista correntistaAchado;
        try
        {
            correntistaAchado = read( new Cpf(cpf) );
        } 
        catch (InvalidEntryException ex)
        {
            correntistaAchado = null;
        }
        return correntistaAchado;
    }
    
    public void update(Correntista correntista)
    {
        // verificação de integridade contralada diretamente pelo sistema
        int index = listaCorrentistas.indexOf(correntista);
        if (index >= 0)
        {
            listaCorrentistas.set(index, correntista);
        }
    }
    
    public void delete(Correntista correntista)
    {
        // verificação de integridade contralada diretamente pelo sistema
        ContaBancariaDaoPostgres dao = new ContaBancariaDaoPostgres();
        ArrayList<ContaBancaria> listaContas = dao.readAll(correntista);
        
        if (listaContas.size() == 0) // só pode deletar se não tem conta bancária
        {
            int index = listaCorrentistas.indexOf(correntista);
            if (index >= 0)
            {
                listaCorrentistas.remove(index);
            }
        }
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
