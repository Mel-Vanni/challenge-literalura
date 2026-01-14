package br.com.literalura;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import br.com.literalura.repository.AutorRepository;
import br.com.literalura.repository.LivroRepository;
import br.com.literalura.service.LivroService;

@Component
public class Menu {

    private final LivroService service;
    private final LivroRepository livroRepo;
    private final AutorRepository autorRepo;

    public Menu(LivroService service, LivroRepository livroRepo, AutorRepository autorRepo) {
        this.service = service;
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
    }

    public void exibir() {
        try (Scanner sc = new Scanner(System.in)) {
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("""
                        1 - Buscar livro por título
                        2 - Listar livros
                        3 - Listar autores
                        4 - Autores vivos em determinado ano
                        5 - Quantidade de livros por idioma
                        0 - Sair
                        """);

                if (sc.hasNextInt()) {
                    opcao = sc.nextInt();
                    sc.nextLine();
                } else {
                    System.out.println("Opção inválida!");
                    sc.nextLine();
                    continue;
                }

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        service.buscarESalvarLivro(titulo);
                    }
                    case 2 -> livroRepo.findAll().forEach(System.out::println);
                    case 3 -> autorRepo.findAll().forEach(System.out::println);
                    case 4 -> {
                        System.out.print("Ano: ");
                        if (sc.hasNextInt()) {
                            int ano = sc.nextInt();
                            sc.nextLine();
                            autorRepo.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano)
                                    .forEach(System.out::println);
                        } else {
                            System.out.println("Ano inválido!");
                            sc.nextLine();
                        }
                    }
                    case 5 -> {
                        System.out.print("Idioma (ex: en, pt): ");
                        String idioma = sc.nextLine();
                        System.out.println("Quantidade: " + livroRepo.findByIdioma(idioma).size());
                    }
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }
}