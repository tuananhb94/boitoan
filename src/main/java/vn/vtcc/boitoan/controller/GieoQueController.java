package vn.vtcc.boitoan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.vtcc.boitoan.model.Bai;
import vn.vtcc.boitoan.model.CungHoangDao;
import vn.vtcc.boitoan.model.Que;
import vn.vtcc.boitoan.model.api.Data;
import vn.vtcc.boitoan.model.api.Info;
import vn.vtcc.boitoan.model.api.Param;
import vn.vtcc.boitoan.model.api.Path;
import vn.vtcc.boitoan.service.BocBaiService;
import vn.vtcc.boitoan.service.CungHoangDaoService;
import vn.vtcc.boitoan.service.GieoQueService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanAnh on 22/02/2018.
 */

@RestController
@RequestMapping(value = "/knowledge")
public class GieoQueController {

    @Autowired
    private GieoQueService gieoQueService;
    @Autowired
    private BocBaiService bocBaiService;
    @Autowired
    private CungHoangDaoService cungHoangDaoService;
    @RequestMapping(path = "/boique", produces = MediaType.APPLICATION_JSON_VALUE)
    public Que getQue() {
        return gieoQueService.getQue();
    }

    @GetMapping("/bocbai")
    public Bai getBai() {
        return bocBaiService.getBai();
    }

    @GetMapping("/boicunghoangdao")
    public CungHoangDao getCungHoangDao(@RequestParam("ngay") String ngaySinh, @RequestParam("gioitinh") String gioiTinh) {
        return cungHoangDaoService.boiCungHoangDao(ngaySinh, gioiTinh);
    }

    @GetMapping("")
    public Info summary() {
        List<Path> paths = new ArrayList<>();
        //khai báo /boique
        paths.add(new Path("/boique", "GET", "bói theo quẻ", new ArrayList<>()));
        //khai báo /bocbai
        paths.add(new Path("/bocbai", "GET", "bói bốc bài 52 lá", new ArrayList<>()));
        //khai báo /boicunghoangdao
        List<Param> params = new ArrayList<>();
        params.add(new Param("ngay","string"));
        params.add(new Param("gioitinh","string"));
        paths.add(new Path("/boicunghoangdao", "GET", "bói cung hoàng đạo", params));
        /*------------------------------------------------------------*/
        Data data = new Data("", "/knowledge", paths);
        Info info = new Info(200, data, "Success");
        return info;
    }
}
