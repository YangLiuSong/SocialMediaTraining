
/**
 * 加载导航栏、子标签信息
 */
$().ready(function() {
	loadUnTagBlogDatas();
})
function loadUnTagBlogDatas(){
    $("#pageContex").empty();
    $("#pageContex").load("/SocialMediaTraining/dataPages/data_Show_Page.jsp",function(){});
    return false;
}