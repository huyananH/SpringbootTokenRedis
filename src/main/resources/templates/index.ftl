<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <title>登录</title>
    <meta charset="UTF-8">


</head>
<body>
    <button id="userId" type="submit">点击</button>
    <#if data??>
    <input name="id" value="${data.userId}">
    </#if>

    <script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">

        $("#userId").click(function () {
            $.post(
                    "/signin",
                    {userId:"1"},
                    function(data){
                        //data可以是xmlDoc,jsonObj,html,text,等等.
                        //this;//这个Ajax请求的选项配置信息，请参考jQuery.get()说到的this
                        alert(data);
                    },
                    "json"//这里设置了请求的返回格式为"json"
            );
        });


    </script>
</body>
</html>