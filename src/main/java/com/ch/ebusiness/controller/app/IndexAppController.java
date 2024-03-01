package com.ch.ebusiness.controller.app;

import com.ch.ebusiness.repository.before.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class IndexAppController {

    @Autowired
    private IndexRepository indexRepository;

    @RequestMapping("/index")
    public String index(){

        return "app/index";
    }


    @RequestMapping("/my-sign")
    public String sign(){

        return "app/my-sign";
    }

    @RequestMapping("/my-time")
    public String time(){

        return "app/my-time";
    }

    @RequestMapping("/my-membership")
    public String membership(){

        return "app/my-membership";
    }

    @RequestMapping("/my-purchase")
    public String purchase(){

        return "app/my-purchase";
    }

    @RequestMapping("/my-invitation")
    public String invitation(){

        return "app/my-invitation";
    }



    @RequestMapping("/my-products-class")
    public String products(){

        return "app/my-products-class";
    }

    @RequestMapping("/find")
    public String find(){

        return "app/find";
    }

    @RequestMapping("/car")
    public String car(){

        return "app/car";
    }

    @RequestMapping("/order")
    public String order(){

        return "app/order";
    }

    @RequestMapping("/my-addres")
    public String myaddres(){

        return "app/my-addres";
    }

    @RequestMapping("/my-address")
    public String myaddress(){
        return "app/my-address";
    }

    @RequestMapping("/my-edit-address")
    public String myeditaddress(){
        return "app/my-edit-address";
    }

    @RequestMapping("/Settlement")
    public String Settlement(){
        return "app/Settlement";
    }

    @RequestMapping("/ui-me")
    public String uime(){
        return "app/ui-me";
    }

    @RequestMapping("/ui-product")
    public String uiproduct(){
        return "app/ui-product";
    }

    @RequestMapping("/my-order")
    public String myorder(){
        return "app/my-order";
    }

    @RequestMapping("/my-orders")
    public String myorders(){
        return "app/my-orders";
    }

    @RequestMapping("/appclass")
    public String appclass(Model model, Integer tid){
        if(tid == null){
            tid = 0;
        }
        //推荐商品
        model.addAttribute("recommendGoods", indexRepository.selectRecommendGoods(tid));
        //最新商品
        model.addAttribute("lastedGoods", indexRepository.selectLastedGoods(tid));
        //导航栏商品类型
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        return "app/class";
    }
}
