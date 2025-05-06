package lt.ca.javau12.sales.mappers;

import org.springframework.stereotype.Component;

import lt.ca.javau12.sales.dto.GoodsDTO;
import lt.ca.javau12.sales.entities.Goods;

@Component
public class GoodsMapper {
    public GoodsDTO toDto(Goods goods) {
        if (goods == null) return null;
        return new GoodsDTO(
        		goods.getId(), 
        		goods.getName(), 
        		goods.getDescription(), 
        		goods.getPrice()
        	);
    }

    public Goods toEntity(GoodsDTO dto) {
        if (dto == null) return null;
        Goods goods = new Goods();
        goods.setName(dto.name());
        goods.setDescription(dto.description());
        goods.setPrice(dto.price());
        return goods;
    }
}