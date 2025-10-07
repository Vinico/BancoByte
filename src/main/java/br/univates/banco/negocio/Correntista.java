package br.univates.banco.negocio;

import br.univates.alexandria.Cpf;

public class Correntista implements Comparable<Correntista>
{
    private Cpf cpf;
    private String nome;
    private String municipio;

    public Correntista(Cpf cpf, String nome, String municipio)
    {
        this.cpf = cpf;
        this.setNome(nome);
        this.setMunicipio(municipio);
    }

    public Cpf getCpf()
    {
        return cpf;
    }
    
    public String getCpfFormatado()
    {
        return cpf.getNumeroCpfFormatado();
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        if (!nome.isBlank())
        {
            this.nome = nome;
        }
    }

    public String getMunicipio()
    {
        return municipio;
    }

    public void setMunicipio(String municipio)
    {
        if (!municipio.isBlank())
        {
            this.municipio = municipio;
        }
    }

    @Override
    public int compareTo(Correntista c)
    {
        //return this.nome.compareToIgnoreCase( c.getNome() );
        return this.cpf.getNumeroCpf().compareTo( c.getCpf().getNumeroCpf() );
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean ok = false;
        if (obj != null && obj instanceof Correntista)
        {
            Correntista c = (Correntista)obj;
            ok = (this.cpf.equals(c.cpf));
        }

        return ok;
    }

    @Override
    public String toString() {
        return nome + " : " + cpf.getNumeroCpf();
    }
}
