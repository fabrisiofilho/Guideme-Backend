package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.roadmap.ContentDTO;
import br.com.fabrisio.guideme.entity.roadmap.ContentEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.repository.ContentRepository;
import br.com.fabrisio.guideme.service.ContentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContentEntity create(ContentDTO dto) {
        return contentRepository.save(modelMapper.map(dto, ContentEntity.class));
    }

    @Override
    public ContentEntity read(Long id) {
        return contentRepository.findById(id).orElseThrow(()->{throw new NotFoundException("Não foi encontrado o conteudo");});
    }

    @Override
    public ContentEntity update(ContentDTO dto) {
        return contentRepository.save(modelMapper.map(dto, ContentEntity.class));
    }

    @Override
    public ContentEntity delete(Long id) {
        ContentEntity content= read(id);
        contentRepository.deleteById(id);
        return content;
    }
}
