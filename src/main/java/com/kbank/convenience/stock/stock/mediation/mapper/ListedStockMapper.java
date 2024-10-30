package com.kbank.convenience.stock.stock.mediation.mapper;

import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesResponse;
import com.kbank.convenience.stock.stock.mediation.config.MapStructCommonConfig;
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailResponse;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface ListedStockMapper {
    GetListedStockPriceDetailResponse.PriceElement toSubResponse(GetListedStockPricesResponse.GetListedStockPricesSubResponse getListedStockPricesSubResponse);
}
