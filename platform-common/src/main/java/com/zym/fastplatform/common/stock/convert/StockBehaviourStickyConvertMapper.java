package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockBehaviourSticky;
import com.zym.fastplatform.common.stock.entity.dto.StockBehaviourStickyDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBehaviourStickyVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface StockBehaviourStickyConvertMapper extends BaseConvertMapper<StockBehaviourSticky, StockBehaviourStickyVO, StockBehaviourStickyDTO> {

    @Mapping(target = "imageUrl", source = "fileList", qualifiedByName = "fileListToImageUrl")
    StockBehaviourSticky toEntity(StockBehaviourStickyDTO dto);

    @Mapping(target = "fileList", source = "imageUrl", qualifiedByName = "imageUrlToFileList")
    StockBehaviourStickyVO toVO(StockBehaviourSticky entity);

    @Named("fileListToImageUrl")
    default String fileListToImageUrl(List<String> fileList) {
        return Optional.ofNullable(fileList)
                .map(list -> String.join(",", list))
                .orElse("");
    }

    @Named("imageUrlToFileList")
    default List<String> imageUrlToFileList(String imageUrl) {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(imageUrl.split(","));
    }

}