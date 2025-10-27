package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DataBaseConnectionManager;
import br.univates.alexandria.persistence.DataBaseException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.ContaBancariaEspecial;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.negocio.Transacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ContaBancariaDaoPostgres
{
    
    public ContaBancariaDaoPostgres()
    {
        if (ContaBancaria.getSequencial() == 0) // atualizar sequencial
        {
            try
            {
                ArrayList<ContaBancaria> lista = this.readAll();
                ContaBancaria.setSequencial( lista.getLast().getNumero() );
            }
            catch (NoSuchElementException e)
            {
                // Não tem nada para fazer. A primeira conta será a conta 1.
            }
        }
    }
    
    public ContaBancaria read( int numero )
    {
        ContaBancaria contaAchada = null;
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            ResultSet rs = db.runPreparedQuerySQL("SELECT * FROM conta WHERE numero_conta=?;", numero);
            if (rs.isBeforeFirst()) // significa que retornou algum resultado
            {
                rs.next();
                char tipo_conta = rs.getString("tipo_conta").charAt(0);
                String cpf_correntista = rs.getString("cpf_correntista");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");
                
                if (tipo_conta == 'S')
                {
                    contaAchada = new ContaBancaria( numero, 
                                                     new CorrentistaDaoPostgres().read(cpf_correntista),
                                                     saldo,
                                                     readTransacoes(numero) );
                }
                else
                {
                    contaAchada = new ContaBancariaEspecial( numero, 
                                                             new CorrentistaDaoPostgres().read(cpf_correntista),
                                                             saldo,
                                                             readTransacoes(numero),
                                                             limite );
                }
            }
        } 
        catch (DataBaseException | SQLException ex)
        {
            System.out.println("Conta não achada");
        } 
        return contaAchada;
    }
    
    
    public void create( ContaBancaria conta )
    {
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            
            double limite = 0;
            if (conta instanceof ContaBancariaEspecial)
            {
                limite = ((ContaBancariaEspecial)conta).getLimite();
            }
            
            db.runPreparedSQL("INSERT INTO conta VALUES (?,?,?,?,?);", 
                               conta.getNumero(),
                               conta.getTipo(),
                               conta.getCorrentista().getCpf().getNumeroCpf(),
                               conta.getSaldo(),
                               limite );
            
            // Na prática isso não será executado, pois contas novas não tem transações
            ArrayList<Transacao> listaTransacoes = conta.getListaTransacoes();
            for (Transacao t: listaTransacoes)
            {
                db.runPreparedSQL("INSERT INTO transacao VALUES (?,?,?,?,?,?);", 
                                   conta.getNumero(),
                                   t.getData(),
                                   t.getDescricao(),
                                   t.getValor(),
                                   t.getTipoOperacao(),
                                   t.getSaldo() );
            }
                    
        } 
        catch (DataBaseException ex)
        {
            System.out.println("Chave duplicada");
        }
    }
    
    
    
    public void update(ContaBancaria conta)
    {
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            
            db.runPreparedSQL("UPDATE conta "
                            + "SET saldo=? "
                            + "WHERE numero_conta=?;", 
                            conta.getSaldo(),
                            conta.getNumero() );
            
            db.runPreparedSQL("DELETE FROM transacao WHERE numero_conta=?;", 
                               conta.getNumero() );
            
            ArrayList<Transacao> listaTransacoes = conta.getListaTransacoes();
            for (Transacao t: listaTransacoes)
            {
                db.runPreparedSQL("INSERT INTO transacao VALUES (?,?,?,?,?,?);", 
                                   conta.getNumero(),
                                   t.getData(),
                                   t.getDescricao(),
                                   t.getValor(),
                                   t.getTipoOperacao(),
                                   t.getSaldo() );
            }
            
        } 
        catch (DataBaseException ex)
        {
            System.out.println("Correntista não achado ----");
        } 
    }
    
    public ArrayList<ContaBancaria> readAll()
    {
        ArrayList<ContaBancaria> listaContas = new ArrayList();
        
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            ResultSet rs = db.runQuerySQL("SELECT * FROM conta;");
            if (rs.isBeforeFirst()) // significa que retornou algum resultado
            {
                rs.next();
                while (!rs.isAfterLast())
                {
                    int numero = rs.getInt("numero_conta");
                    char tipo_conta = rs.getString("tipo_conta").charAt(0);
                    String cpf_correntista = rs.getString("cpf_correntista");
                    double saldo = rs.getDouble("saldo");
                    double limite = rs.getDouble("limite");

                    ContaBancaria conta;
                    if (tipo_conta == 'S')
                    {
                        conta = new ContaBancaria( numero, 
                                                   new CorrentistaDaoPostgres().read(cpf_correntista),
                                                   saldo,
                                                   readTransacoes(numero) );
                    }
                    else
                    {
                        conta = new ContaBancariaEspecial( numero, 
                                                           new CorrentistaDaoPostgres().read(cpf_correntista),
                                                           saldo,
                                                           readTransacoes(numero),
                                                           limite );
                    }
                    listaContas.add(conta);
                    rs.next();
                }
            }
        } 
        catch (DataBaseException | SQLException ex)
        {
            System.out.println("Conta não achada");
        } 
        
        
        return listaContas;
    }
    
    public ArrayList<ContaBancaria> readAll( Correntista correntista )
    {
        ArrayList<ContaBancaria> listaContas = readAll();
        ArrayList<ContaBancaria> listaContasAux = new ArrayList();
        
        for (ContaBancaria conta: listaContas)
        {
            if (conta.getCorrentista().equals(correntista))
            {
                listaContasAux.add(conta);
            }
        }
        Collections.sort(listaContasAux);
        return listaContasAux;
    }
    
    
    private ArrayList<Transacao> readTransacoes( int numeroConta )
    {
        ArrayList<Transacao> listaTransacoes = new ArrayList();
        
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte", "vinicius", "123456");
            ResultSet rs = db.runPreparedQuerySQL("SELECT * FROM transacao "
                    + "WHERE numero_conta=? ORDER BY numero_conta;",
                    numeroConta );
            if (rs.isBeforeFirst()) // significa que retornou algum resultado
            {
                rs.next();
                while (!rs.isAfterLast())
                {
                    LocalDate dt = rs.getDate("data_transacao").toLocalDate();
                    String descricao = rs.getString("descricao");
                    double valor = rs.getDouble("valor");
                    char tipo_operacao = rs.getString("tipo_operacao").charAt(0);
                    double saldo = rs.getDouble("saldo");

                    Transacao t = new Transacao( dt, descricao, valor, tipo_operacao, saldo );
                    listaTransacoes.add(t);
                    
                    rs.next();
                }
            }
        } 
        catch (DataBaseException | SQLException  ex)
        {
            System.out.println("Transacao não achada");
        } 
        
        return listaTransacoes;
    }
    
    
}
