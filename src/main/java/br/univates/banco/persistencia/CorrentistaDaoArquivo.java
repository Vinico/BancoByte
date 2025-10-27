package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;
import java.util.Collections;

public class CorrentistaDaoArquivo
{
    

    public CorrentistaDaoArquivo()
    {
    
    }

    // Create Read Update Delete
    public void create(Correntista correntista)
    {
        // verificação de integridade contralada diretamente pelo sistema
        Correntista c = read( correntista.getCpf() );
        if (c == null) // o novo correntista não pode existir na base
        {
            ArrayList<Correntista> listaCorrentistas = readAll();
            listaCorrentistas.add(correntista);
            salvarArquivo( listaCorrentistas );
        }
    }
    
    private void salvarArquivo( ArrayList<Correntista> listaCorrentistas )
    {
        Arquivo a = new Arquivo("correntista.dat");
        if (a.abrirEscrita())
        {
            for (Correntista i: listaCorrentistas)
            {
                a.escreverLinha( i.toString() );
            }
            a.fecharArquivo();
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
        ArrayList<Correntista> listaCorrentistas = readAll();
        int index = listaCorrentistas.indexOf(correntista);
        if (index >= 0)
        {
            listaCorrentistas.set(index, correntista);
            salvarArquivo(listaCorrentistas);
        }
    }
    
    public void delete(Correntista correntista)
    {
        // verificação de integridade contralada diretamente pelo sistema
        ContaBancariaDaoPostgres dao = new ContaBancariaDaoPostgres();
        ArrayList<ContaBancaria> listaContas = dao.readAll(correntista);
        
        if (listaContas.size() == 0) // só pode deletar se não tem conta bancária
        {
            ArrayList<Correntista> listaCorrentistas = readAll();
            int index = listaCorrentistas.indexOf(correntista);
            if (index >= 0)
            {
                listaCorrentistas.remove(index);
            }
            salvarArquivo(listaCorrentistas);
        }
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
        ArrayList<Correntista> listaCorrentistas = new ArrayList();
        
        Arquivo a = new Arquivo("correntista.dat");
        if (a.abrirLeitura())
        {
            String linha = a.lerLinha();
            while (linha != null)
            {
                String[] campo = linha.split(";");
                
                try
                {
                    Correntista c = new Correntista( new Cpf(campo[0]), campo[1], campo[2] );
                    listaCorrentistas.add(c);
                } 
                catch (InvalidEntryException ex)
                {
                    // correntista ignorado caso CPF sejá inválido. Fato que espera-se que não ocorra.
                }

                linha = a.lerLinha();
            }
            a.fecharArquivo();
        }
        Collections.sort(listaCorrentistas);
        return listaCorrentistas;
    }
    
    
}
