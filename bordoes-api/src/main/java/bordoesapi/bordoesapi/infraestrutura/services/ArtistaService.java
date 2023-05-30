package bordoesapi.bordoesapi.infraestrutura.services;

import bordoesapi.bordoesapi.api.models.ArtistaDto;
import bordoesapi.bordoesapi.dominio.entidade.Artista;
import bordoesapi.bordoesapi.dominio.interfaces.InterfaceArtistaService;
import bordoesapi.bordoesapi.infraestrutura.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ArtistaService implements InterfaceArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;
    @Override
    public List<ArtistaDto> getAll() {

        List<ArtistaDto> artistas =  artistaRepository.findAll().stream()
                .map(ArtistaDto::new)
                .collect(Collectors.toList());
        return artistas;
    }

    @Override
    public ArtistaDto getById(int id) {
        Artista artista = artistaRepository.getReferenceById(id);
        if(artista == null){
            throw new RuntimeException("Nenhum artista encontrado para este ID!");
        }
        ArtistaDto artistaDto = new ArtistaDto(artista);
        return artistaDto;
    }

    @Override
    public ArtistaDto save(ArtistaDto dto) {
        if(dto == null){
            throw new RuntimeException("Não é permitido salvar um artista nulo!");
        }
        Artista artista = new Artista(dto);
        artistaRepository.save(artista);
        ArtistaDto artistaDto = new ArtistaDto(artista);
        return artistaDto;
    }

    @Override
    public ArtistaDto update(ArtistaDto dto) {
        if(dto == null){
            throw new RuntimeException("Não é permitido atualizar com um artista nulo!");
        }

        Artista artistaAtualizado = artistaRepository.getReferenceById(dto.id());

        if(artistaAtualizado == null){
            throw new RuntimeException("Nenhum artista encontrado para este ID!");
        }

        artistaAtualizado.setNome(dto.nome());
        artistaAtualizado.setDetalhe(dto.detalhe());
        artistaAtualizado.setInstagram(dto.instagram());
        artistaAtualizado.setHabilitado(dto.habilitao());
        artistaRepository.save(artistaAtualizado);

        ArtistaDto artistaDto = new ArtistaDto(artistaAtualizado);
        return artistaDto;
    }

    @Override
    public void delete(int id) {
       Artista artista = artistaRepository.getReferenceById(id);
       if(artista == null){
           throw new RuntimeException("Nenhum artista encontrado para este ID!");
       }
       artistaRepository.delete(artista);
    }
}
