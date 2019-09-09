package com.leyou.cart.client;

import com.leyou.item.api.GoodApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-service")
public interface GoodsClient extends GoodApi {

}