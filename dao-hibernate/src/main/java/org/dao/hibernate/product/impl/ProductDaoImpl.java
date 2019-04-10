package org.dao.hibernate.product.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.product.ProductDao;
import org.domain.product.Product;
import org.springframework.stereotype.Repository;
import org.util.StringUtil;
import org.vo.product.ProductDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDaoImpl<Product> implements ProductDao {

    @Override
    public PageResponse<Product> querByPage(ProductDto productDto, PageRequest request, String desc) throws Exception{
        Map<String, Object> condition = new HashMap<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql1 = "";
        StringBuffer sql = new StringBuffer();
        sql.append("select p.* FROM product p LEFT JOIN member  m ON p.memberId = m.memberId "
                + "LEFT JOIN company c ON p.memberId = c.memberId LEFT JOIN safeguard s ON p.productId = s.productId where 1=1 ");
        // 1--项目申请列表 2--待审核列表
        if (StringUtil.isEmpty(desc) || desc.equals("1")) {
            // 判断待处理 还是已处理(2)列表
            if (StringUtil.isNotEmpty(productDto.getStatus()) && productDto.getStatus().equals("2")) {
                sql.append(" and p.status in (11,12,15,30,31,32) ");
                // 已处理 则查询上线日期
                if (StringUtil.isNotEmpty(productDto.getStartTime())) {
                    sql.append(" and p.groundTime >=:startTime ");
                    condition.put("startTime",
                            format1.parse(format.format(productDto.getStartTime()).trim() + " 00:00:00"));
                }
                if (StringUtil.isNotEmpty(productDto.getEndTime())) {
                    sql.append(" and p.groundTime <=:endTime ");
                    condition
                            .put("endTime", format1.parse(format.format(productDto.getEndTime()).trim() + " 23:59:59"));
                }
                sql1 = " order by p.groundTime desc ";
            } else {
                sql.append(" and p.status in(10,13,14) ");
                // 未处理 则查询创建日期
                if (StringUtil.isNotEmpty(productDto.getStartTime())) {
                    sql.append(" and p.createTime >=:startTime ");
                    condition.put("startTime",
                            format1.parse(format.format(productDto.getStartTime()).trim() + " 00:00:00"));
                }
                if (StringUtil.isNotEmpty(productDto.getEndTime())) {
                    sql.append(" and p.createTime <=:endTime ");
                    condition
                            .put("endTime", format1.parse(format.format(productDto.getEndTime()).trim() + " 23:59:59"));
                }
                sql1 = " order by p.createTime desc ";
            }
        } else if (desc.equals("2")) {
            sql.append(" and p.status = '11' ");
            // 项目审核查询 提交时间
            if (StringUtil.isNotEmpty(productDto.getStartTime())) {
                sql.append(" and p.createTime >=:startTime ");
                condition
                        .put("startTime", format1.parse(format.format(productDto.getStartTime()).trim() + " 00:00:00"));
            }
            if (StringUtil.isNotEmpty(productDto.getEndTime())) {
                sql.append(" and p.createTime <=:endTime ");
                condition.put("endTime", format1.parse(format.format(productDto.getEndTime()).trim() + " 23:59:59"));
            }
            sql1 = " order by p.createTime desc ";
        }
        if (StringUtil.isNotEmpty(productDto.getApplyKeywords())) {
            String applyKeywords = productDto.getApplyKeywords();
            // 项目编号
            sql.append(" and (p.productCd like :productCd ");
            // 标题
            sql.append(" or p.productNm like :productNm ");
            // 借款人
            sql.append(" or m.realNm like :realNm ");
            // 借款企业
            sql.append(" or c.companyNm like :companyNm ");
            // 担保机构
            sql.append(" or s.companyNm like :safeNm )");

            condition.put("productCd", "%" + applyKeywords + "%");
            condition.put("productNm", "%" + applyKeywords + "%");
            condition.put("realNm", "%" + applyKeywords + "%");
            condition.put("companyNm", "%" + applyKeywords + "%");
            condition.put("safeNm", "%" + applyKeywords + "%");
        }
        String fSql = "select s.* from (" + sql.toString() + sql1 + ")s";

        return queryPageBySql(fSql, request, condition);
    }
}
