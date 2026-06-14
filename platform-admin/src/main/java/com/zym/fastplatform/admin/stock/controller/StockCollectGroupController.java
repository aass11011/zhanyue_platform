package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.common.stock.entity.StockCollectGroup;
import com.zym.fastplatform.common.stock.entity.dto.StockCollectGroupDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCollectGroupVO;
import com.zym.fastplatform.common.stock.service.StockCollectGroupSerivce;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock/collect/group")
public class StockCollectGroupController extends BaseController<StockCollectGroupSerivce, StockCollectGroup,StockCollectGroupDTO,StockCollectGroupVO> {

}
