package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.roadmap.ContentDTO;
import br.com.fabrisio.guideme.entity.roadmap.ContentEntity;

public interface ContentService {

    ContentEntity create(ContentDTO dto);
    ContentEntity read(Long id);
    ContentEntity update(ContentDTO dto);
    ContentEntity delete(Long id);

}
