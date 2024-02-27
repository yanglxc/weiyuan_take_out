package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * Insert Dish Data
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * Dish_Paginated_Query
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    void deleteByIds(List<Long> ids);


    List<Dish> getByIds(List<Long> ids);
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    @Select("select * from dish where category_id = #{categoryId}")
    ArrayList<DishVO> getByCategoryId(Long categoryId);

    /**
     * 动态条件查询菜品
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);
}
