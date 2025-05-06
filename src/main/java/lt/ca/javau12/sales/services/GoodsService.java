package lt.ca.javau12.sales.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lt.ca.javau12.sales.dto.GoodsDTO;
import lt.ca.javau12.sales.entities.Goods;
import lt.ca.javau12.sales.mappers.GoodsMapper;
import lt.ca.javau12.sales.repositories.GoodsRepository;

@Service
public class GoodsService {
	private final GoodsRepository goodsRepository;
    private final GoodsMapper goodsMapper;

    public GoodsService(GoodsRepository goodsRepository, GoodsMapper goodsMapper) {
        this.goodsRepository = goodsRepository;
        this.goodsMapper = goodsMapper;
    }

    public List<GoodsDTO> getAll() {
        return goodsRepository.findAll().stream()
                .map(goodsMapper::toDto)
                .toList();
    }

    public GoodsDTO getById(Long id) {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goods not found with id: " + id));
        return goodsMapper.toDto(goods);
    }

    public GoodsDTO create(GoodsDTO dto) {
        Goods goods = goodsMapper.toEntity(dto);
        return goodsMapper.toDto(goodsRepository.save(goods));
    }

    public GoodsDTO update(Long id, GoodsDTO dto) {
        Goods existing = goodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goods not found with id: " + id));
        
        existing.setName(dto.name());
        existing.setPrice(dto.price());
        existing.setDescription(dto.description());
        return goodsMapper.toDto(goodsRepository.save(existing));
    }

    public boolean delete(Long id) {
        if (!goodsRepository.existsById(id)) {
            return false;
        }
        goodsRepository.deleteById(id);
        return true;
    }
}