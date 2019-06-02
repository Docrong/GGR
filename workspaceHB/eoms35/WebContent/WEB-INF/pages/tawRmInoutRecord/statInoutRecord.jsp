<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<script language="javascript">
var Provinces=new Array(
new Array("110000","${eoms:a2u("北京市")}"),
new Array("120000","${eoms:a2u("天津市")}"),
new Array("130000","${eoms:a2u("河北省")}"),
new Array("140000","${eoms:a2u("山西省")}"),
new Array("150000","${eoms:a2u("内蒙古自治区")}"),
new Array("210000","${eoms:a2u("辽宁省")}"),
new Array("220000","${eoms:a2u("吉林省")}"),
new Array("230000","${eoms:a2u("黑龙江省")}"),
new Array("310000","${eoms:a2u("上海市")}"),
new Array("320000","${eoms:a2u("江苏省")}"),
new Array("330000","${eoms:a2u("浙江省")}"),
new Array("340000","${eoms:a2u("安徽省")}"),
new Array("350000","${eoms:a2u("福建省")}"),
new Array("360000","${eoms:a2u("江西省")}"),
new Array("370000","${eoms:a2u("山东省")}"),
new Array("410000","${eoms:a2u("河南省")}"),
new Array("420000","${eoms:a2u("湖北省")}"),
new Array("430000","${eoms:a2u("湖南省")}"),
new Array("440000","${eoms:a2u("广东省")}"),
new Array("450000","${eoms:a2u("广西壮族自治区")}"),
new Array("460000","${eoms:a2u("海南省")}"),
new Array("500000","${eoms:a2u("重庆市")}"),
new Array("510000","${eoms:a2u("四川省")}"),
new Array("520000","${eoms:a2u("贵州省")}"),
new Array("530000","${eoms:a2u("云南省")}"),
new Array("540000","${eoms:a2u("西藏自治区")}"),
new Array("610000","${eoms:a2u("陕西省")}"),
new Array("620000","${eoms:a2u("甘肃省")}"),
new Array("630000","${eoms:a2u("青海省")}"),
new Array("640000","${eoms:a2u("宁夏回族自治区")}"),
new Array("650000","${eoms:a2u("新疆维吾尔自治区")}"),
new Array("710000","${eoms:a2u("台湾省")}"),
new Array("810000","${eoms:a2u("香港特别行政区")}"),
new Array("820000","${eoms:a2u("澳门特别行政区")}")
);

