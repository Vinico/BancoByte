package br.univates.banco.persistencia;

import br.univates.alexandria.persistence.IDao;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;

public class DaoFactory
{
    public static IDao<Correntista,String> createCorrentistaDao()
    {
        return new CorrentistaDaoPostgres();
    }
    
    public static IDao<ContaBancaria,Integer> createContaBancariaDao()
    {
        return new ContaBancariaDaoPostgres();
    }
}
