
var offset=0;
var currtag_ids="";
var currtag_names="";
var mon_list = new Array("jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","decc");
var mon = "jan";

$(function () {
    $("#top").append("<div id=\"textcenter\" align=\"center\" style=\"margin-bottom:10px;\">\n" +
        "\t\t\t<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(1)\">一月</a>\n<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(2)\">二月</a>\n" +
        "\t\t\t<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(3)\">三月</a>\n<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(4)\">四月</a>\n" +
        "\t\t\t<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(5)\">五月</a>\n<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(6)\">六月</a>\n"+
        "\t\t\t<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(7)\">七月</a>\n<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(8)\">八月</a>\n" +
        "\t\t\t<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(9)\">九月</a>\n<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(10)\">十月</a>\n" +
        "\t\t\t<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(11)\">十一月</a>\n<a href=\"#\" style=\"color:#FFFFFF\" class=\"a_demo_two\" onclick=\"change_mon(12)\">十二月</a>\n" +
        "\t\t</div>")
	loadBlogDatas(mon);
	initBackToTop();
});
function change_mon(i) {
    mon = mon_list[i-1];
    offset = 0
    loadBlogDatas(mon);
    initBackToTop();
}
function loadBlogDatas(mon){
	$("#loading").show();
	$("#nomoredata").hide();
	$("#mainBannerContent2").empty();
	$.ajax({
		type: 'get',
		url: 'blogData-list.action',
        data:{
            mon: mon ,
            offset: offset
        },
		dataType: 'json',
		ansyc: false, //同步加载
		success: function(data){
			//window.console.log(data);
			if(data.length>0){
				$("#mainBannerContent2").empty();
				var str="";
				for(var i =0;i<data.length;i++){
					str+=buildData($.parseJSON(data[i]['Context']),data[i]['id']);
				}
                str += "<div class='mydiv'>"
				str += "<a href=\"#\" class=\"anniu\" onclick=\"loadBlogDatas(mon)\" style='alignment: center;align-self: center;align-items: center'>……点击加载下一页……</a>"
                str += "</div>"
                $("#loading").hide()
				$("#mainBannerContent2").append(str);
			}else{
				$("#loading").hide();
				$("#nomoredata").show();
			}
				
			//更新当前查询数据的起始位置
            offset+=10;
			//动态设置主页面的高度
			var height = $("#mainBannerContent2").height();
			$("#mainBanner").height(height+20);
			//alert(height);
		} 
	});
}

function initBackToTop(){
	var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop"></div>').appendTo($("#mainBanner"))
	.text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
		$("html, body").animate({ scrollTop: 0 }, 120);
	}), $backToTopFun = function() {
		var st = $(document).scrollTop();
		var winh = $(window).height();
		var doch = $(document).height() ;
		//判断是否显示 返回顶部 按钮
		(st > 0)? $backToTopEle.show(): $backToTopEle.hide();

		//当滚动条到底部时加载更多数据
		// if (st >= doch - winh&&st>0) {
		// 	//alert("滚动条已经到达底部 数据条数：" + countData());
		// 	//curroffset+=currlimit;
		// 	//alert(curroffset);
		// 	scroolLoaddata(offset);
		// }
	};
	$(window).bind("scroll", $backToTopFun);
	$(function() { $backToTopFun(); });
}

// function countData(){
// 	var count = 0;
// 	count = $("#mainBannerContent2").children('#mainBannerContent2People').length;
// 	return count;
// }

/**
 * 将每条微博信息编辑成界面展示需要的格式
 * 
 */