var Citys=new Array(
new Array("110100","${eoms:a2u("北京")}"),
new Array("120100","${eoms:a2u("天津")}"),
new Array("130101","${eoms:a2u("石家庄")}"),
new Array("130201","${eoms:a2u("唐山")}"),
new Array("130301","${eoms:a2u("秦皇岛")}"),
new Array("130701","${eoms:a2u("张家口")}"),
new Array("130801","${eoms:a2u("承德")}"),
new Array("131001","${eoms:a2u("廊坊")}"),
new Array("130401","${eoms:a2u("邯郸")}"),
new Array("130501","${eoms:a2u("邢台")}"),
new Array("130601","${eoms:a2u("保定")}"),
new Array("130901","${eoms:a2u("沧州")}"),
new Array("133001","${eoms:a2u("衡水")}"),
new Array("140101","${eoms:a2u("太原")}"),
new Array("140201","${eoms:a2u("大同")}"),
new Array("140301","${eoms:a2u("阳泉")}"),
new Array("140501","${eoms:a2u("晋城")}"),
new Array("140601","${eoms:a2u("朔州")}"),
new Array("142201","${eoms:a2u("忻州")}"),
new Array("142331","${eoms:a2u("离石")}"),
new Array("142401","${eoms:a2u("榆次")}"),
new Array("142601","${eoms:a2u("临汾")}"),
new Array("142701","${eoms:a2u("运城")}"),
new Array("140401","${eoms:a2u("长治")}"),
new Array("150101","${eoms:a2u("呼和浩特")}"),
new Array("150201","${eoms:a2u("包头")}"),
new Array("150301","${eoms:a2u("乌海")}"),
new Array("152601","${eoms:a2u("集宁")}"),
new Array("152701","${eoms:a2u("东胜")}"),
new Array("152801","${eoms:a2u("临河")}"),
new Array("152921","${eoms:a2u("阿拉善左旗")}"),
new Array("150401","${eoms:a2u("赤峰")}"),
new Array("152301","${eoms:a2u("通辽")}"),
new Array("152502","${eoms:a2u("锡林浩特")}"),
new Array("152101","${eoms:a2u("海拉尔")}"),
new Array("152201","${eoms:a2u("乌兰浩特")}"),
new Array("210101","${eoms:a2u("沈阳")}"),
new Array("210201","${eoms:a2u("大连")}"),
new Array("210301","${eoms:a2u("鞍山")}"),
new Array("210401","${eoms:a2u("抚顺")}"),
new Array("210501","${eoms:a2u("本溪")}"),
new Array("210701","${eoms:a2u("锦州")}"),
new Array("210801","${eoms:a2u("营口")}"),
new Array("210901","${eoms:a2u("阜新")}"),
new Array("211101","${eoms:a2u("盘锦")}"),
new Array("211201","${eoms:a2u("铁岭")}"),
new Array("211301","${eoms:a2u("朝阳")}"),
new Array("211401","${eoms:a2u("锦西")}"),
new Array("210601","${eoms:a2u("丹东")}"),
new Array("220101","${eoms:a2u("长春")}"),
new Array("220201","${eoms:a2u("吉林")}"),
new Array("220301","${eoms:a2u("四平")}"),
new Array("220401","${eoms:a2u("辽源")}"),
new Array("220601","${eoms:a2u("浑江")}"),
new Array("222301","${eoms:a2u("白城")}"),
new Array("222401","${eoms:a2u("延吉")}"),
new Array("220501","${eoms:a2u("通化")}"),
new Array("230101","${eoms:a2u("哈尔滨")}"),
new Array("230301","${eoms:a2u("鸡西")}"),
new Array("230401","${eoms:a2u("鹤岗")}"),
new Array("230501","${eoms:a2u("双鸭山")}"),
new Array("230701","${eoms:a2u("伊春")}"),
new Array("230801","${eoms:a2u("佳木斯")}"),
new Array("230901","${eoms:a2u("七台河")}"),
new Array("231001","${eoms:a2u("牡丹江")}"),
new Array("232301","${eoms:a2u("绥化")}"),
new Array("230201","${eoms:a2u("齐齐哈尔")}"),
new Array("230601","${eoms:a2u("大庆")}"),
new Array("232601","${eoms:a2u("黑河")}"),
new Array("232700","${eoms:a2u("加格达奇")}"),
new Array("310100","${eoms:a2u("上海")}"),
new Array("320101","${eoms:a2u("南京")}"),
new Array("320201","${eoms:a2u("无锡")}"),
new Array("320301","${eoms:a2u("徐州")}"),
new Array("320401","${eoms:a2u("常州")}"),
new Array("320501","${eoms:a2u("苏州")}"),
new Array("320600","${eoms:a2u("南通")}"),
new Array("320701","${eoms:a2u("连云港")}"),
new Array("320801","${eoms:a2u("淮阴")}"),
new Array("320901","${eoms:a2u("盐城")}"),
new Array("321001","${eoms:a2u("扬州")}"),
new Array("321101","${eoms:a2u("镇江")}"),
new Array("330101","${eoms:a2u("杭州")}"),
new Array("330201","${eoms:a2u("宁波")}"),
new Array("330301","${eoms:a2u("温州")}"),
new Array("330401","${eoms:a2u("嘉兴")}"),
new Array("330501","${eoms:a2u("湖州")}"),
new Array("330601","${eoms:a2u("绍兴")}"),
new Array("330701","${eoms:a2u("金华")}"),
new Array("330801","${eoms:a2u("衢州")}"),
new Array("330901","${eoms:a2u("舟山")}"),
new Array("332501","${eoms:a2u("丽水")}"),
new Array("332602","${eoms:a2u("临海")}"),
new Array("340101","${eoms:a2u("合肥")}"),
new Array("340201","${eoms:a2u("芜湖")}"),
new Array("340301","${eoms:a2u("蚌埠")}"),
new Array("340401","${eoms:a2u("淮南")}"),
new Array("340501","${eoms:a2u("马鞍山")}"),
new Array("340601","${eoms:a2u("淮北")}"),
new Array("340701","${eoms:a2u("铜陵")}"),
new Array("340801","${eoms:a2u("安庆")}"),
new Array("341001","${eoms:a2u("黄山")}"),
new Array("342101","${eoms:a2u("阜阳")}"),
new Array("342201","${eoms:a2u("宿州")}"),
new Array("342301","${eoms:a2u("滁州")}"),
new Array("342401","${eoms:a2u("六安")}"),
new Array("342501","${eoms:a2u("宣州")}"),
new Array("342601","${eoms:a2u("巢湖")}"),
new Array("342901","${eoms:a2u("贵池")}"),
new Array("350101","${eoms:a2u("福州")}"),
new Array("350201","${eoms:a2u("厦门")}"),
new Array("350301","${eoms:a2u("莆田")}"),
new Array("350401","${eoms:a2u("三明")}"),
new Array("350501","${eoms:a2u("泉州")}"),
new Array("350601","${eoms:a2u("漳州")}"),
new Array("352101","${eoms:a2u("南平")}"),
new Array("352201","${eoms:a2u("宁德")}"),
new Array("352601","${eoms:a2u("龙岩")}"),
new Array("360101","${eoms:a2u("南昌")}"),
new Array("360201","${eoms:a2u("景德镇")}"),
new Array("362101","${eoms:a2u("赣州")}"),
new Array("360301","${eoms:a2u("萍乡")}"),
new Array("360401","${eoms:a2u("九江")}"),
new Array("360501","${eoms:a2u("新余")}"),
new Array("360601","${eoms:a2u("鹰潭")}"),
new Array("362201","${eoms:a2u("宜春")}"),
new Array("362301","${eoms:a2u("上饶")}"),
new Array("362401","${eoms:a2u("吉安")}"),
new Array("362502","${eoms:a2u("临川")}"),
new Array("370101","${eoms:a2u("济南")}"),
new Array("370201","${eoms:a2u("青岛")}"),
new Array("370301","${eoms:a2u("淄博")}"),
new Array("370401","${eoms:a2u("枣庄")}"),
new Array("370501","${eoms:a2u("东营")}"),
new Array("370601","${eoms:a2u("烟台")}"),
new Array("370701","${eoms:a2u("潍坊")}"),
new Array("370801","${eoms:a2u("济宁")}"),
new Array("370901","${eoms:a2u("泰安")}"),
new Array("371001","${eoms:a2u("威海")}"),
new Array("371100","${eoms:a2u("日照")}"),
new Array("372301","${eoms:a2u("滨州")}"),
new Array("372401","${eoms:a2u("德州")}"),
new Array("372501","${eoms:a2u("聊城")}"),
new Array("372801","${eoms:a2u("临沂")}"),
new Array("372901","${eoms:a2u("菏泽")}"),
new Array("410101","${eoms:a2u("郑州")}"),
new Array("410201","${eoms:a2u("开封")}"),
new Array("410301","${eoms:a2u("洛阳")}"),
new Array("410401","${eoms:a2u("平顶山")}"),
new Array("410501","${eoms:a2u("安阳")}"),
new Array("410601","${eoms:a2u("鹤壁")}"),
new Array("410701","${eoms:a2u("新乡")}"),
new Array("410801","${eoms:a2u("焦作")}"),
new Array("410901","${eoms:a2u("濮阳")}"),
new Array("411001","${eoms:a2u("许昌")}"),
new Array("411101","${eoms:a2u("漯河")}"),
new Array("411201","${eoms:a2u("三门峡")}"),
new Array("412301","${eoms:a2u("商丘")}"),
new Array("412701","${eoms:a2u("周口")}"),
new Array("412801","${eoms:a2u("驻马店")}"),
new Array("412901","${eoms:a2u("南阳")}"),
new Array("413001","${eoms:a2u("信阳")}"),
new Array("420101","${eoms:a2u("武汉")}"),
new Array("420201","${eoms:a2u("黄石")}"),
new Array("420301","${eoms:a2u("十堰")}"),
new Array("420400","${eoms:a2u("沙市")}"),
new Array("420501","${eoms:a2u("宜昌")}"),
new Array("420601","${eoms:a2u("襄樊")}"),
new Array("420701","${eoms:a2u("鄂州")}"),
new Array("420801","${eoms:a2u("荆门")}"),
new Array("422103","${eoms:a2u("黄州")}"),
new Array("422201","${eoms:a2u("孝感")}"),
new Array("422301","${eoms:a2u("咸宁")}"),
new Array("422421","${eoms:a2u("江陵")}"),
new Array("422801","${eoms:a2u("恩施")}"),
new Array("430101","${eoms:a2u("长沙")}"),
new Array("430401","${eoms:a2u("衡阳")}"),
new Array("430501","${eoms:a2u("邵阳")}"),
new Array("432801","${eoms:a2u("郴州")}"),
new Array("432901","${eoms:a2u("永州")}"),
new Array("430801","${eoms:a2u("大庸")}"),
new Array("433001","${eoms:a2u("怀化")}"),
new Array("433101","${eoms:a2u("吉首")}"),
new Array("430201","${eoms:a2u("株洲")}"),
new Array("430301","${eoms:a2u("湘潭")}"),
new Array("430601","${eoms:a2u("岳阳")}"),
new Array("430701","${eoms:a2u("常德")}"),
new Array("432301","${eoms:a2u("益阳")}"),
new Array("432501","${eoms:a2u("娄底")}"),
new Array("440101","${eoms:a2u("广州")}"),
new Array("440301","${eoms:a2u("深圳")}"),
new Array("441501","${eoms:a2u("汕尾")}"),
new Array("441301","${eoms:a2u("惠州")}"),
new Array("441601","${eoms:a2u("河源")}"),
new Array("440601","${eoms:a2u("佛山")}"),
new Array("441801","${eoms:a2u("清远")}"),
new Array("441901","${eoms:a2u("东莞")}"),
new Array("440401","${eoms:a2u("珠海")}"),
new Array("440701","${eoms:a2u("江门")}"),
new Array("441201","${eoms:a2u("肇庆")}"),
new Array("442001","${eoms:a2u("中山")}"),
new Array("440801","${eoms:a2u("湛江")}"),
new Array("440901","${eoms:a2u("茂名")}"),
new Array("440201","${eoms:a2u("韶关")}"),
new Array("440501","${eoms:a2u("汕头")}"),
new Array("441401","${eoms:a2u("梅州")}"),
new Array("441701","${eoms:a2u("阳江")}"),
new Array("450101","${eoms:a2u("南宁")}"),
new Array("450401","${eoms:a2u("梧州")}"),
new Array("452501","${eoms:a2u("玉林")}"),
new Array("450301","${eoms:a2u("桂林")}"),
new Array("452601","${eoms:a2u("百色")}"),
new Array("452701","${eoms:a2u("河池")}"),
new Array("452802","${eoms:a2u("钦州")}"),
new Array("450201","${eoms:a2u("柳州")}"),
new Array("450501","${eoms:a2u("北海")}"),
new Array("460100","${eoms:a2u("海口")}"),
new Array("460200","${eoms:a2u("三亚")}"),
new Array("510101","${eoms:a2u("成都")}"),
new Array("513321","${eoms:a2u("康定")}"),
new Array("513101","${eoms:a2u("雅安")}"),
new Array("513229","${eoms:a2u("马尔康")}"),
new Array("510301","${eoms:a2u("自贡")}"),
new Array("500100","${eoms:a2u("重庆")}"),
new Array("512901","${eoms:a2u("南充")}"),
new Array("510501","${eoms:a2u("泸州")}"),
new Array("510601","${eoms:a2u("德阳")}"),
new Array("510701","${eoms:a2u("绵阳")}"),
new Array("510901","${eoms:a2u("遂宁")}"),
new Array("511001","${eoms:a2u("内江")}"),
new Array("511101","${eoms:a2u("乐山")}"),
new Array("512501","${eoms:a2u("宜宾")}"),
new Array("510801","${eoms:a2u("广元")}"),
new Array("513021","${eoms:a2u("达县")}"),
new Array("513401","${eoms:a2u("西昌")}"),
new Array("510401","${eoms:a2u("攀枝花")}"),
new Array("500239","${eoms:a2u("黔江土家族苗族自治县")}"),
new Array("520101","${eoms:a2u("贵阳")}"),
new Array("520200","${eoms:a2u("六盘水")}"),
new Array("522201","${eoms:a2u("铜仁")}"),
new Array("522501","${eoms:a2u("安顺")}"),
new Array("522601","${eoms:a2u("凯里")}"),
new Array("522701","${eoms:a2u("都匀")}"),
new Array("522301","${eoms:a2u("兴义")}"),
new Array("522421","${eoms:a2u("毕节")}"),
new Array("522101","${eoms:a2u("遵义")}"),
new Array("530101","${eoms:a2u("昆明")}"),
new Array("530201","${eoms:a2u("东川")}"),
new Array("532201","${eoms:a2u("曲靖")}"),
new Array("532301","${eoms:a2u("楚雄")}"),
new Array("532401","${eoms:a2u("玉溪")}"),
new Array("532501","${eoms:a2u("个旧")}"),
new Array("532621","${eoms:a2u("文山")}"),
new Array("532721","${eoms:a2u("思茅")}"),
new Array("532101","${eoms:a2u("昭通")}"),
new Array("532821","${eoms:a2u("景洪")}"),
new Array("532901","${eoms:a2u("大理")}"),
new Array("533001","${eoms:a2u("保山")}"),
new Array("533121","${eoms:a2u("潞西")}"),
new Array("533221","${eoms:a2u("丽江纳西族自治县")}"),
new Array("533321","${eoms:a2u("泸水")}"),
new Array("533421","${eoms:a2u("中甸")}"),
new Array("533521","${eoms:a2u("临沧")}"),
new Array("540101","${eoms:a2u("拉萨")}"),
new Array("542121","${eoms:a2u("昌都")}"),
new Array("542221","${eoms:a2u("乃东")}"),
new Array("542301","${eoms:a2u("日喀则")}"),
new Array("542421","${eoms:a2u("那曲")}"),
new Array("542523","${eoms:a2u("噶尔")}"),
new Array("542621","${eoms:a2u("林芝")}"),
new Array("610101","${eoms:a2u("西安")}"),
new Array("610201","${eoms:a2u("铜川")}"),
new Array("610301","${eoms:a2u("宝鸡")}"),
new Array("610401","${eoms:a2u("咸阳")}"),
new Array("612101","${eoms:a2u("渭南")}"),
new Array("612301","${eoms:a2u("汉中")}"),
new Array("612401","${eoms:a2u("安康")}"),
new Array("612501","${eoms:a2u("商州")}"),
new Array("612601","${eoms:a2u("延安")}"),
new Array("612701","${eoms:a2u("榆林")}"),
new Array("620101","${eoms:a2u("兰州")}"),
new Array("620401","${eoms:a2u("白银")}"),
new Array("620301","${eoms:a2u("金昌")}"),
new Array("620501","${eoms:a2u("天水")}"),
new Array("622201","${eoms:a2u("张掖")}"),
new Array("622301","${eoms:a2u("武威")}"),
new Array("622421","${eoms:a2u("定西")}"),
new Array("622624","${eoms:a2u("成县")}"),
new Array("622701","${eoms:a2u("平凉")}"),
new Array("622801","${eoms:a2u("西峰")}"),
new Array("622901","${eoms:a2u("临夏")}"),
new Array("623027","${eoms:a2u("夏河")}"),
new Array("620201","${eoms:a2u("嘉峪关")}"),
new Array("622102","${eoms:a2u("酒泉")}"),
new Array("630100","${eoms:a2u("西宁")}"),
new Array("632121","${eoms:a2u("平安")}"),
new Array("632221","${eoms:a2u("门源回族自治县")}"),
new Array("632321","${eoms:a2u("同仁")}"),
new Array("632521","${eoms:a2u("共和")}"),
new Array("632621","${eoms:a2u("玛沁")}"),
new Array("632721","${eoms:a2u("玉树")}"),
new Array("632802","${eoms:a2u("德令哈")}"),
new Array("640101","${eoms:a2u("银川")}"),
new Array("640201","${eoms:a2u("石嘴山")}"),
new Array("642101","${eoms:a2u("吴忠")}"),
new Array("642221","${eoms:a2u("固原")}"),
new Array("650101","${eoms:a2u("乌鲁木齐")}"),
new Array("650201","${eoms:a2u("克拉玛依")}"),
new Array("652101","${eoms:a2u("吐鲁番")}"),
new Array("652201","${eoms:a2u("哈密")}"),
new Array("652301","${eoms:a2u("昌吉")}"),
new Array("652701","${eoms:a2u("博乐")}"),
new Array("652801","${eoms:a2u("库尔勒")}"),
new Array("652901","${eoms:a2u("阿克苏")}"),
new Array("653001","${eoms:a2u("阿图什")}"),
new Array("653101","${eoms:a2u("喀什")}"),
new Array("654101","${eoms:a2u("伊宁")}"),
new Array("710001","${eoms:a2u("台北")}"),
new Array("710002","${eoms:a2u("基隆")}"),
new Array("710020","${eoms:a2u("台南")}"),
new Array("710019","${eoms:a2u("高雄")}"),
new Array("710008","${eoms:a2u("台中")}"),
new Array("211001","${eoms:a2u("辽阳")}"),
new Array("653201","${eoms:a2u("和田")}"),
new Array("542200","${eoms:a2u("泽当镇")}"),
new Array("542600","${eoms:a2u("八一镇")}"),
new Array("820000","${eoms:a2u("澳门")}"),
new Array("810000","${eoms:a2u("香港")}")
);

