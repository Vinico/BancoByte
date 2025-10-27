package br.univates.banco;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DataBaseConnectionManager;
import br.univates.alexandria.persistence.DataBaseException;
import br.univates.banco.negocio.Correntista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTesteDataBase
{
    public static void main(String[] args)
    {
        DataBaseConnectionManager db;
        try
        {
            db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte","postgres","postgres");
            
            db.connectionTest();
            
            Correntista c = new Correntista( new Cpf("03625221039"), "João Schneider", "Passo Fundo");
            
            //db.runSQL("INSERT INTO correntista VALUES ('03765569060','Maria Cardoso','Estrela');");
            //db.runSQL("INSERT INTO correntista VALUES ('"+c.getCpf().getNumeroCpf()+
            //          "','"+c.getNome()+"','"+c.getMunicipio() +"');");
            
            //db.runPreparedSQL("INSERT INTO correntista VALUES (?,?,?);",
            //        c.getCpf().getNumeroCpf(), c.getNome(), c.getMunicipio());
            
            ResultSet rs = db.runQuerySQL("SELECT * FROM correntista;");
            
            if (rs.isBeforeFirst()) // retornou alguma coisa
            {
                rs.next();
                while (!rs.isAfterLast())
                {
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String municipio = rs.getString("municipio");

                    Correntista c2 = new Correntista( new Cpf(cpf), nome, municipio );
                    System.out.println(c2.toString());
                    rs.next();
                }
            }
            db.closeConnection();
        } 
        catch (DataBaseException ex)
        {
            System.out.println( ex.getMessage() );
        } 
        catch (InvalidEntryException ex)
        {
            System.out.println("CPF Furado");
        } 
        catch (SQLException ex)
        {
            System.out.println("Deu pau no RS");
        }
        
        
        
    }
}
