package com.bigdata.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.gulimall.product.entity.BrandEntity;
import com.bigdata.gulimall.product.service.BrandService;
import com.bigdata.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@SpringBootTest
@Slf4j
class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;


    @Autowired
    StringRedisTemplate redisTemplate;


    @Test
    public void redistTest(){
        ValueOperations<String, String>  valueOperations= redisTemplate.opsForValue();
        valueOperations.set("key1","helllo world"+ UUID.randomUUID().toString());

        System.out.println("保存的数据："+valueOperations.get("key1"));
    }

    @Test
    public void getCatelogPath(){
        Long[] catelogPath = categoryService.findCatelogPath(227L);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("huawei");
        brandService.save(brandEntity);
        System.out.println("success.");
    }

    @Test
    void updateValue() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("huawei honor8");
        brandService.updateById(brandEntity);
        System.out.println("success.");
    }

    @Test
    void listBrand() {
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>(new BrandEntity()));
        list.forEach(t -> System.out.println(t));
    }
}