function buildData(data,Id){
	// alert(Id);
	var str = '<div id="mainBannerContent2People" dataId='+Id+'>';
	// alert(Id);
	//<!-- 头像-->
	str+='<div id="mainBannerContent2PeopleImg">';
	str+=buildUserTouxiang(data.gender);
	str+="</div>";

	//<!-- 第二个人微博 mainBannerContent2PeopleImg DIV 开始 -->
	str+='<div id="mainBannerContent2PeopleWord">';
	str+=buildUserName(data.user_name);
	str+=buildText(data.text);
	str+=buildImgs(data.pic_ids);
	//window.console.log(data);
	str+=buildTime(data.time,Id);
	//<!--微博回复栏-->
	str+='<div id="mainBannerContent2PeopleWordBack">';
	str+="</div>";
	//!--微博回复栏结束-->
	str+="</div>";
	//<!-- 第二个人微博 mainBannerContent2peopleWord DIV 结束  -->
	str+="</div>";
	return str;
}
function buildUserTouxiang(gender){

	var str="";
	if(gender.toString() == "f"){//用头像区别男女
	str+=' <img src="img/user/female.gif" width="54" height="54" alt="" title="" />';
	}else{
	str+='<img src="img/user/male.gif" width="54" height="54" alt="" title="" />';
	}
	// str+='<img src="'+touxiang+'" width="50" height="50" alt="" title="" />';
	return str;
}
function buildUserName(name){
	var str='<img src="img/biao.gif" alt="" width="17" height="13" align="absmiddle" id="pic" title="" />'
		+'<font class="f1">'+name+'</font>';
	return str;
}
function buildText(text){
	var str ='<font class="f2">&nbsp;&nbsp;'+text+'</font><br/><br/>';
	return str;
}
function buildImgs(imgs){
	var count = imgs.length;
	var rows = 0;
	if(count>0){
		rows=parseInt((count-1)/3)+1;
	}
	var str = "<div id='imgs'>";
	str+="<table style='width:558px ;>";
	for(var i =0;i<rows;i++){
		str+="<tr class='imgdataUl' style='width:558px;'>";
		for(var j =i*3;j<(i+1)*3;j++){
			if(j<count){
				str+="<td class='imgdataLi' style='width:180px;height:180px ; '>";
				str+='<img src="'+imgs[j]+'" width="180px" height="180px" alt="" title="" />';
				str+="</td>";
			}else{
				str+="<td class='imgdataLi' style='width:180px;height:180px ; '>";
				str+="</td>";
			}
		}
		str+="</tr>";			
	}
	str+="</table>";
	str+="</div>";
	return str;
}
function buildTime(time,id){
	// tag_ids=[];
	// tag_ids.push(1);
	var str='<font class="f3">'+ time
	+' <div id="textright">'
	// +'<a href="#" class="a2" onclick="showDataTagPage('+id+','+tag_ids+')">查看标签</a>&nbsp;&nbsp;'
	+'<a href="###" class="a2" onclick="updata_tag_food('+id+')">餐饮</a>' + '          ' + '<a href="###" class="a2" onclick="updata_tag_life('+id+')">生活服务</a>'
    + '          ' + '<a href="###" class="a2" onclick="updata_tag_school('+id+')">校园</a>' + '          ' + '<a href="###" class="a2" onclick="updata_tag_outdoor('+id+')">户外</a>'
    + '          ' + '<a href="###" class="a2" onclick="updata_tag_enterment('+id+')">娱乐</a>' + '          ' + '<a href="###" class="a2" onclick="updata_tag_travel('+id+')">出行住宿</a>'
    + '          ' + '<a href="###" class="a2" onclick="updata_tag_shopping('+id+')">购物</a>' + '          ' + '<a href="###" class="a2" onclick="updata_tag_work('+id+')">工作相关</a>'
        + '          ' + '<a href="###" class="a2" onclick="updata_tag_others('+id+')">其他</a>'+ '&nbsp;'
	+'</div> </font><br /> ';
	return str;
}

function updata_tag_food(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"1",
        "tag_names":"餐饮"
    }
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                // window.alert(txt);
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_life(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"2",
        "tag_names":"生活服务"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_school(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"3",
        "tag_names":"校园"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_outdoor(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"4",
        "tag_names":"户外"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_enterment(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"5",
        "tag_names":"娱乐"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_travel(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"6",
        "tag_names":"出行住宿"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_shopping(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"7",
        "tag_names":"购物"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_work(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"8",
        "tag_names":"工作相关"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
function updata_tag_others(id) {
    var data={
        "mon":mon,
        "id":id,
        "tag_ids":"9",
        "tag_names":"其他"
    }
    // alert(data);
    $.ajax({
        type: 'post',
        url: 'blogData-save.action',
        ansyc: false, //同步加载
        data: data,
        dataType: 'text',
        success: function(data){
            var info = $.trim(data);
            if(info=="success"){
                var txt=  "保存成功！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
            }else{
                var txt="保存失败";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            }
        }
    })
}
