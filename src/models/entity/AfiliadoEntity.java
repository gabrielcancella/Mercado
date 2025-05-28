package models.entity;


public class AfiliadoEntity {
    private long id;
    private String nome;
    private String sobrenome;

    public AfiliadoEntity(String nome, String sobrenome, String cpf, String nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    private String cpf;
    private String nascimento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
