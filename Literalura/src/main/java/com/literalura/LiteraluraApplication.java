package com.literalura;

import com.literalura.model.Livro;
import com.literalura.repository.LivroRepository;
import com.literalura.repository.AutorRepository;
import com.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private AutorRepository autorRepo;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU LITERALURA ===");
            System.out.println("1. Buscar livro por título");
            System.out.println("2. Listar todos os livros");
            System.out.println("3. Listar livros por idioma");
            System.out.println("4. Listar todos os autores");
            System.out.println("5. Listar autores vivos em determinado ano");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    Livro livro = gutendexService.buscarLivroPorTitulo(titulo);
                    if (livro != null) {
                        autorRepo.save(livro.getAutor());
                        livroRepo.save(livro);
                        System.out.println("Livro adicionado: " + livro);
                    }
                }
                case 2 -> livroRepo.findAll().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Digite o idioma: ");
                    String idioma = scanner.nextLine();
                    livroRepo.findByIdioma(idioma).forEach(System.out::println);
                }
                case 4 -> autorRepo.findAll().forEach(System.out::println);
                case 5 -> {
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    List autores = autorRepo.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(ano, ano);
                    autores.forEach(System.out::println);
                }
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
