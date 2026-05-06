package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockCollectGroup;
import com.zym.fastplatform.stock.entity.dto.StockCollectGroupDTO;
import com.zym.fastplatform.stock.entity.vo.StockCollectGroupVO;
import com.zym.fastplatform.stock.service.StockCollectGroupSerivce;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock/collect/group")
public class StockCollectGroupController extends BaseController<StockCollectGroupSerivce, StockCollectGroup,StockCollectGroupDTO,StockCollectGroupVO> {

}
