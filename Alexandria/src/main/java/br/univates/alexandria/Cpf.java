package br.univates.alexandria;

public class Cpf
{
    private String cpf;
    private boolean permitidoInvalido;

    public Cpf(String cpf) throws InvalidEntryException
    {
        this.permitidoInvalido = false;
        this.setCpf(cpf);
    }
    
    public Cpf(String cpf, boolean permitidoInvalido) throws InvalidEntryException
    {
        this.permitidoInvalido = permitidoInvalido;
        this.setCpf(cpf);
    }
    
    public void setCpf(String sCpf) throws InvalidEntryException
    {
        if (permitidoInvalido)
        {
            this.cpf = sCpf.replaceAll("[^0-9]", "");
        }
        else if (Util.validarCPF(sCpf))
        {
            this.cpf = sCpf.replaceAll("[^0-9]", "");
        }
        else
        {
            throw new InvalidEntryException("CPF inválido");
        }
    }
    
    public boolean isValido()
    {
        return Util.validarCPF(cpf);
    }
    
    public String getNumeroCpf()
    {
        return cpf;
    }
    
    public String getNumeroCpfFormatado()
    {
        String aux = cpf;
        if (this.isValido())
        {
            aux = cpf.substring(0, 3)+"."+cpf.substring(3, 6)+
                "."+cpf.substring(6, 9)+"-"+cpf.substring(9);
        }
        return aux;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean ok = false;
        if (obj != null && obj instanceof Cpf)
        {
            Cpf c = (Cpf)obj;
            ok = (this.cpf.equals(c.cpf));   
        }
        return ok;
    }
    
}
