package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;
import java.util.Collections;

public class CorrentistaDao
{
    private final String path = "db/correntista.dat";

    public CorrentistaDao()
    {
    
    }

    // Create Read Update Delete
    public void create(Correntista correntista)
    {
        ArrayList<Correntista> listaCorrentistas = readAll();
        listaCorrentistas.add(correntista);
        
        // salvar em arquivo texto
        Arquivo a = new Arquivo(path);
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
        ArrayList<Correntista> listaCorrentistas = new ArrayList<>();
        Arquivo a = new Arquivo(path);
        a.abrirLeitura();


        String linha = a.lerLinha();
        while (linha != null) {
            if (!linha.isEmpty()) {
                String[] splis = linha.split(";");
                try {
                    listaCorrentistas.add(new Correntista(new Cpf(splis[0], true), splis[1], splis[2]));
                } catch (InvalidEntryException e) {
                    throw new RuntimeException(e);
                }
            }
            linha = a.lerLinha();
        }

        a.fecharArquivo();
        return listaCorrentistas;
    }


    
    
}
