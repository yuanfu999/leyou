package com.leyou.item.api;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("spec")
public interface SpecificationApi {


    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    List<SpecGroup> queryGroupsByCid(@PathVariable("cid")Long cid);

    /**
     * 根据条件查询规格参数
     * @param gid 分组id
     * @param cid 分类id
     * @param generic sku通用参数
     * @param searching  是否是搜索过滤字段
     * @return
     */
    @GetMapping("params")
    List<SpecParam> queryParams(
            @RequestParam(value = "gid", required = false)Long gid,
            @RequestParam(value = "cid", required = false)Long cid,
            @RequestParam(value = "generic", required = false)Boolean generic,
            @RequestParam(value = "searching", required = false)Boolean searching
    );

    @GetMapping("group/param/{cid}")
    List<SpecGroup> queryGroupsWithParam(@PathVariable("cid") Long cid);
}
