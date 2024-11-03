package cn.edu.xmu.javaee.productdemoaop.mapper.manual;

import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;
import cn.edu.xmu.javaee.productdemoaop.mapper.manual.po.ProductAllPo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ProductAllMapperByJoin {
    @Select({
            "select",
            "p.id, p.sku_sn, p.name, p.original_price, p.weight, p.barcode, p.unit, ",
            "p.origin_place, p.commission_ratio, p.free_threshold, p.status, p.creator_id, p.creator_name, ",
            "p.modifier_id, p.modifier_name, p.gmt_create, p.gmt_modified, ",
            "o.id as o_id, o.product_id as o_product_id,o.price as o_price, o.begin_time as o_begin_time, o.end_time as o_end_time, o.quantity as o_quantity, o.max_quantity as o_max_quantity, ",
            "o.creator_id as o_creator_id, o.creator_name as o_creator_name, ",
            "o.modifier_id as o_modifier_id, o.modifier_name as o_modifier_name, ",
            "o.gmt_create as o_gmt_create, o.gmt_modified as o_gmt_modified, ",
            "op.id as op_id, op.sku_sn as op_sku_sn, op.name as op_name, op.original_price as op_original_price, op.weight as op_weight, op.barcode as op_barcode, ",
            "op.unit as op_unit, op.origin_place as op_origin_place, op.creator_id as op_creator_id, ",
            "op.creator_name as op_creator_name, op.modifier_id as op_modifier_id, ",
            "op.modifier_name as op_modifier_name, op.gmt_create as op_gmt_create, ",
            "op.gmt_modified as op_gmt_modified ",
            "from goods_product p ",
            "left join goods_onsale o on p.id = o.product_id ",
            "left join goods_product op on p.goods_id = op.goods_id ",
            "where o.begin_time <= NOW() and o.end_time > NOW() and p.name = #{name}"

    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="sku_sn", property="skuSn", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.BIGINT),
            @Result(column="weight", property="weight", jdbcType=JdbcType.BIGINT),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
            @Result(column="origin_place", property="originPlace", jdbcType=JdbcType.VARCHAR),
            @Result(column="commission_ratio", property="commissionRatio", jdbcType=JdbcType.INTEGER),
            @Result(column="free_threshold", property="freeThreshold", jdbcType=JdbcType.BIGINT),
            @Result(column="status", property="status", jdbcType=JdbcType.SMALLINT),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
            @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
            @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
            @Result(property = "onSaleList", many = @Many(resultMap = "OnSaleMap", columnPrefix = "o_")),
            @Result(property = "otherProduct", many = @Many(resultMap = "OtherProductMap", columnPrefix = "op_"))
    })
    List<ProductAllPo> getProductWithAllByJoin(String name);

    @Select("SELECT id FROM goods_onsale WHERE id = #{id}")
    @Results(id = "OnSaleMap", value = {
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="price", property="price", jdbcType=JdbcType.BIGINT),
            @Result(column="begin_time", property="beginTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
            @Result(column="max_quantity", property="maxQuantity", jdbcType=JdbcType.INTEGER),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
            @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
            @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OnSalePo> selectLastOnSaleByProductId(Long productId);

    @Select("SELECT id FROM goods_product WHERE id = #{id}")
    @Results(id = "OtherProductMap", value = {
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="sku_sn", property="skuSn", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.BIGINT),
            @Result(column="weight", property="weight", jdbcType=JdbcType.BIGINT),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
            @Result(column="origin_place", property="originPlace", jdbcType=JdbcType.VARCHAR),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
            @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
            @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    ProductPo selectOtherProduct(Long goodsId);
}
