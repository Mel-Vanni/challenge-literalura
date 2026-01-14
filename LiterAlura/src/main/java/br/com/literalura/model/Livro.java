package br.com.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer downloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, String idioma, Integer downloads, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloads = downloads;
        this.autor = autor;
    }

    public String getIdioma() { return idioma; }

    @Override
    public String toString() {
        return """
        Título: %s
        Autor: %s
        Idioma: %s
        Downloads: %d
        """.formatted(titulo, autor.getNome(), idioma, downloads);
    }
}