function FillProvinces(selProvince)
{
    selProvince.options[0]=new Option('${eoms:a2u("请选择")}','');
    for(i=0;i<Provinces.length;i++)
    {
        selProvince.options[i+1]=new Option(Provinces[i][1],Provinces[i][0]);
    }
    selProvince.options[0].selected=true;
    selProvince.length=i+1;
}

function FillCitys(selCity,ProvinceCode)
{
    //if the province is a direct-managed city, like Beijing, shanghai, tianjin, chongqin,hongkong, macro
        //need not "����������"
        if(ProvinceCode=="110000"||ProvinceCode=="120000"||ProvinceCode=="310000"
                 ||ProvinceCode=="810000"||ProvinceCode=="820000"||ProvinceCode=="500000")
             count=0;
        else
                {selCity.options[0]=new Option('${eoms:a2u("请选择")}','');
                count=1;}
    for(i=0;i<Citys.length;i++)
    {
        if(Citys[i][0].toString().substring(0,2)==ProvinceCode.substring(0,2))
        {
            selCity.options[count]=new Option(Citys[i][1],Citys[i][0]);
            count=count+1;
        }
    }
    selCity.options[0].selected=true;
    selCity.length=count;
}

function Province_onchange(cEl, pEl)
{
	eoms.log(cEl,pEl);
    FillCitys(cEl,pEl.value);
}

