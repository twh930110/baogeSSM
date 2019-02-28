<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head> 
<title>天气查询</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"> 
<meta http-equiv="X-UA-Compatible" content="IE=9"> 
<meta http-equiv="X-UA-Compatible" content="IE=10">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
 </head>
 <body>
 <form id='form' action="">
 请输入城市名称: <input type="text" name="city" id='city' value = "${path}">
<input name="btn1" type="button" id="btn1"  value="查询" onclick="test();"/>
 </form>
<p id='result'></p> 
</body>
<script language="javascript"  type="text/javascript">
//查询城市
function test(){
	var city=$("#city").val();
	if(!city){
		alert("城市名称不能为空");
		return;
		}
    var url = "/test/city/getCity.do"
    $.ajax({
		async: false,
		url: url,
		type: 'post',
		data:{"city":city},
		cache: false,
		dataType: "json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(resp) {
      		$("#result").html(resp.city+": 天气 : "+resp.weather+" PM2.5质量: "+resp.pm25+" 风向风力: "+resp.wind+" 温度: "+resp.temp)
  		},
		error: function (jqXHR, textStatus, errorThrown) {
	
	 	}
	});
}
</script>
</html>