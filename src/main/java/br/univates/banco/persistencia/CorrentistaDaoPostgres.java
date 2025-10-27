package br.univates.banco.persistencia;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DataBaseConnectionManager;
import br.univates.alexandria.persistence.DataBaseException;
import br.univates.banco.negocio.Correntista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorrentistaDaoPostgres
{
    

    public CorrentistaDaoPostgres()
    {
    
    }

    // Create Read Update Delete
    public void create(Correntista correntista)
    {
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            db.runPreparedSQL("INSERT INTO correntista VALUES (?,?,?);", 
                    correntista.getCpf().getNumeroCpf(),
                    correntista.getNome(),
                    correntista.getMunicipio());
        } 
        catch (DataBaseException ex)
        {
            System.out.println("Chave duplicada");
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
    
    public Correntista read( Cpf cpf )
    {
        Correntista correntistaAchado = null;
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            ResultSet rs = db.runPreparedQuerySQL("SELECT * FROM correntista WHERE cpf_correntista=?;", cpf.getNumeroCpf());
            if (rs.isBeforeFirst()) // significa que retornou algum resultado
            {
                rs.next();
                //String cpf_correntista = rs.getString("cpf_correntista");
                String nome = rs.getString("nome");
                String municipio = rs.getString("municipio");
                
                correntistaAchado = new Correntista( cpf, nome, municipio );
            }
        } 
        catch (DataBaseException | SQLException ex)
        {
            System.out.println("Correntista não achado");
        } 
        return correntistaAchado;
    }
    
    public void update(Correntista correntista)
    {
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            db.runPreparedSQL("UPDATE correntista "
                            + "SET nome=?, municipio=? "
                            + "WHERE cpf_correntista=?;", 
                            correntista.getNome(),
                            correntista.getMunicipio(),
                            correntista.getCpf().getNumeroCpf() );
            
        } 
        catch (DataBaseException ex)
        {
            System.out.println("Correntista não achadao");
        } 
    }
    
    public void delete(Correntista correntista)
    {
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            db.runPreparedSQL("DELETE FROM correntista WHERE cpf_correntista=?;", correntista.getCpf().getNumeroCpf() );
            
        } 
        catch (DataBaseException ex)
        {
            System.out.println("Correntista não achadao");
        } 
    }

    public ArrayList<Correntista> readAll()
    {
        ArrayList<Correntista> listaCorrentistas = new ArrayList();
        
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            ResultSet rs = db.runQuerySQL("SELECT * FROM correntista ORDER BY cpf_correntista;");
            if (rs.isBeforeFirst()) // significa que retornou algum resultado
            {
                rs.next();
                while (!rs.isAfterLast())
                {
                    String cpf_correntista = rs.getString("cpf_correntista");
                    String nome = rs.getString("nome");
                    String municipio = rs.getString("municipio");

                    Correntista correntista = new Correntista( new Cpf(cpf_correntista), nome, municipio );
                    listaCorrentistas.add(correntista);
                    
                    rs.next();
                }
            }
        } 
        catch (DataBaseException | SQLException | InvalidEntryException ex)
        {
            System.out.println("Correntista não achado");
        } 
        
        return listaCorrentistas;
    }
    
    
}
