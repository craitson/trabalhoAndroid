package unidavi.com.br.trabalhofinal.model;

public class Foto {

    private String fileName;
    private String uf;
    private String cidade;
    private String bairro;

    public Foto(String fileName, String uf, String cidade, String bairro) {
        this.fileName = fileName;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public String getfileName() {
        return fileName;
    }

    public void setfileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
