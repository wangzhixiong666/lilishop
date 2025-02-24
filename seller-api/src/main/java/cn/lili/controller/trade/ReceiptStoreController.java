package cn.lili.controller.trade;

import cn.lili.common.security.context.UserContext;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.order.order.entity.dos.Receipt;
import cn.lili.modules.order.order.entity.dto.OrderReceiptDTO;
import cn.lili.modules.order.order.entity.dto.ReceiptSearchParams;
import cn.lili.modules.order.order.service.ReceiptService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 店铺端,发票接口
 *
 * @author Bulbasaur
 * @since 2020/11/28 14:09
 **/
@RestController
@Api(tags = "店铺端,发票接口")
@RequestMapping("/store/receipt")
public class ReceiptStoreController {

    @Autowired
    private ReceiptService receiptService;

    @ApiOperation(value = "分页获取")
    @GetMapping
    public ResultMessage<IPage<OrderReceiptDTO>> getByPage(PageVO page, ReceiptSearchParams receiptSearchParams) {
        receiptSearchParams.setStoreId(UserContext.getCurrentUser().getStoreId());
        return ResultUtil.data(receiptService.getReceiptData(receiptSearchParams, page));
    }

    @ApiOperation(value = "通过id获取")
    @ApiImplicitParam(name = "id", value = "发票ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/get/{id}")
    public ResultMessage<Receipt> get(@PathVariable String id) {
        return ResultUtil.data(receiptService.getById(id));
    }

    @ApiOperation(value = "开发票")
    @ApiImplicitParam(name = "id", value = "发票ID", required = true, dataType = "String", paramType = "path")
    @PostMapping(value = "/{id}/invoicing")
    public ResultMessage<Receipt> invoicing(@PathVariable String id) {
        return ResultUtil.data(receiptService.invoicing(id));
    }

    @ApiOperation(value = "通过订单编号获取")
    @ApiImplicitParam(name = "orderSn", value = "订单编号", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/get/orderSn/{orderSn}")
    public ResultMessage<Receipt> getByOrderSn(@PathVariable String orderSn) {
        return ResultUtil.data(receiptService.getByOrderSn(orderSn));
    }

}
