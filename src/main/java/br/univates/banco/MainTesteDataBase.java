package br.univates.banco;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DataBaseConnectionManager;
import br.univates.alexandria.persistence.DataBaseException;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainTesteDataBase {
    public static void main(String[] args) {

        CorrentistaDao dao = new CorrentistaDao();
        /*try {
            dao.create(new Correntista(new Cpf("05950052099"), "alexsandro", "Canudos do vale"));
        } catch (InvalidEntryException e) {
            throw new RuntimeException(e);
        }*/

        Correntista corre = null;
        try {
            corre = new Correntista(new Cpf("05950052099"), "alexsandro", "Canudos do vale");
        } catch (InvalidEntryException e) {
            dao.delete(corre);
            throw new RuntimeException(e);
        }
//        System.out.println(corre.toString());


    }
}
