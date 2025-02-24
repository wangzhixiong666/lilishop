package cn.lili.modules.order.order.entity.dos;

import cn.lili.mybatis.BaseEntity;
import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.order.cart.entity.enums.DeliveryMethodEnum;
import cn.lili.modules.order.cart.entity.dto.TradeDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 交易
 *
 * @author Chopper
 * @since 2020/11/17 7:34 下午
 */
@Data
@Entity
@Table(name = "li_trade")
@TableName("li_trade")
@ApiModel(value = "交易")
@NoArgsConstructor
public class Trade extends BaseEntity {

    private static final long serialVersionUID = 5177608752643561827L;

    @ApiModelProperty(value = "交易编号")
    private String sn;

    @ApiModelProperty(value = "买家id")
    private String memberId;

    @ApiModelProperty(value = "买家用户名")
    private String memberName;

    @ApiModelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * @see cn.lili.modules.order.order.entity.enums.PayStatusEnum
     */
    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "总价格")
    private Double flowPrice;

    @ApiModelProperty(value = "原价")
    private Double goodsPrice;

    @ApiModelProperty(value = "运费")
    private Double freightPrice;

    @ApiModelProperty(value = "优惠的金额")
    private Double discountPrice;

    /**
     * @see DeliveryMethodEnum
     */
    @ApiModelProperty(value = "配送方式")
    private String deliveryMethod;

    @ApiModelProperty(value = "收货人姓名")
    private String consigneeName;

    @ApiModelProperty(value = "收件人手机")
    private String consigneeMobile;

    @ApiModelProperty(value = "地址名称， '，'分割")
    private String consigneeAddressPath;

    @ApiModelProperty(value = "地址id，'，'分割 ")
    private String consigneeAddressIdPath;

    public Trade(TradeDTO tradeDTO) {
        String originId = this.getId();
        if (tradeDTO.getMemberAddress() != null) {
            BeanUtil.copyProperties(tradeDTO.getMemberAddress(), this);
            this.setConsigneeMobile(tradeDTO.getMemberAddress().getMobile());
            this.setConsigneeName(tradeDTO.getMemberAddress().getName());
        }
        BeanUtil.copyProperties(tradeDTO, this);
        BeanUtil.copyProperties(tradeDTO.getPriceDetailDTO(), this);
        this.setId(originId);
    }
}