package br.com.literalura.service;

import br.com.literalura.dto.*;
import br.com.literalura.model.*;
import br.com.literalura.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepo;
    private final AutorRepository autorRepo;
    private final ObjectMapper mapper = new ObjectMapper();
    private final ConsumoApi api = new ConsumoApi();

    public LivroService(LivroRepository livroRepo, AutorRepository autorRepo) {
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
    }

    public void buscarESalvarLivro(String titulo) {
        try {
            String json = api.buscarLivro(titulo);
            RespostaDTO resposta = mapper.readValue(json, RespostaDTO.class);

            LivroDTO livroDTO = resposta.results().get(0);
            AutorDTO autorDTO = livroDTO.autores().get(0);

            Autor autor = autorRepo.save(
                    new Autor(autorDTO.nome(), autorDTO.nascimento(), autorDTO.falecimento())
            );

            Livro livro = new Livro(
                    livroDTO.titulo(),
                    livroDTO.idiomas().get(0),
                    livroDTO.downloads(),
                    autor
            );

            livroRepo.save(livro);

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro.");
        }
    }
}
