package com.iotlife.controller;

import com.iotlife.dto.CommonResponseDto;
import com.iotlife.dto.DevDto;
import com.iotlife.service.DevService;
import com.iotlife.util.myconst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 设备controller
 */
@RestController
public class DevController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DevService devService;

    /**
     * 添加设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/addDev")
    @ResponseBody
    public CommonResponseDto addDev(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        try {
            devService.addDev(devDto);
            ret.setCode(myconst.SUCCESS);
        } catch (Exception e) {
            logger.error("DevController addDev error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 删除设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/delDev")
    @ResponseBody
    public CommonResponseDto delDev(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        try {
            int delStatus = devService.delDev(devDto);
            /**
             * 0 删除成功
             * 1 有用户绑定关系
             */
            if (0 == delStatus) {
                ret.setCode(myconst.SUCCESS);
            } else if (1 == delStatus) {
                ret.setCode(myconst.DEV_HAVE_Bind_CODE);
            }
        } catch (Exception e) {
            logger.error("DevController delDev error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 修改设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/updateDev")
    @ResponseBody
    public CommonResponseDto updateDev(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        try {
            devService.updateDev(devDto);
            ret.setCode(myconst.SUCCESS);
        } catch (Exception e) {
            logger.error("DevController updateDev error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 通过设备id查询设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/selectDevById")
    @ResponseBody
    public CommonResponseDto selectDevById(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        try {
            ret.setData(devService.selectById(devDto));
            ret.setCode(myconst.SUCCESS);
        } catch (Exception e) {
            logger.error("DevController selectDevById error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 通过用户id查询该用户下面绑定的设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/selectDevByUserId")
    @ResponseBody
    public CommonResponseDto selectDevByUserId(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        List<DevDto> dList = null;
        try {
            dList = devService.selectDevByUserId(devDto);
            if (null != dList) {
                if (dList.size() > 0) {
                    ret.setData(dList);
                    ret.setCode(myconst.SUCCESS);
                } else {
                    ret.setCode(myconst.EMPTY_LIST);
                }
            } else {
                ret.setCode(myconst.FAIL);
            }
        } catch (Exception e) {
            logger.error("DevController selectDevByUserId error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 用户绑定设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/bindDevToUser")
    @ResponseBody
    public CommonResponseDto bindDevToUser(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        try {
            devService.bindDevToUser(devDto);
            ret.setCode(myconst.SUCCESS);
        } catch (Exception e) {
            logger.error("DevController bindDevToUser error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 用户解绑设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/unBindDevToUser")
    @ResponseBody
    public CommonResponseDto unBindDevToUser(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        try {
            devService.unBindDevToUser(devDto);
            ret.setCode(myconst.SUCCESS);
        } catch (Exception e) {
            logger.error("DevController unBindDevToUser error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 解绑用户所有设备
     *
     * @param devDto
     * @return
     */
    @RequestMapping("/unBindUserAllDev")
    @ResponseBody
    public CommonResponseDto unBindUserAllDev(@RequestBody @Validated DevDto devDto) {
        CommonResponseDto ret = new CommonResponseDto();
        try {
            devService.unBindUserAllDev(devDto);
            ret.setCode(myconst.SUCCESS);
        } catch (Exception e) {
            logger.error("DevController unBindUserAllDev error");
            ret.setCode(myconst.EXCEPTION);
            e.printStackTrace();
        }
        return ret;
    }

}
