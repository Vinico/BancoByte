package br.univates.banco;

import br.univates.alexandria.persistence.DataBaseConnectionManager;
import br.univates.alexandria.persistence.DataBaseException;
import br.univates.banco.apresentacao.TelaMenuPrincipal;
import br.univates.banco.persistencia.ContaBancariaDaoPostgres;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta versão implementa todas opções do menu com classes internas e anônimas
 * e também implementa um DAO que gerencia várias contas bancárias
 * @author mouriac
 */
public class Main
{

    public static void main(String[] args)
    {
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            db.connectionTest();
            
            new ContaBancariaDaoPostgres(); // carregar número da conta
        
            TelaMenuPrincipal tela = new TelaMenuPrincipal();
            tela.exibir(); 
        } 
        catch (DataBaseException ex)
        {
            System.out.println("Não conectou com o banco de dados");
        }
        finally
        {
            System.exit(0);
        }
    }
}
