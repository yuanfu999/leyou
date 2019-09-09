package com.leyou.item.api;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("brand")
public interface BrandApi {

    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key 搜索关键词，String
     * @param page 当前页，int
     * @param rows 每页大小，int
     * @param sortBy 排序字段，String
     * @param desc  是否为降序，boolean
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    );

    /**
     * 通过分类id来查询品牌列表
     * @param cid
     * @return
     */
    @GetMapping("cid/{cid}")
    List<Brand> queryBrandsByCid(@PathVariable("cid")Long cid);

    /**
     * 通过id获取品牌名称
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Brand queryBrandById(@PathVariable("id") Long id);


}
