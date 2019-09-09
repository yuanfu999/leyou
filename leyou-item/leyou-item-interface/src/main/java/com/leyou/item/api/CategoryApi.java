package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {


    /**
     * 根据父id查询子节点
     * @param pid
     * @return
     */
    @GetMapping("list")
    List<Category> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid);

    /**
     * 通过品牌id查询商品分类
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    List<Category> queryByBrandId(@PathVariable("bid") Long bid);

    /**
     * 根据分类id查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("names")
    List<String> queryNamesByIds(@RequestParam("ids")List<Long> ids);

    /**
     * 根据3级分类id，查询1~3级的分类
     * @param id
     * @return
     */
    @GetMapping("all/level")
    List<Category> queryAllByCid3(@RequestParam("id") Long id);
}
