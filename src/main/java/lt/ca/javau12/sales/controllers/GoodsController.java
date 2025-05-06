package lt.ca.javau12.sales.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau12.sales.dto.GoodsDTO;
import lt.ca.javau12.sales.services.GoodsService;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    // GET /goods
    @GetMapping
    public List<GoodsDTO> getAll() {
        return goodsService.getAll();
    }

    // GET /goods/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GoodsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(goodsService.getById(id));
    }

    // POST /goods
    @PostMapping
    public ResponseEntity<GoodsDTO> create(@RequestBody GoodsDTO dto) {
        GoodsDTO created = goodsService.create(dto);
        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(created);
    }

    // PUT /goods/{id}
    @PutMapping("/{id}")
    public ResponseEntity<GoodsDTO> update(@PathVariable Long id, @RequestBody GoodsDTO dto) {
        GoodsDTO updated = goodsService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE /goods/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        goodsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
