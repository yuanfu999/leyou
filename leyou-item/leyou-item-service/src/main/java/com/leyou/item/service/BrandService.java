package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key 搜索关键词，String
     * @param page 当前页，int
     * @param rows 每页大小，int
     * @param sortBy 排序字段，String
     * @param desc  是否为降序，boolean
     * @return
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        // 初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        // 根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        // 添加分页条件
        PageHelper.startPage(page, rows);

        // 添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 新增品牌
     * @param brand 品牌对象
     * @param cids 商品分类的id数组cids
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        // 先新增brand
        this.brandMapper.insertSelective(brand);

        // 在新增中间表
        cids.forEach(cid -> {
            this.brandMapper.insertBrandAndCategory(cid, brand.getId());
        });
    }

    /**
     * 通过id删除品牌
     * @param bid
     * @return
     */
    @Transactional
    public void deleteBrand(Long bid) {
        //删除品牌
        this.brandMapper.deleteByPrimaryKey(bid);

        //删除中间表的分类信息
        this.brandMapper.deleteBrandAndCategory(bid);
    }


    /**
     * 更新品牌
     * @param brand 品牌对象
     * @param cids 商品分类的id数组cids
     */
    @Transactional
    public void updateBrand(Brand brand, List<Long> cids) {
        //更新品牌
        this.brandMapper.updateByPrimaryKey(brand);

        //删除所有已经存在的品牌分类
        this.brandMapper.deleteBrandAndCategory(brand.getId());

        // 在新增中间表
        cids.forEach(cid -> {
            this.brandMapper.insertBrandAndCategory(cid, brand.getId());
        });
    }

    /**
     * 通过分类id来查询品牌列表
     * @param cid
     * @return
     */
    public List<Brand> queryBrandsByCid(Long cid) {

        return this.brandMapper.selectBrandByCid(cid);
    }

    /**
     * 通过id获取品牌名称
     * @param id
     * @return
     */
    public Brand queryBrandNameById(Long id) {
        Brand brand = this.brandMapper.selectByPrimaryKey(id);
        return brand;
    }
}
