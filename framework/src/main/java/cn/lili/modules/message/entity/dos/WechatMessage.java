package cn.lili.modules.message.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微信消息
 *
 * @author Chopper
 * @version v4.0
 * @since 2020/12/10 17:02
 */
@Data
@Entity
@Table(name = "li_wechat_message")
@TableName("li_wechat_message")
@ApiModel(value = "微信消息")
public class WechatMessage extends BaseEntity {


    private static final long serialVersionUID = -9157586585885836755L;

    @ApiModelProperty(value = "模版名称")
    private String name;

    @ApiModelProperty(value = "微信模版码")
    private String code;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "是否开启")
    private Boolean enable = true;

    @ApiModelProperty("订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "模版头部信息")
    private String first;

    @ApiModelProperty(value = "模版备注（位于最下方）")
    private String remark;


}