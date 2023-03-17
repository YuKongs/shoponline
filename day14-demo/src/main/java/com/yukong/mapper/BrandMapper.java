package com.yukong.mapper;

import com.yukong.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
   /*
   使用注解
   @Select("select * from tb_brand")
    @ResultMap("brandResultMap")*/

    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    void add(Brand brand);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    int selectTotalCount();

    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Brand brand);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(int id);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    Brand selectById(int id);

    /**
     * 更新操作
     * @param brand
     */
    void update(Brand brand);
}
