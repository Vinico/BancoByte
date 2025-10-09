package br.univates.alexandria.menu3;

public class MenuItem
{
    private char atalho;
    private String descricao;
    private MenuProcedimento procedimento;

    public MenuItem(char atalho, String descricao, MenuProcedimento procedimento)
    {
        this.atalho = atalho;
        this.descricao = descricao;
        this.procedimento = procedimento;
    }

    public char getAtalho()
    {
        return atalho;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public String getItemFormatado()
    {
        return "["+atalho+"] "+descricao.toUpperCase().charAt(0)+descricao.toLowerCase().substring(1);
    }
    
    public void executar()
    {
        procedimento.executarProcedimento();
    }
}