function InitCitySelect(selProvince,selCity)
{
    //alert("begin");
    g_selProvince=selProvince;
    g_selCity=selCity;
    //selProvince.onchange=Function("Province_onchange();");
    
    selProvince.onchange=function(){
    	Province_onchange(selCity, selProvince);
    };
    FillProvinces(selProvince);
    Province_onchange(selCity, selProvince);
}
function changeType(v){
  document.getElementById("intelFrom").style.display = (v==1||v==2) ? "none" : "block";
  document.getElementById("localFrom").style.display = (v==0||v==2) ? "none" : "block";
  document.getElementById("intelTo").style.display = (v==1||v==2) ? "none" : "block";
  document.getElementById("localTo").style.display = (v==0||v==2) ? "none" : "block";
  if(v==0){
  	document.getElementById("fromProvince").value='';
  	document.getElementById("fromCity").value='';
  	document.getElementById("toProvince").value='';
  	document.getElementById("toCity").value='';
  }else if(v==1){
  	document.getElementById("fromCountry").value='';
  	document.getElementById("fromOperator").value='';
  	document.getElementById("toCountry").value='';
  	document.getElementById("toOperator").value='';
  }else{
  	document.getElementById("fromProvince").value='';
  	document.getElementById("fromCity").value='';
  	document.getElementById("toProvince").value='';
  	document.getElementById("toCity").value='';
  	document.getElementById("fromCountry").value='';
  	document.getElementById("fromOperator").value='';
  	document.getElementById("toCountry").value='';
  	document.getElementById("toOperator").value='';
  }
}  
function confirmCheck(v){
	var r=document.getElementsByName("cardType"); 
	for(var i=0;i<r.length;i++){
	    if(r[i].value==v){ 
	        r[i].checked=true; 
	        break; 
	    } 
	}
}
window.onload = function setup(){
 	InitCitySelect(document.getElementById("fromProvince"),document.getElementById("fromCity"));
 	InitCitySelect(document.getElementById("toProvince"),document.getElementById("toCity"));
 	confirmCheck('2');
 	changeType('2');
 }
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmInoutRecordForm'});
});
</script>
<html:form action="tawRmInoutRecords" method="post" styleId="tawRmInoutRecordForm"> 
	<table class="formTable">
		<caption>
			<fmt:message key="statInoutRecordDetail.heading" />
		</caption>
		<tr>
			<td width="100" class="label">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.cardType" />
			</td> 
			<td width="500" colspan="2">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.allCard" /><html:radio property="cardType" value="2" onclick="changeType(this.value);"/>
				<eoms:label styleClass="desc" key="tawRmTestcardForm.intelCard" /><html:radio property="cardType" value="0" onclick="changeType(this.value);"/>
				<eoms:label styleClass="desc" key="tawRmTestcardForm.localCard" /><html:radio property="cardType" value="1" onclick="changeType(this.value);"/>
			</td>
		</tr>
		<tr id="intelFrom">
			<td width="100" class="label">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.ascriptionPlace" />
			</td> 
			<td width="500" colspan="2">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.country" />:
				<eoms:country name="fromCountry"/>
				<eoms:label styleClass="desc" key="tawRmTestcardForm.operator" />:
				<eoms:dict key="dict-plancontent" dictId="operator" beanId="selectXML"
					isQuery="false" selectId="fromOperator"/>
			</td>
		</tr>
		<tr id="localFrom">
			<td width="100" class="label">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.ascriptionPlace" />
			</td> 
			<td width="500" colspan="2">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.province" />
				<select id="fromProvince" size="1" name="fromProvince">
				    <option selected></option>
				</select>
				<eoms:label styleClass="desc" key="tawRmTestcardForm.city" />
				<select id="fromCity" size="1" name="fromCity">
			   	 <option selected></option>
				</select>
			</td>
		</tr>
		<tr id="intelTo">
			<td width="100" class="label">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.visitPlace" />
			</td> 
			<td width="500" colspan="2">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.country" />:
				<eoms:country name="toCountry"/>
				<eoms:label styleClass="desc" key="tawRmTestcardForm.operator" />:
				<eoms:dict key="dict-plancontent" dictId="operator" beanId="selectXML"
					isQuery="false" selectId="toOperator"/>
			</td>
		</tr>
		<tr id="localTo">
			<td width="100" class="label">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.visitPlace" />
			</td> 
			<td width="500" colspan="2">
				<eoms:label styleClass="desc" key="tawRmTestcardForm.province" />
				<select id="toProvince" size="1" name="toProvince">
				    <option selected></option>
				</select>
				<eoms:label styleClass="desc" key="tawRmTestcardForm.city" />
				<select id="toCity" size="1" name="toCity">
				    <option selected></option>
				</select>
			</td>
		</tr>
		<!-- <tr>
			<td width="100" class="label">
				<fmt:message key="tawRmTestcardForm.ascriptionPlace" />
			</td>
			<td width="500" colspan="3">
				<input type="text" name="ascriptionPlace" size="30" value="" class="text">
			</td>
		</tr>-->
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmTestcardForm.msisdn" />
			</td>
			<td width="500" colspan="3">
				<input type="text" name="msisdn" size="30" value="" class="text">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmInoutRecordForm.borrowDate" />
			</td>
			<td width="500" colspan="3">
				<fmt:message key="tawRmInoutRecordForm.from" /><input type="text" name="borrowStartDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
				<fmt:message key="tawRmInoutRecordForm.to" /><input type="text" name="borrowEndDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmInoutRecordForm.intendingReturnDate" />
			</td>
			<td width="500" colspan="3">
				<fmt:message key="tawRmInoutRecordForm.from" /><input type="text" name="intendingReturnStartDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
				<fmt:message key="tawRmInoutRecordForm.to" /><input type="text" name="intendingReturnEndDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmInoutRecordForm.realReturnDate" />
			</td>
			<td width="500" colspan="3">
				<fmt:message key="tawRmInoutRecordForm.from" /><input type="text" name="realReturnStartDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
				<fmt:message key="tawRmInoutRecordForm.to" /><input type="text" name="realReturnEndDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
			</td>
		</tr>
	</table>
	<br>
		<html:submit styleClass="button" property="method.statInoutRecord" onclick="bCancel=false">
            <fmt:message key="button.query"/>
        </html:submit>
</html:form>
<!-- </form>-->
<%@ include file="/common/footer_eoms.jsp"%>
