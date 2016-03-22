/**
 * Created by onedayrex on 2016/3/4.
 */
function loaduser(){
    $.ajax({
        url:"loaduser.do",
        type:"post",
        dataType:"json",
        success:function(result){
            $("#user_ul li").remove();
            var userlist = result.object;
            for(var i= 0;i<userlist.length;i++){
                var username = userlist[i];
                var str = "<li><i class='am-icon-user'>"+username+"</i></li>";
                var $li = $(str);
                $("#user_ul").append($li);
            }
        },
        error:function(){
            alert("加载失败");
        }
    })
}