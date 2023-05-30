package bordoesapi.bordoesapi.dominio.interfaces;

import bordoesapi.bordoesapi.api.models.ArtistaDto;
import bordoesapi.bordoesapi.dominio.entidade.Artista;

import java.util.List;

public interface InterfaceArtistaService {
    public List<ArtistaDto> getAll();
    public ArtistaDto getById(int id);
    public ArtistaDto save(ArtistaDto artistaDto);
    public ArtistaDto update(ArtistaDto artistaDto);
    public void delete(int id);
}
