package br.univates.alexandria.menu2;

public abstract class MenuItem
{
    private char atalho;
    private String descricao;

    public MenuItem(char atalho, String descricao)
    {
        this.atalho = atalho;
        this.descricao = descricao;
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
    
    public abstract void executar();
}
